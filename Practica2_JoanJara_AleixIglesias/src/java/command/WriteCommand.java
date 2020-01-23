/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import backend.Renter;
import backend.Tenant;
import frontend.Client;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class WriteCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        if(Boolean.parseBoolean(request.getParameter("tipus"))){
            Tenant user = new Tenant();
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setPassword(request.getParameter("password"));
            user.setUsername(request.getParameter("username"));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            request.setAttribute("user", user);
        }else{
            Renter user = new Renter();
            user.setName(request.getParameter("first_name"));
            user.setSurname(request.getParameter("last_name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setUsername(request.getParameter("username"));
            user.setPet(Boolean.parseBoolean(request.getParameter("pet")));
            user.setSmoker(Boolean.parseBoolean(request.getParameter("smoker")));
            user.setSex(request.getParameter("sex"));
            request.setAttribute("user", user);
        }
        

        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
