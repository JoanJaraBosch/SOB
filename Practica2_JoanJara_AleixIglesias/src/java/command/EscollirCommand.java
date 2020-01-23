/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Renter;
import backend.Tenant;
import frontend.RenterClient;
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
public class EscollirCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        if(Boolean.parseBoolean(request.getParameter("tipus"))){
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/registerTenant.jsp").forward(request, response);
            
        }else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/registerRenter.jsp").forward(request, response);
        }
        

        // 2. produce the view with the web result
        
    }
    
}
