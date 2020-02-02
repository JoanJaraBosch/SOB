/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Renter;
import backend.Tenant;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan
 */
public class RenterClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Practica2_JoanJara_AleixIglesias/rest/api/v1/";
    
    public RenterClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("renter/");
    }
    
     public Response createRenter(Renter renter) throws ClientErrorException{
        return webTarget.request().post(Entity.json(renter));
    }
     
     public Response findAllRenters() throws ClientErrorException{
        WebTarget resource = webTarget.path("");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
    }
     
     public Response findRenterById(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
    }
     
     public Response deleteById(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete();
    }
     
      public Response updateRenter(Integer id, Renter renter) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(renter));
    }
}
