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
    </head>
    <body>
        <form method="post" action="tipeModificar.do">
            <table>
                <tr>
                    <td>
                        Tipus d'usuari
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
                        <input type="submit" name="enter_button" value="Register" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
