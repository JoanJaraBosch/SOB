/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joan
 */
public class EscollirModificarCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        if(Boolean.parseBoolean(request.getParameter("tipus"))){
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/modificarTenant.jsp").forward(request, response);
            
        }else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/modificarRenter.jsp").forward(request, response);
        }
    }
}
