package pl.vmrent.web.controller.rent;


import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.service.RentService;

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
    private List<Rent> rents;

    @Inject
    private RentService rentService;

    @PostConstruct
    private void init()
    {
        rents = rentService.getAll();
    }

    public List<Rent> getRents()
    {
        return rents;
    }
}
