/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import classes.Renter;
import classes.Tenant;
import classes.Room;
import classes.Secured;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
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
import javax.ws.rs.core.GenericEntity;
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
    @Secured
    @Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        if(super.find(id)==null) return Response.status(404).build();
        return Response.ok(super.find(id), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    public Response find() {
        List<Tenant> tenants = super.findAll();
        String json = "{";
        Boolean primer=true;
        for(Tenant t: tenants){
            if(primer){
                json+=" Tenant: "+t.getName()+" "+t.getSurname();
                primer=false;
            }
            else{
                 json+=", Tenant: "+t.getName()+" "+t.getSurname();
            }
        }
        json+="}";
        if(tenants==null) return Response.status(404).build();
        return Response.ok(json,MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTenant(Tenant entity) {
        if(entity==null || entity.getId()==null) return Response.status(400).build();
        else if(super.find(entity.getId())!=null) {
            return Response.status(403).build();
        }else{
            super.create(entity);
            return Response.status(202).build();
        }
    }
    
    @POST
    @Secured
    @Path("{id}/rent")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRelation(Room room, @PathParam("id") Integer id) {
        Tenant tenant = find(id);
        Renter renter = new Renter();
        if(room!=null){
            renter = room.getRenter();
        }
        else return Response.status(404).entity("Room not found").build();
        
        if(tenant!=null){
            if(renter!=null){
                if(tenant.getPet()==renter.getPet()){
                    if(tenant.getSmoker()==renter.getSmoker()){
                        if(renter.getAgemin()<=tenant.getAge() && tenant.getAge()<=renter.getAgemax()){
                          if(renter.getSex().equals("unisex") || tenant.getSex().equals(renter.getSex())){
                                room.setTenant(tenant);
                                getEntityManager().merge(room);
                                return Response.ok(renter, MediaType.APPLICATION_JSON).build();   
                            }
                        }
                    }
                }
                return Response.status(403).entity("The tenant didn't accomplish one or more rules of the renter.").build();
            }else{
                return Response.status(404).entity("Renter not found").build();
            }
        }else{
            return Response.status(404).entity("Tenant not found").build();
        }
    }
    
    @PUT
    @Secured
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Tenant entity) {
        if(entity==null || entity.getId()==null) return Response.status(400).build();
        else if(super.find(entity.getId())!=null) {
            super.edit(entity);
            return Response.status(202).build();
        }else{
            this.createTenant(entity);
            return Response.status(201).build();
        }
    }
    
    @DELETE
    @Secured
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
