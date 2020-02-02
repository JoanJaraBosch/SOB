/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Room;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
/**
 *
 * @author Joan
 */
public class RoomClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Practica2_JoanJara_AleixIglesias/rest/api/v1/";

    /**
     * Constructor
     */
    public RoomClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("room");
    }
    
    /**
     * Mètode que retorna totes les habitacions en format JSON de la API REST
     * @param criterion criteri
     * @return totes les habitacions
     * @throws ClientErrorException error al connectar-se
     */
    public Response findAllRooms() throws ClientErrorException{
        WebTarget resource = webTarget.path("all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
    }
    
    /**
     * Mètode que retorna una habitacio donada una id, la recupera de la API REST
     * @param id id habitacio
     * @return habitació
     * @throws ClientErrorException error al connectar-se
     */
    public Response findRoom(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        
    }

    /**
     * Mètode que permet recuperar les habitacions donada la localitzacio i el criteri d'ordenament, ho recupera de la API REST, en format JSON
     * @param location ciutat
     * @param sort criteri
     * @return totes les habitacions o nomes les habitacions de la ciutat en específic
     * @throws ClientErrorException error al connectar-se
     */
    public Response findCriterion(String location, String sort) throws ClientErrorException {
        WebTarget resource = webTarget;
        if(location.equals("all")) return findAllRooms();
        
        resource = resource.queryParam("location", location).queryParam("sort", sort);
        System.out.println(resource.getUri().toString());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
    }
    
     public Response createRoom(Room room) throws ClientErrorException{
        return webTarget.request().post(Entity.json(room));
    }
     
     public Response deleteById(Integer id) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete();
    }
     
      public Response updateRenter(Integer id, Room room) throws ClientErrorException{
        WebTarget resource = webTarget.path("/").path(String.valueOf(id));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.json(room));
    }
    /**
     * Mètode per a tancar la comunicació amb la api REST
     */
    public void close() {
        client.close();
    }
}
