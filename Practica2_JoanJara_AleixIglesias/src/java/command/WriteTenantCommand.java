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
public class WriteTenantCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            Tenant user = new Tenant();
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("password")));
            user.setUsername(request.getParameter("username"));
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            TenantClient tClient = new TenantClient();
            Response res = tClient.findAllTenants();
            user.setId(user.maxID(res.readEntity(new GenericType<List<Tenant>>(){}))+1);
            Response res1=tClient.createTenant(user);
            System.out.println(res1);
            request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("usuariClient"));
            if(res1.getStatus()==201){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                request.setAttribute("error", res.getStatus());
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/registerTenant.jsp").forward(request, response);
            }
        
    }
}
