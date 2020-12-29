package pl.vmrent.web.validator.unique.machinename;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMachineNameValidator implements ConstraintValidator<UniqueMachineName, Machine>
{
    @Inject
    private MachineService machineService;

    @Override
    public boolean isValid(Machine machine, ConstraintValidatorContext constraintValidatorContext)
    {
        return machine.getId() != null || !machineService.findMachineByName(machine.getName()).isPresent();
    }
}
