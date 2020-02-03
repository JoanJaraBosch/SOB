<%-- 
    Document   : moredetails
    Created on : 02-feb-2020, 17:54:21
    Author     : Joan
--%>

<%@page import="backend.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" class="backend.Renter" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Més informacio de la habitacio</title>
        <link rel="stylesheet" href="css/search.css">
        <link rel="stylesheet" href="css/habitacions.css">
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
    <br />
    <body>
        <div>
            <image src="${roomLlogar.imatge}" />
            <p id="preuMes" class="currency">${roomLlogar.price}€</p>
            <p id="descripcio" class="text-muted"> ${roomLlogar.description}</p>
            <div class="row-form"> 
                <br/>
                <p id="descripcio">•Adreça: ${roomLlogar.city}. ${roomLlogar.adresa}, ${elem.zip}</p>
                <%
                    Room r=(Room)request.getSession().getAttribute("roomLlogar");
                    if(r.getSimple()){
                    %> 
                    <p id="descripcio">•L'habitació és simple</p>
               <%
                    }else{
               %>
                <p id="descripcio">•L'habitació és doble o de més d'una persona</p>
                <%}%>
                <%
                    if(r.getFurnished()){
                    %> 
                    <p id="descripcio">•L'habitació està moblada.</p>
               <%
                    }else{
               %>
                <p id="descripcio">•L'habitació no està moblada.</p>
                <%}%>
                <%
                    if(r.getIndoor()){
                    %> 
                    <p id="descripcio">•L'habitació és interior.</p>
               <%
                    }else{
               %>
               <ul><p id="descripcio">•L'habitació és exterior.</p></ul>
                <%}%>
            </div>
            <%
                if(request.getSession().getAttribute("usuariClient") instanceof backend.Tenant){
                %>
            <form id="formRoom" method="post" action="llogarHabitacio.do" class="form-inline">
                <input type="hidden" id="roomId" name="room" value="">
                <button class="btn btn-success my-2 my-sm-0" id="habId" type="submit" name="idRoom" value="${elem.roomID}">Llogar habitacio</button>
            </form>
            <%}%>
        </div>
        <br/>
        <br/>
        <br/>
    </div>
    </body>
</html>
