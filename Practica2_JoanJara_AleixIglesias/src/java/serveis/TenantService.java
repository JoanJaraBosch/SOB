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
@Path("tenant")
public class TenantService extends AbstractFacade<Tenant>{
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    /**
     *
     */
    public TenantService() {
        super(Tenant.class);
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
        List<Tenant> tenants = super.findAll();
        
        if(tenants==null){
            ResponseHandler r = new ResponseHandler();
            r.setResponse("404");
            return Response.status(404).entity(r).build();
        }
        GenericEntity<List<Tenant>> entity = new GenericEntity<List<Tenant>>(tenants) {};
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
    public Response createTenant(Tenant entity) {
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
                if(!sameUsername(entity.getUsername())){
                    entity.setId(entity.maxID(super.findAll())+1);
                    super.create(entity);
                    return Response.status(201).entity(entity).build();
                }else{
                    ResponseHandler r = new ResponseHandler();
                    r.setResponse("403");
                    return Response.status(403).entity(r).build();
                }
            }
        }else{
            if(!sameUsername(entity.getUsername())){
                entity.setId(entity.maxID(super.findAll())+1);
                super.create(entity);
                return Response.status(201).entity(entity).build();
            }else{
                ResponseHandler r = new ResponseHandler();
                r.setResponse("403");
                return Response.status(403).entity(r).build();
            }
        }
    }
    
    /**
     * Metode post per a demanar les dades del renter per a alquilar una habitació. Metode marcat amb el tag secured perque necessita autenticacio
     * @param room
     * @param id
     * @return Retorna 404 si falta alguna classe, 403 si no compleix algun requeriment i 20o si tot ha anat be
     */
    @POST
    @Secured
    @Path("{id}/rent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRelation(Room room, @PathParam("id") Integer id) {
        Tenant tenant = find(id);
        Renter renter = new Renter();
        return compleixRequeriments(tenant, renter, room);
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
    public Response edit(@PathParam("id") Integer id, Tenant entity) {
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
    public Response deleteTenantById(@PathParam("id") int id) {
        Tenant tenant = super.find(id);
        if(tenant!=null){
            tenantVinculat(tenant);
            return Response.ok().build();
        }else{
            ResponseHandler r = new ResponseHandler();
            r.setResponse("404");
            return Response.status(404).entity(r).build();
        }
    }

    /**
     * Metode que serveix per a eliminar un tenat i mira si esta vinculat o no amb una habitacio
     * @param tenant
     */
    public void tenantVinculat(Tenant tenant){
        //desvincular si te una habitacio llogada, sino en te cap no pasa res
        List<Room> rooms = (List<Room>) getEntityManager().createNamedQuery("Room.findAll").getResultList();
        for(Room r : rooms){
            if(r.getTenant() !=null && r.getTenant().equals(tenant)){
                r.setTenant(null);
                break;
            }
        }
        super.remove(tenant);
    }
    
    /**
     * Metode per a retornar si compleix o no amb els requeriments del renter
     * @param tenant
     * @param renter
     * @return retorna true si els cokpleix i fals si no els compleix
     */
    public Response compleixRequeriments(Tenant tenant, Renter renter,Room room) {
        Response response;
        System.out.println(tenant.toString());
        try {
            Tenant exist = null;
            if(room!=null){
                renter = room.getRenter();
                exist = room.getTenant();
            }
            else{
                ResponseHandler r = new ResponseHandler();
                r.setResponse("404");
                response= Response.status(404).entity(r).build();
            }
            
            if(exist==null){
                if(tenant!=null){
                    if(renter!=null){
                        if (renter.getPet() || tenant.getPet().equals(renter.getPet())) {
                            if (renter.getSmoker() || tenant.getSmoker().equals(renter.getSmoker())) {
                                 if (renter.getAgemin() <= tenant.getAge() && tenant.getAge() <= renter.getAgemax()) {
                                     if (renter.getSex().equals("unisex") || tenant.getSex().equals(renter.getSex())) {
                                        room.setTenant(tenant);
                                        getEntityManager().merge(room);
                                        response= Response.ok(renter).build();
                                     }else{
                                         ResponseHandler r = new ResponseHandler();
                                         r.setResponse("403");
                                        response= Response.status(403).entity(r).build();
                                     }
                                 }else{
                                     ResponseHandler r = new ResponseHandler();
                                     r.setResponse("403");
                                    response= Response.status(403).entity(r).build();
                                 }
                             }else{
                                ResponseHandler r = new ResponseHandler();
                                r.setResponse("403");
                                 response= Response.status(403).entity(r).build();
                             }
                        }else{
                            ResponseHandler r = new ResponseHandler();
                            r.setResponse("403");
                            response= Response.status(403).entity(r).build();
                        }
                    }else{
                        ResponseHandler r = new ResponseHandler();
                        r.setResponse("404");
                        response= Response.status(404).entity(r).build();
                    }
                }else{
                    ResponseHandler r = new ResponseHandler();
                    r.setResponse("404");
                    response= Response.status(404).entity(r).build();
                }
            }else{
                ResponseHandler r = new ResponseHandler();
                r.setResponse("404");
                 response=Response.status(403).entity(r).build();
            }
        } catch (java.lang.NullPointerException e) {
            ResponseHandler r = new ResponseHandler();
            r.setResponse("400");
            response=Response.status(400).entity(r).build();
        }
        return response;
    }
    
    private Boolean sameUsername(String username){
        Boolean same = false;
        List<Tenant> all = this.findAll();
        try{
            for(Tenant r : all){
                if(r.getUsername().toLowerCase().equals(username.toLowerCase())) same=true;
            }
        }catch(java.lang.NullPointerException e){
            same=false;
        }
        return same;
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
