/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Room;
import backend.Tenant;
import frontend.RoomClient;
import frontend.TenantClient;
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
public class LlogarRoomCommand implements Command{

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        TenantClient tenantC = new TenantClient();
        Tenant t = (Tenant) request.getSession().getAttribute("usuariClient");
        // 2. produce the view with the web result
        Room room = (Room) request.getSession().getAttribute("roomLlogar");
        Response res = tenantC.llogarHabitacio(t.getId(), room);
        ServletContext context = request.getSession().getServletContext();
        request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("usuariClient"));
        if(res.getStatus()==201 || res.getStatus()==202 || res.getStatus()==200){
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        }else{
            context.getRequestDispatcher("/moredetails.jsp").forward(request, response);
        }
        
    }
}
