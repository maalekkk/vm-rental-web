package pl.vmrent.web.controller.rent;


import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.service.RentService;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RentListController implements Serializable
{
    @Inject
    private UserService userService;

    @Inject
    private RentService rentService;

    private List<Rent> rents;

    @PostConstruct
    private void init()
    {
        if (userService.getCurrentRole() == Role.Owner)
        {
            rents = rentService.getAll();
        }
        else
        {
            rents = rentService.findRentsByUser(userService.getCurrentUser());
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

    public boolean isFinished(Rent rent)
    {
        return rentService.isRentFinished(rent);
    }

    public List<Rent> getRents()
    {
        return rents;
    }
}
