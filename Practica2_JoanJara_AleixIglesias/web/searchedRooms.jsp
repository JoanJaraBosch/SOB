<%-- 
    Document   : searchedRooms
    Created on : 22-ene-2020, 19:00:49
    Author     : Joan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" class="backend.Renter" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Cercador d'habitacions</title>
        <link rel="stylesheet" href="css/search.css">
        <link rel="stylesheet" href="css/habitacions.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="javascript/search.js"></script>
    </head>
    <body id="rooms">
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
    <div class="barra_buscadora wrapper">
    <form action="search.do" method="post">
    <input type="search" placeholder="Search by city" name="ciutat"> 
    <button type="submit" class="searchButton">
        <i class="fa fa-search"></i>
    </button>
    </form>
    </div>
        <div class="container mt-3">
            <div class="row">
                <c:choose>
                    <c:when test = "${rooms != null}">
                        <c:forEach items="${rooms}" var="elem">
                            <div class="habitacio_dades">
                            <br/>
                            <hr style="border-color:orange; margin-left:20px" width="80%">
                            <br/>
                            <img class="imatge_habitacio" src="${elem.imatge}" />
                            <p id="preuMes" class="currency">${elem.price}€</p>
                            <p id="descripcio" class="text-muted"> ${elem.description}</p>
                            </div>
                            <br/>
                            <form id="formRoom" method="post" action="roomById.do" class="form-inline">
                                <input type="hidden" id="roomId" name="room" value="">
                                <button class="buto_mes_detalls" id="habId" type="submit" name="idRoom" value="${elem.roomID}">Més detalls</button>
                            </form>
                        </c:forEach> 
                    </c:when>
                    <c:otherwise> <div id="noHab" class="card card-body"><div class="col"><h2> No s'han trobat habitacions. </h2> </div></div></c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
