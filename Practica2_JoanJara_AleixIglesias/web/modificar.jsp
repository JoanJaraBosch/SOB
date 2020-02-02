<%-- 
    Document   : modificar
    Created on : 02-feb-2020, 12:59:47
    Author     : Joan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="frontend.TenantClient" scope="request" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Modificacio de l'usuari</title>
        <link rel="stylesheet" href="css/search.css">
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
        <% 
            if(request.getSession().getAttribute("usuariClient") instanceof backend.Renter){
                request.getSession().setAttribute("tipus", false);
            }else{
                request.getSession().setAttribute("tipus", true);
            }
            %>
            <META HTTP-EQUIV="REFRESH" CONTENT="1;URL=/Practica2_JoanJara_AleixIglesias/tipeModificar.do">
    </body>
</html>
