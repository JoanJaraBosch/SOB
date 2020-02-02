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
import frontend.RoomClient;
import frontend.TenantClient;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joan
 */
public class EliminarRoomCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            String dbname = "sob_grup_04";
            Room room=null;
            Renter r = (Renter)request.getSession().getAttribute("usuariClient");
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname + ";create=true", "sob", "sob");
                Statement stmt = con.createStatement();
                room=(Room)stmt.executeQuery("SELECT c FROM Room c WHERE c.renter = :renter");
                System.out.println(room);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EliminarRoomCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EliminarRoomCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            RoomClient roomC = new RoomClient();
            roomC.deleteById(room.getRoomID());
            request.getSession().setAttribute("usuariClient", request.getSession().getAttribute("userClient"));
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);      
    }
    
}
