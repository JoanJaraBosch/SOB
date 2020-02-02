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
public class CerrarSessionCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            
    
            request.getSession().invalidate();
            ServletContext context = request.getSession().getServletContext();
            request.getSession().setAttribute("usuariClient", null);
            context.getRequestDispatcher("/index.jsp").forward(request, response);      
    }
}
