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
            <nav>
                <a href="index.jsp">Buscador</a>
          <% 
            if(request.getSession().getAttribute("renter")==null && request.getSession().getAttribute("tenant")==null){ 
                %>
              <a href="registre.do">Registrar-se</a>
              <a href="login.do">Autenticar-se</a>
         <%
            }
          %>
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