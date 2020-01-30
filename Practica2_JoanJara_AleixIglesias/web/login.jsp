<%-- 
    Document   : login
    Created on : 23-ene-2020, 17:11:39
    Author     : Joan
--%>

<jsp:useBean id="user" class="frontend.TenantClient" scope="request" />
<html>
    <head>
        <title>Login d'Usuari</title>
    </head>
    <body>
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

