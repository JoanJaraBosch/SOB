/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Room;
import frontend.RenterClient;
import frontend.RoomClient;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan
 */
public class RoomDadesUpdateCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            
        
        Room room = (Room)request.getSession().getAttribute("RoomModificada");
        RoomClient tClient = new RoomClient();
        
        room.setDescription(request.getParameter("description"));
        room.setCity(request.getParameter("city"));
        room.setAdresa(request.getParameter("adresa"));
        room.setZip(Integer.parseInt(request.getParameter("zip")));
        room.setPrice(Float.parseFloat(request.getParameter("price")));
        room.setImatge(request.getParameter("image_url"));        
        room.setSimple(Boolean.parseBoolean(request.getParameter("simple")));
        room.setIndoor(Boolean.parseBoolean(request.getParameter("indoor")));
        room.setFurnished(Boolean.parseBoolean(request.getParameter("furnsihed")));    
        
       Response res1=tClient.updateRenter(room.getRoomID(),room);
            System.out.println(res1);
            if(res1.getStatus()==201 || res1.getStatus()==202){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/registerRenter.jsp").forward(request, response);
            }
    }
}
