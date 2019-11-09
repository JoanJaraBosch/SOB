/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import classes.Client;
import classes.Room;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
@Path("/authentication")
public class AuthenticationService extends AbstractFacade<Client>{
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;
    
    public AuthenticationService() {
        super(Client.class);
    }
   
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> findAll() {
        return super.findAll();
    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createToken(Client entity) {
        if(entity==null || entity.getId()==null) return Response.status(403).build();
        else if(super.find(entity.getId())!=null) {
            if(entity.getToken().equals("null")){
                byte[] array = new byte[7]; // length is bounded by 7
                new Random().nextBytes(array);
                String generatedString = new String(array, Charset.forName("UTF-8"));
                entity.setToken(generatedString);
            }
            super.edit(entity);
            return Response.status(200).build();
        }else{
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            entity.setToken(generatedString);
            super.create(entity);
            return Response.status(202).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Client entity) {
        if(entity==null || entity.getId()==null) return Response.status(400).build();
        else if(super.find(entity.getId())!=null) {
            super.edit(entity);
            return Response.status(202).build();
        }else{
            this.createToken(entity);
            return Response.status(201).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteClientById(@PathParam("id") int id) {
        super.remove(super.find(id));
        return Response.ok().build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
