package ru.rb.rest;

import java.text.MessageFormat;

import java.net.URI;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
//import org.jboss.logging.Message;

import ru.rb.data.Currency;
import ru.rb.data.Currencies;

@Path("/currency")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Produces({MediaType.APPLICATION_XML})
//@Consumes({MediaType.APPLICATION_XML})
@Stateless
public class CurrencyService {
  
  @PersistenceContext(unitName = "app-main")
  private EntityManager em;
  
  @Context
  private UriInfo uriInfo;

  private Currency getCurrencyByCode( String code ) {
    try {
      Query qu = em.createQuery("select CUR from Currency CUR where CUR.nm = :name");
      qu.setParameter("name", code);
      Currency cur = (Currency)qu.getSingleResult();
      if (cur == null) throw new NoResultException();
      return cur;
    } catch ( NoResultException ex ) {
      throw new NotFoundException(MessageFormat.format("Currency with code {0} not found", code), ex);
    }
  }
  
  /**
   * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/RESTFul-ex/rs/currency/USD -v
   * XML  : curl -X GET -H "Accept: application/xml" http://localhost:8080/RESTFul-ex/rs/currency/USD -v
   */
  @GET
  @Path("{id}")
  public Response getCurrency(@PathParam("id") String id) {
    Currency cur = getCurrencyByCode(id);
    return Response.ok(cur).build();
  }
  
  /**
   * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/RESTFul-ex/rs/currency -v
   * XML  : curl -X GET -H "Accept: application/xml" http://localhost:8080/RESTFul-ex/rs/currency -v
   */
  @GET
  public Response getAllBooks() {
    Query qu = em.createQuery("select CUR from Currency CUR order by CUR.nm");
    Currencies curs = new Currencies(qu.getResultList());
    return Response.ok(curs).build();
  }
  
  /**
   * curl -X POST --data-binary "" -H "Content-Type: application/xml" http://localhost:8080/RESTFul-ex/rs/currency -v
   * curl -X POST --data-binary "" -H "Content-Type: application/json" http://localhost:8080/RESTFul-ex/rs/currency -v
   */
  @POST
  public Response createCurrency(Currency curr) {
    if (curr == null) throw new BadRequestException();
    /*try { saveToXml(curr, "C:\\xml.dat"); } catch (Exception  ex) { ex.printStackTrace();}*/
    em.persist(curr);
    String id = String.valueOf(curr.getCtId());
    URI bookUri = uriInfo.getAbsolutePathBuilder().path(id).build();
    return Response.created(bookUri).build();
  }
  
  @PUT
  public Response updateCurrency(Currency curr) {
    if (curr == null) throw new BadRequestException();
    Currency curOrig = null;
    try {
      curOrig = getCurrencyByCode(curr.getNm());
      if ( curr.getCtId() != curOrig.getCtId() )
        throw new BadRequestException(MessageFormat.format("Currency with code {0} not found for update", curr.getNm()));
    } catch ( NotFoundException ex ) {
      throw new BadRequestException(MessageFormat.format("The passed record has the wrong Ct_Id: expected - {0}, passed - {1}", curOrig.getCtId(), curr.getCtId()), ex);
    }

    em.merge(curr);
    return Response.ok().build();
  }
  
  /**
   * curl -X DELETE http://localhost:8080/RESTFul-ex/rs/currency/ETH -v
   */
  @DELETE
  @Path("{id}")
  public Response deleteBook(@PathParam("id") String id) {
    Currency cur = getCurrencyByCode(id);
    em.remove(cur);
    return Response.noContent().build();
  }

}