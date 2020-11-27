package pl.vmrent.web.validator.unique.machinename;

import pl.vmrent.web.service.MachineService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMachineNameValidator implements ConstraintValidator<UniqueMachineName, String>
{
    @Inject
    private MachineService machineService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !machineService.findMachineByName(s).isPresent();
    }
}
