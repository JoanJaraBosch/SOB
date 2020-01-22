/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import frontend.RoomClient;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        System.out.println(location);
        
        RoomClient r = new RoomClient();
        Response res = r.findCriterion(location, "desc");
        System.out.println(res);
        request.setAttribute("habitacions", res);
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/searchedRooms.jsp").forward(request, response);
    }
}
