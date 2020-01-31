/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import encryptar.EncryptarPassword;
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
import sun.net.www.content.text.Generic;

/**
 *
 * @author Joan
 */
public class AutenticacioCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        if(Boolean.parseBoolean(request.getParameter("tipus"))){//llogater
            TenantClient tClient = new TenantClient();
            Response res = tClient.findAllTenants();
            List<Tenant> tenantList = res.readEntity(new GenericType<List<Tenant>>(){});
            System.out.println(tenantList);
            Integer id = comprovacioAutenticacio(request.getParameter("username"), 
                    EncryptarPassword.getMD5(request.getParameter("password")), 
                    tenantList);
            if(id==null){
                request.setAttribute("errorLogin", 404);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/login.jsp").forward(request, response);
            }else{
                Response res1 = tClient.findTenantById(id);
                request.getSession().setAttribute("tenant", res1.readEntity(Tenant.class));
                ServletContext context = request.getServletContext();
                context.getRequestDispatcher("/menutenant.jsp").forward(request, response);
            }
        }else{//renter
            RenterClient rClient = new RenterClient();
            Response res =rClient.findAllRenters();
            List<Renter> renterList = res.readEntity(new GenericType<List<Renter>>(){});
            System.out.println(renterList);
                    
            Integer id = comprovacioAutenticacio(request.getParameter("username"), 
                    EncryptarPassword.getMD5(request.getParameter("password")), 
                    renterList);
            if(id==null){
                request.setAttribute("status", 404);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/login.jsp").forward(request, response);
            }else{
                Response res1 = rClient.findRenterById(id);
                Renter r = res1.readEntity(Renter.class);
                request.getSession().setAttribute("renterHabitacio",r);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/menu.jsp").forward(request, response);
            }
        }
    }
    
    public Integer comprovacioAutenticacio(String username, String password, List<?> llista){
        Integer id = null;
        for(Object ob:llista){
            if(ob instanceof Tenant){
                
                if(((Tenant) ob).getUsername().toLowerCase().equals(username.toLowerCase())){
                    System.out.println(((Tenant) ob).getPassword());
                    System.out.println(password);
                    if(((Tenant) ob).getPassword().toLowerCase().equals(password.toLowerCase())){
                        id = ((Tenant) ob).getId();
                    }
                }
            }else{
                if(((Renter) ob).getUsername().toLowerCase().equals(username.toLowerCase())){
                    System.out.println(((Renter) ob).getPassword());
                    System.out.println(password);
                    if(((Renter) ob).getPassword().toLowerCase().equals(password.toLowerCase())){
                        id = ((Renter) ob).getId();
                    }
                }
            }
        }
        return id;
    }
}
