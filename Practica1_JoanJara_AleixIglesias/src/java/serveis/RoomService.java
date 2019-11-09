/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import classes.Room;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.RolesAllowed;
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
import static javax.ws.rs.client.Entity.entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; 

/**
 *
 * @author Joan Jara
 */
@Stateless
@Path("/room")
public class RoomService extends AbstractFacade<Room>{
    
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    public RoomService() {
        super(Room.class);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        if(super.find(id)==null) return Response.status(404).build();
        return Response.ok(super.find(id), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCriterion(@QueryParam("location") String city, @QueryParam("sort") String criterion) {
        List<Room> aux = new ArrayList<Room>();
        List<Room> all = this.findAll();
        try{
            for(Room r : all){
                if(r.getCity().toLowerCase().equals(city.toLowerCase())) aux.add(r);
            }
        }catch(java.lang.NullPointerException e){
            aux=this.findAll();
        }
        
        try{
            if(criterion.toLowerCase().equals("asc"))
            {
                Collections.sort(aux, Room.Comparators.PRICECOMP);
            }
            else if(criterion.toLowerCase().equals("desc"))
            {
                Collections.sort(aux, Room.Comparators.PRICECOMP);
                Collections.reverse(aux);
            }
        }catch(java.lang.NullPointerException e){
            
        }
        return Response.ok(aux,MediaType.APPLICATION_JSON).build();
    }
   
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> findAll() {
        return super.findAll();
    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Room entity) {
        if(entity==null || entity.getRoomID()==null) return Response.status(400).build();
        else if(super.find(entity.getRoomID())!=null) {
            return Response.status(403).build();
        }else{
            super.create(entity);
            return Response.status(202).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Room entity) {
        if(entity==null || entity.getRoomID()==null) return Response.status(400).build();
        else if(super.find(entity.getRoomID())!=null) {
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
