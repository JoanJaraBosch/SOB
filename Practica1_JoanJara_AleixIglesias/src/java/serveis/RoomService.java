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
 * Classe per a fer les crides per a les habitacions
 * @author Joan Jara
 */
@Stateless
@Path("room")
public class RoomService extends AbstractFacade<Room>{
    
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    /**
     *
     */
    public RoomService() {
        super(Room.class);
    }
    
    /**
     * Funcio que implementa un get per el id passat 
     * @param id
     * @return si troba l'habitació, retorna el json de l'habitació sino retorna l'error 404
     */
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        if(super.find(id)==null) return Response.status(404).entity("Room not found.").build();
        return Response.ok(super.find(id), MediaType.APPLICATION_JSON).build();
    }
    
    
    /**
     * Funcio que implementa un get per les queries location i sort on sort es obligatori 
     * @param city
     * @param criterion
     * @return retorna la llista ordenada pel preu en ordre asc o desc. Si no es passa retorna lerror 422
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCriterion(@QueryParam("location") String city, @QueryParam("sort") String criterion) {
        return queryRoom(city, criterion);
    }
   
    /**
     * Metode get per retornar totes les habitacions
     * @return totes les habitacions disponibles
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> findAll() {
        return super.findAll();
    }
    
    /**
     * Metode post per a crear o fer update d'una habitacio
     * @param entity
     * @return Retorna 201, 400, 403 segons si li passem les dades corresponents, si existeix i esta prohibit o el creem
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Room entity) {
        if(entity==null || entity.getRoomID()==null) return Response.status(400).entity("The request is bad formated.").build();
        else if(super.find(entity.getRoomID())!=null) {
            return Response.status(403).entity("The request can't be done because there is a room already created.").build();
        }else{
            super.create(entity);
            return Response.status(201).entity("The request was accepted and you created the room").build();
        }
    }
    
    /**
     * Metode put per a la classe room
     * @param id
     * @param entity
     * @return Retorna el codi 201, 202 o 400 segons creem, fem update o no estiguin ben passats els parametres
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Room entity) {
        if(entity==null || entity.getRoomID()==null) return Response.status(400).entity("There are invalid parameters. The sintax is not correct.").build();
        else if(super.find(entity.getRoomID())!=null) {
            super.edit(entity);
            return Response.status(202).entity("The request was accepted and you updated the room").build();
        }else{
            this.createRoom(entity);
            return Response.status(201).entity("The request was accepted and you created the room").build();
        }
    }
    
    /**
     * Metode delete per a la classe room
     * @param id
     * @return retorna el codi 200 si tot ha anat be o el codi 404 si no existeix aquesta habitacio
     */
    @DELETE
    @Path("{id}")
    public Response deleteRoomById(@PathParam("id") int id) {
        Room room = super.find(id);
        if(room!=null){
            super.remove(room);
            return Response.ok().build();
        }else{
            return Response.status(404).entity("Room not found to be eliminated").build();
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

    /**
     * Metode que retorna una llista usada per el motede get amb query
     * @param city
     * @param criterion
     * @return retorna el codi 200 si tot ha anat be o el codi 422 si falta lelement sort de la query
     */
    private Response queryRoom(String city, String criterion) {
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
            else
            {
                return Response.status(422).entity("You have to equal de sort parameter to asc or desc to search ascendent or descendent prices.").build();
            }
        }catch(java.lang.NullPointerException e){
            return Response.status(422).entity("The query parameter sort has to be used.").build();
        }
        GenericEntity<List<Room>> entity = new GenericEntity<List<Room>>(aux) {};
        return Response.ok(entity,MediaType.APPLICATION_JSON).build();
    }
}
