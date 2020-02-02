/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Renter;
import backend.Tenant;
import frontend.RenterClient;
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
public class WriteModificatRenterCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

            Renter user = (Renter)request.getSession().getAttribute("userClient");
            user.setName(request.getParameter("first_nameR"));
            user.setSurname(request.getParameter("last_nameR"));
            user.setEmail(request.getParameter("emailR"));
            user.setPassword(encryptar.EncryptarPassword.getMD5(request.getParameter("passwordR")));
            user.setPet(Boolean.parseBoolean(request.getParameter("petR")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smokerR")));
            user.setSex(request.getParameter("sexR"));
            user.setAdresa(request.getParameter("adressR"));
            user.setZip(Integer.parseInt(request.getParameter("zipR")));
            user.setCity(request.getParameter("cityR"));
            user.setAgemax(Integer.parseInt(request.getParameter("age_maxR")));
            user.setAgemin(Integer.parseInt(request.getParameter("age_minR")));
            RenterClient tClient = new RenterClient();
            Response res1=tClient.updateRenter(user.getId(),user);
            System.out.println(res1);
            if(res1.getStatus()==201 || res1.getStatus()==403){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/registerRenter.jsp").forward(request, response);
            }
    }
}
