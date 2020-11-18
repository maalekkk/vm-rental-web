package pl.vmrent.web.config;

import javax.faces.annotation.FacesConfig;
import javax.faces.application.ViewVisitOption;
import javax.faces.context.FacesContext;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@FacesConfig
@WebListener
public class AppConfig implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        addExtensionLessMappings(event.getServletContext(), FacesContext.getCurrentInstance());
    }

    private void addExtensionLessMappings(ServletContext servletContext, FacesContext facesContext)
    {
        servletContext
                .getServletRegistrations().values().stream()
                .filter(servlet -> servlet.getClassName().equals(FacesServlet.class.getName()))
                .findAny()
                .ifPresent(facesServlet -> facesContext
                        .getApplication()
                        .getViewHandler()
                        .getViews(facesContext, "/", ViewVisitOption.RETURN_AS_MINIMAL_IMPLICIT_OUTCOME)
                        .forEach(view -> facesServlet.addMapping(view)));
    }
}
