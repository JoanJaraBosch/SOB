/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Tenant;
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
public class WriteModificatTenantCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            Tenant user = (Tenant)request.getSession().getAttribute("userClient");
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("passwordT")));
            user.setName(request.getParameter("first_nameT"));
            user.setSurname(request.getParameter("last_nameT"));
            user.setEmail(request.getParameter("emailT"));
            user.setAge(Integer.parseInt(request.getParameter("ageT")));
            user.setPet(Boolean.parseBoolean(request.getParameter("petT")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smokeTr")));
            user.setSex(request.getParameter("sexT"));
            TenantClient tClient = new TenantClient();
            Response res1=tClient.updateTenant(user.getId(),user);
            System.out.println(res1);
            if(res1.getStatus()==201 || res1.getStatus()==403){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/writeModificatTenant.jsp").forward(request, response);
            }
    }
}
