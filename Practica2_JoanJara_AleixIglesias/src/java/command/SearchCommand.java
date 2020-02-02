/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Room;
import frontend.RoomClient;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import serveis.RoomService;

/**
 *
 * @author Joan
 */
public class SearchCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        String location="";
        if(request.getParameterMap().containsKey("ciutat")){
            location = request.getParameter("ciutat").toLowerCase();
        }
        
        if(location.equals("")) location = "all";
        RoomClient r = new RoomClient();
        Response res = r.findCriterion(location, "desc");
        List<Room> habitacions = null;
        if(res.getStatus() == 200){
            habitacions = res.readEntity(new GenericType<List<Room>>(){});
            if(location.equals("all")){
                Collections.sort(habitacions, Room.Comparators.PRICECOMP);
                Collections.reverse(habitacions);
            }
            request.setAttribute("rooms",  habitacions);
        }
        System.out.println(habitacions);
        request.setAttribute("habitacions", habitacions);
        request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("userClient"));
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/searchedRooms.jsp").forward(request, response);
    }
}
