<%-- 
    Document   : login
    Created on : 23-ene-2020, 17:11:39
    Author     : Joan
--%>

<jsp:useBean id="user" class="backend.Renter" scope="session" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Autenticacio de l'usuari</title>
        <link rel="stylesheet" href="css/search.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="javascript/search.js"></script>
    </head>
    <body>
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
                <a href="login.do">Modificar habitació</a>
                <a href="eliminarroom.do">Eliminar habitació</a>
                <% }else{%>
               <a href="registre.do">Llogar habitació</a>
               <%}%>
              <a href="config.do">Modificar dades</a>
              <a href="eliminatuser.do">Eliminar usuari</a>
              <a href="logout.do">Tancar sessió</a>
          <%}%>
      </nav>
          </div>
      </header>
        <h2>Credencials</h2>  
        <form method="post" action="autenticacio.do">
             <% if (response.getStatus() == 404) { %>
             <p>Tenant o Renter not found</p><%}%>
            <table>
                <tr>
                    <tr>
                    <td>
                        Username:
                    </td>
                    <td>
                        <input type="text" 
                               name="username" required />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" 
                               name="password" required />
                    </td>
                    </tr>
                    <td>
                        Tipus:
                    </td>
                    <td>
                        <input type="radio" name="tipus" value="true" checked/>
                          Llogater
                        </label>
                        <label class="radio">
                         <input type="radio" name="tipus" value="false" />
                          Arrendador
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="LoginIn" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

