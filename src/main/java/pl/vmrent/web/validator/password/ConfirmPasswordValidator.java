package pl.vmrent.web.validator.password;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator
public class ConfirmPasswordValidator implements Validator<String>
{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException
    {
        UIInput inputPass = (UIInput) uiComponent.getAttributes().get("confirm_pass");
        if (!inputPass.isValid())
        {
            return;
        }

        String confirmPass = inputPass.getSubmittedValue().toString();
        if (!s.equals(confirmPass))
        {
            inputPass.setValid(false);
            ResourceBundle curBundle = ResourceBundle.getBundle("ValidationMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            String message = curBundle.getString("confirmpassword.message");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }
    }
}