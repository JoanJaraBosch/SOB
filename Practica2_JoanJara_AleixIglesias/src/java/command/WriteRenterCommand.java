/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Renter;
import backend.Room;
import backend.Tenant;
import frontend.RenterClient;
import frontend.TenantClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class WriteRenterCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            Renter user = new Renter();
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("password")));
            user.setUsername(request.getParameter("username"));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            user.setAdresa(request.getParameter("adress"));
            user.setZip(Integer.parseInt(request.getParameter("zip")));
            user.setCity(request.getParameter("city"));
            user.setAgemax(Integer.parseInt(request.getParameter("age_max")));
            user.setAgemin(Integer.parseInt(request.getParameter("age_min")));
            request.setAttribute("user", user);
            RenterClient tClient = new RenterClient();
            Response res = tClient.findAllRenters();
            System.out.println(res);
            user.setId(user.maxID(res.readEntity(new GenericType<List<Renter>>(){}))+1);
            Response res1=tClient.createRenter(user);
            System.out.println(res1);
            request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("userClient"));
            if(res1.getStatus()==201){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                request.setAttribute("error", res.getStatus());
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/registerRenter.jsp").forward(request, response);
            }
    }
}
