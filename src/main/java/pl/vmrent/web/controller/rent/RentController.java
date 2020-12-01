package pl.vmrent.web.controller.rent;

import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.service.RentService;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RentController implements Serializable
{
    private Rent rent = new Rent();

    @Inject
    private RentService rentService;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init()
    {
        rent.setUser(userService.getCurrentUser());
    }

    public String submit()
    {
        rentService.saveRent(rent);
        return "show-rents.xhtml?faces-redirect=true";
    }

    public String verify()
    {
        if (rentService.isMachineRented(rent.getMachine()))
        {
            return "error";
        }
        return null;
    }

    public Rent getRent()
    {
        return rent;
    }

    public void setRent(Rent rent)
    {
        this.rent = rent;
    }
}
