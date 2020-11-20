package pl.vmrent.web.controller.rent;


import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.service.RentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class RentListController
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
