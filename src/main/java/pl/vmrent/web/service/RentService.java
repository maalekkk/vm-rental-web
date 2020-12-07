package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.rent.Period;
import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.rent.Status;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.RentRepository;
import pl.vmrent.web.util.DateTimeProvider;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.vmrent.web.model.rent.Status.*;

@RequestScoped
public class RentService
{
    @Inject
    private RentRepository rentRepository;

    @Inject
    private DateTimeProvider dateTimeProvider;

    public Rent saveRent(@Valid Rent rent)
    {
        if (rent.getPeriod().getEndDate().isBefore(rent.getPeriod().getStartDate()))
        {
            String msg = ResourceBundle.getBundle("ValidationMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("period.message");
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage("rent:endDate", error);
            return null;
        }
        if (!isMachineAvailable(rent))
        {
            String msg = ResourceBundle.getBundle("ValidationMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("rent.date.busy.message");
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage("rent:endDate", error);
            return null;
        }
        return rentRepository.save(rent);
    }

    public Optional<Rent> findRentById(UUID fromString)
    {
        return rentRepository.findById(fromString);
    }

    public List<Rent> findRentsByUser(User user)
    {
        return rentRepository.findByPredicate(rent -> rent.getUser().equals(user));
    }

    public List<Rent> filterRents(List<Predicate<Rent>> filters)
    {
        Stream<Rent> stream = rentRepository.findAll().stream();
        for (Predicate<Rent> filter : filters)
        {
            stream = stream.filter(filter);
        }
        return stream.collect(Collectors.toList());
    }

    public List<Rent> getAll()
    {
        return rentRepository.findAll();
    }

    public void deleteRent(Rent rent)
    {
        if (getRentStatus(rent) == RESERVED)
        {
            rentRepository.delete(rent);
        }
    }

    public void finishRent(Rent rent)
    {
        if (getRentStatus(rent) == IN_PROGRESS)
        {
            rent.getPeriod().setEndDate(dateTimeProvider.now());
            rentRepository.save(rent);
        }
    }

    public boolean isMachineAllocated(Machine machine)
    {
        return rentRepository.findByPredicate(rent -> getRentStatus(rent) == RESERVED || getRentStatus(rent) == IN_PROGRESS)
                .stream().anyMatch(r -> r.getMachine().equals(machine));
    }

    public boolean isMachineAvailable(Rent rent)
    {
        return rentRepository.findByPredicate(r -> r.getMachine().equals(rent.getMachine()) &&
                getRentStatus(r) == RESERVED || getRentStatus(r) == IN_PROGRESS)
                .stream().noneMatch(r -> arePeriodOverlaping(r.getPeriod(), rent.getPeriod()));
    }

    public Status getRentStatus(Rent rent)
    {
        LocalDateTime now = dateTimeProvider.now();
        if (isRentFinished(rent, now))
        {
            return FINISHED;
        }
        if (isRentInProgress(rent, now))
        {
            return IN_PROGRESS;
        }
        return RESERVED;
    }

    private boolean isRentInProgress(Rent rent, LocalDateTime time)
    {
        return rent.getPeriod().getStartDate().isBefore(time) && time.isBefore(rent.getPeriod().getEndDate());
    }

    private boolean isRentFinished(Rent rent, LocalDateTime time)
    {
        return rent.getPeriod().getEndDate().isBefore(time);
    }

    private boolean arePeriodOverlaping(@Valid Period p1, @Valid Period p2)
    {
        return p1.getStartDate().isBefore(p2.getEndDate()) && p2.getStartDate().isBefore(p1.getEndDate());
    }
}
