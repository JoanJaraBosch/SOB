<%-- 
    Document   : room_modificacio
    Created on : 02-feb-2020, 17:00:29
    Author     : Joan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="backend.Renter" scope="session" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Afegir habitació</title>
        <link rel="stylesheet" href="css/search.css">
        <link rel="stylesheet" href="css/formulari.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="javascript/search.js"></script>
    </head>
    <header>
          <div class="wrapper">
            <div class="logo">
                Alquil·ler d'habitacions
            </div>
              <hr style="border-color:white;">
           <nav>
          <a href="index.jsp">Buscador</a>
          <% 
            if(request.getSession().getAttribute("usuariClient")==null){ 
                %>
              <a href="registre.do">Registrar-se</a>
              <a href="login.do">Autenticar-se</a>
         <%
            }else{
          %>
           <% 
            if(request.getSession().getAttribute("usuariClient")!=null && request.getSession().getAttribute("usuariClient")instanceof backend.Renter){ 
                %>
                <a href="addRoom.jsp">Afegir habitació</a>
                <a href="modificarHabitacio.do">Modificar habitació</a>
                <a href="eliminarroom.do">Eliminar habitació</a>
                <% }%>
              <a href="modificar.do">Modificar dades</a>
              <a href="eliminatusuari.do">Eliminar usuari</a>
              <a href="logout.do">Tancar sessió</a>
          <%}%>
      </nav>
          </div>
      </header>
    <body> 
        <form method="post" action="upgradeRoom.do">
          <table>
                <tr>
                    <td>
                        Description:
                    </td>
                    <td>
                        <input type="text" style="WIDTH: 228px; HEIGHT: 98px"
size=32
                               name="description" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        City:
                    </td>
                    <td>
                        <input type="text" 
                               name="city" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Adress:
                    </td>
                    <td>
                        <input type="text" 
                               name="adresa" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Zip:
                    </td>
                    <td>
                        <input type="text" 
                               name="zip" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price:
                    </td>
                    <td>
                        <input type="text" 
                               name="price" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Image url:
                    </td>
                    <td>
                        <input type="text" 
                               name="image_url" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Simple: 
                    </td>
                    <td>
                        <input type="radio" name="simple" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="simple" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                 <tr>
                    <td>
                        Furnished: 
                    </td>
                    <td>
                        <input type="radio" name="furnished" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="furnished" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                 <tr>
                    <td>
                        Indoor 
                    </td>
                    <td>
                        <input type="radio" name="outdoor" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="indoor" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Register Room" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>