<%-- 
    Document   : searchedRooms
    Created on : 22-ene-2020, 19:00:49
    Author     : Joan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Cercador d'Habitacions</title>
    </head>
    <body id="rooms">
        <jsp:include page="index.html" />
        <div class="container mt-3">
            <div class="row">
                <c:choose>
                    <c:when test = "${rooms != null}">
                        <c:forEach items="${rooms}" var="elem">
                            <div class="col-md-6 mb-3">
                                <div class="card card-body">
                                    <h2>${elem.renter.name}</h2>
                                    <image src="${elem.imatge}" />
                                    <h2 id="preuMes" class="currency">${elem.price}€</h2>
                                    <p id="descripcio" class="text-muted"> ${elem.description}</p>
                                    <div class="row-form"> 
                                        <h5>${elem.city}. ${elem.adresa}, ${elem.zip}</h5>
                                        <h5>Habitacio simple: ${elem.simple} </h5>
                                        <h5>Habitacio indoor: ${elem.indoor} </h5>
                                        <h5>Habitacio moblada: ${elem.furnished} </h5>
                                    </div>

                                    <form id="formRoom" method="post" action="roomById.do" class="form-inline">
                                        <input type="hidden" id="roomId" name="room" value="">
                                        <button class="btn btn-success my-2 my-sm-0" id="habId" type="submit" name="idRoom" onclick="saveID()" value="${elem.roomID}">More details</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach> 
                    </c:when>
                    <c:otherwise> <div id="noHab" class="card card-body"><div class="col"><h2> No s'han trobat habitacions. </h2> </div></div></c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
