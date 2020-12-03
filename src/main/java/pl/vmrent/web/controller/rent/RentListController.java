package pl.vmrent.web.controller.rent;


import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.rent.Status;
import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.service.RentService;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Named
@ViewScoped
public class RentListController implements Serializable
{
    private final List<Predicate<Rent>> predicates = new ArrayList<>();
    @Inject
    private UserService userService;
    @Inject
    private RentService rentService;
    private List<Rent> rents;
    private String machineFilter = "";
    private String userFilter = "";

    @PostConstruct
    private void init()
    {
        if (userService.getCurrentRole() != Role.Owner)
        {
            rents = rentService.findRentsByUser(userService.getCurrentUser());
        }
        else
        {
            rents = rentService.getAll();
        }
    }

    public String finishRent(Rent rent)
    {
        rentService.finishRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public String deleteRent(Rent rent)
    {
        rentService.deleteRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public void filter()
    {
        if (!userFilter.isEmpty())
        {
            predicates.add(rent -> rent.getUser().getUsername().equals(userFilter));
        }
        if (!machineFilter.isEmpty())
        {
            predicates.add(rent -> rent.getMachine().getName().equals(machineFilter));
        }
        rents = rentService.filterRents(predicates);
        predicates.clear();
    }

    public boolean isReserved(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.RESERVED;
    }

    public boolean isInProgress(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.IN_PROGRESS;
    }

    public boolean isFinished(Rent rent)
    {
        return rentService.getRentStatus(rent) == Status.FINISHED;
    }

    public List<Rent> getRents()
    {
        return rents;
    }

    public String getMachineFilter()
    {
        return machineFilter;
    }

    public void setMachineFilter(String machineFilter)
    {
        this.machineFilter = machineFilter;
    }

    public String getUserFilter()
    {
        return userFilter;
    }

    public void setUserFilter(String userFilter)
    {
        this.userFilter = userFilter;
    }
}
