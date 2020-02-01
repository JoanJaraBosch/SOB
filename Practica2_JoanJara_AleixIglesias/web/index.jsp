<%-- 
    Document   : index
    Created on : 01-feb-2020, 13:35:22
    Author     : Joan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>
      Alquil·ler d'habitacions
    </title>
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
                <a href="registre.do">Afegir habitació</a>
                <a href="registre.do">Modificar habitació</a>
                <a href="login.do">Eliminar habitació</a>
                <% }else{%>
               <a href="registre.do">Llogar habitació</a>
               <%}%>
              <a href="registre.do">Modificar dades</a>
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
  </body>
</html>