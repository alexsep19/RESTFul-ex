package ru.rb.data.util;
 
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.sql.SQLIntegrityConstraintViolationException;
 
@Provider
public class SQLIntegrityConstraintViolationExceptionHandler implements ExceptionMapper<SQLIntegrityConstraintViolationException> 
{
    @Override
    public Response toResponse(SQLIntegrityConstraintViolationException exception) 
    {
        return Response.status(422).entity(exception.getMessage()).build();  
    }
}