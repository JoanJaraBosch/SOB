/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.*;
import frontend.RenterClient;
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
public class EliminarUsuariCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            if(request.getSession().getAttribute("usuariClient") instanceof backend.Tenant){
                Tenant t = (Tenant)request.getSession().getAttribute("usuariClient");
                System.out.println(t);
                TenantClient tenantC = new TenantClient();
                Response res = tenantC.deleteById(t.getId());
                System.out.println(res);
                request.getSession().invalidate();
            }else{
                Renter r = (Renter)request.getSession().getAttribute("usuariClient");
                RenterClient renterC = new RenterClient();
                renterC.deleteById(r.getId());
                request.getSession().invalidate();
            }
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);      
    }
    
}
