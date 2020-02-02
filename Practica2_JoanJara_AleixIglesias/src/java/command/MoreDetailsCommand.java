/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Room;
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
public class MoreDetailsCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        RoomClient roomC = new RoomClient();
        Response res = roomC.findRoom(Integer.parseInt(request.getParameter("idRoom")));
        // 2. produce the view with the web result
        request.getSession().setAttribute("roomLlogar", res.readEntity(Room.class));
        ServletContext context = request.getSession().getServletContext();
        request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("usuariClient"));
        context.getRequestDispatcher("/moredetails.jsp").forward(request, response);
    }
}
