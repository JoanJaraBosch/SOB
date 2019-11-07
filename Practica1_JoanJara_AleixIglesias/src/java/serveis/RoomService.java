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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
        return Response.ok(super.find(id), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Room> findByCriterion(@QueryParam("location") String city, @QueryParam("sort") String criterion) {
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
                Collections.reverse(aux);
            }
            else if(criterion.toLowerCase().equals("desc"))
            {
                Collections.sort(aux, Room.Comparators.PRICECOMP);
            }
        }catch(java.lang.NullPointerException e){
            
        }
        if(criterion==null && city==null) return new ArrayList<Room>();
        return aux;
    }
   
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Room> findAll() {
        return super.findAll();
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
