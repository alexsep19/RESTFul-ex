package ru.rb.data.util;
 
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.sql.SQLException;
 
@Provider
public class SQLExceptionHandler implements ExceptionMapper<SQLException> 
{
    @Override
    public Response toResponse(SQLException exception) 
    {
        return Response.status(422).entity(exception.getMessage()).build();  
    }
}