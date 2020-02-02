/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Room;
import backend.Tenant;
import java.util.Base64;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Classe pel frontend per a registrar-se i fer les peticions.
 * @author Joan
 */
public class TenantClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Practica2_JoanJara_AleixIglesias/rest/api/v1/";
    private String aut = "Basic "+Base64.getEncoder().encodeToString(("sob:sob").getBytes());
    
    public TenantClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tenant/");
    }
    
     public Response createTenant(Tenant tenant) throws ClientErrorException{
        return webTarget.request().post(Entity.json(tenant));
    }
     
     public Response findAllTenants() throws ClientErrorException{
        WebTarget resource = webTarget.path("");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
    }
     
     public Response findTenantById(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, aut).get();
    }
     
     public Response deleteById(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, aut).delete();
    }
     
    public Response updateTenant(Integer id, Tenant t) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, aut).put(Entity.json(t));

    }
    
    
     public Response llogarHabitacio(Integer id, Room r) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id)).path("/rent");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, aut).post(Entity.json(r));

    }
}

