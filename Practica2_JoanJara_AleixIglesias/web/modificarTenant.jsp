<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<jsp:useBean id="user" class="frontend.TenantClient" scope="request" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Registre d'usuari</title>
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
        <form method="post" action="writeModificatTenant.do">
            <table>
                <tr>
                    <td>
                        First Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="first_nameT" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="last_nameT" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" 
                               name="emailT" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Pet:
                    </td>
                    <td>
                        <input type="radio" name="petT" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="petT" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Smoker:
                    </td>
                    <td>
                        <input type="radio" name="smokerT" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="smokerT" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Age:
                    </td>
                    <td>
                        <input type="text" 
                               name="ageT" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Sex:
                    </td>
                    <td>
                        <input type="text" 
                               name="sexT" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" 
                               name="passwordT" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Modify" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
