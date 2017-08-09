package ru.rb.data.util;
 
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import javax.persistence.PersistenceException;
 
@Provider
public class PersistenceExceptionHandler implements ExceptionMapper<PersistenceException> 
{
    @Override
    public Response toResponse(PersistenceException exception) 
    {
        return Response.status(422).entity(exception.getMessage()).build();  
    }
}