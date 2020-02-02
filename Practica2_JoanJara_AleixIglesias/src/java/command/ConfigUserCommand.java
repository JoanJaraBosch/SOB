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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan
 */
public class ConfigUserCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        if(request.getSession().getAttribute("userClient")!=null && request.getSession().getAttribute("userClient") instanceof backend.Renter){
            Renter user = (Renter)request.getSession().getAttribute("userClient");
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("password")));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            user.setAdresa(request.getParameter("adress"));
            user.setZip(Integer.parseInt(request.getParameter("zip")));
            user.setCity(request.getParameter("city"));
            user.setAgemax(Integer.parseInt(request.getParameter("age_max")));
            user.setAgemin(Integer.parseInt(request.getParameter("age_min")));
            RenterClient tClient = new RenterClient();
            Response res1=tClient.updateRenter(user.getId(),user);
        }else{
            Tenant user =(Tenant)request.getSession().getAttribute("userClient");
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("password")));
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            TenantClient tClient = new TenantClient();
            Response res1=tClient.updateTenant(user.getId(),user);
        }
        
        
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
