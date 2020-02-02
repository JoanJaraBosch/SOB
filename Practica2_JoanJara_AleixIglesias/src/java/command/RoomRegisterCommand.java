/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Renter;
import backend.Room;
import backend.Tenant;
import encryptar.EncryptarPassword;
import frontend.RenterClient;
import frontend.RoomClient;
import frontend.TenantClient;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan
 */
public class RoomRegisterCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            
        
        Room room = new Room();
        RoomClient tClient = new RoomClient();
        Response res = tClient.findAllRooms();
        RenterClient renter = new RenterClient();
        
        room.setDescription(request.getParameter("description"));
        room.setCity(request.getParameter("city"));
        room.setAdresa(request.getParameter("adresa"));
        room.setZip(Integer.parseInt(request.getParameter("zip")));
        room.setPrice(Float.parseFloat(request.getParameter("price")));
        room.setImatge(request.getParameter("image_url"));        
        room.setSimple(Boolean.parseBoolean(request.getParameter("simple")));
        room.setIndoor(Boolean.parseBoolean(request.getParameter("indoor")));
        room.setFurnished(Boolean.parseBoolean(request.getParameter("furnsihed")));       
        room.setRoomID(room.maxID(res.readEntity(new GenericType<List<Room>>(){}))+1);
        room.setRenter((Renter)request.getSession().getAttribute("renter"));
        System.out.println(room);
        
        Response resp = tClient.createRoom(room);
        ServletContext context = request.getSession().getServletContext();
        
        if(resp.getStatus()!=201){
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }else{
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
