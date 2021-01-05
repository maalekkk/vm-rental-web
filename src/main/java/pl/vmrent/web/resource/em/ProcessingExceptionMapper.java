package pl.vmrent.web.resource.em;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException>
{
    @Override
    public Response toResponse(ProcessingException exception)
    {
        return Response.status(BAD_REQUEST).build();
    }
}