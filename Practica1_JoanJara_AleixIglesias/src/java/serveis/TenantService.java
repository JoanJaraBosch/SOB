/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import classes.Tenant;
import classes.Room;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan Jara
 */
@Stateless
@Path("tenant")
public class TenantService extends AbstractFacade<Tenant>{
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    public TenantService() {
        super(Tenant.class);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        if(super.find(id)==null) return Response.status(404).build();
        return Response.ok(super.find(id), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Tenant entity) {
        if(entity==null || entity.getId()==null) return Response.status(400).build();
        else if(super.find(entity.getId())!=null) {
            return Response.status(403).build();
        }else{
            super.create(entity);
            return Response.status(202).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Tenant entity) {
        if(entity==null || entity.getId()==null) return Response.status(400).build();
        else if(super.find(entity.getId())!=null) {
            super.edit(entity);
            return Response.status(202).build();
        }else{
            this.createRoom(entity);
            return Response.status(201).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteRoomById(@PathParam("id") int id) {
        super.remove(super.find(id));
        return Response.ok().build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
