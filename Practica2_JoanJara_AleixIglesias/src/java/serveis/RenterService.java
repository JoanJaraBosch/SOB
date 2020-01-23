/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import backend.Renter;
import backend.ResponseHandler;
import backend.Tenant;
import backend.Room;
import backend.Secured;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe per a fer la rest api del tenant
 * @author Joan Jara
 */
@Stateless
@Path("renter")
public class RenterService extends AbstractFacade<Renter>{
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    /**
     *
     */
    public RenterService() {
        super(Renter.class);
    }
    
    /**
     * Metode get per retornar un tenant. Te el tag Secured perque passara per un filtre perque requereix authenticacio
     * @param id
     * @return retorna 200 si tot ha anat be i 404 si no troba el tenant
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    @Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        if(super.find(id)==null){
            ResponseHandler r = new ResponseHandler();
            r.setResponse("404");
            return Response.status(404).entity(r).build();
        }
        return Response.ok(super.find(id)).build();
    }
    
    
      /**
     * Metode get per retornar tots els tenants
     * @return retorna 200
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        List<Renter> renters = super.findAll();
        
        if(renters==null){
            ResponseHandler r = new ResponseHandler();
            r.setResponse("404");
            return Response.status(404).entity(r).build();
        }
        GenericEntity<List<Renter>> entity = new GenericEntity<List<Renter>>(renters) {};
        return Response.ok(entity).build();
    }
    
    /**
     * Metode post per a crear un tenant
     * @param entity
     * @return retorna 400 si falta algun parametre, 403 si ja esta creat i 201 si no esta creat i el crea
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRenter(Renter entity) {
        if(entity==null){
            ResponseHandler r = new ResponseHandler();
            r.setResponse("400");
            return Response.status(400).entity(r).build();
        }
        else if(entity.getId()!=null){ 
            if(super.find(entity.getId())!=null) {
                ResponseHandler r = new ResponseHandler();
                r.setResponse("403");
                return Response.status(403).entity(r).build();
            }else{
                entity.setId(entity.maxID(super.findAll())+1);
                super.create(entity);
                return Response.status(201).entity(entity).build();
            }
        }else{
            entity.setId(entity.maxID(super.findAll())+1);
            super.create(entity);
            return Response.status(201).entity(entity).build();
        }
    }
    
    /**
     * Metode per actualitzar un tenant. Esta marcat amb el tag secured perque necessites autenticacio
     * @param id
     * @param entity
     * @return retorna 400 si falta alguna dada, 202 si edita la entitat i 201 si la crea i 202 si lactualitza
     */
    @PUT
    @Secured
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Renter entity) {
        if(entity==null || id==null){
            ResponseHandler r = new ResponseHandler();
            r.setResponse("400");
            return Response.status(400).entity(r).build();
        }
        else if(super.find(id)!=null) {
            super.edit(entity);
            return Response.status(202).entity(entity).build();
        }else{
            super.create(entity);
            return Response.status(201).entity(entity).build();
        }
    }
    
    /**
     * Metode delete de la calsse tenant que necessita autenticacio
     * @param id
     * @return retorna 200 si tot ha anat be o 404 si no troba la classe
     */
    @DELETE
    @Secured
    @Path("{id}")
    public Response deleteRenterById(@PathParam("id") int id) {
        Renter renter = super.find(id);
        if(renter!=null){
            super.remove(renter);
            return Response.ok().build();
        }else{
            ResponseHandler r = new ResponseHandler();
            r.setResponse("404");
            return Response.status(404).entity(r).build();
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
