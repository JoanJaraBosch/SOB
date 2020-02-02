<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<jsp:useBean id="user" class="frontend.TenantClient" scope="request" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Registre d'usuari</title>
    </head>
    <body>
        <% if (response.getStatus() != 201) { %>
        <p>Error al registrar el Tenant</p><%}%>
        <h2>Dades de l'usuari</h2>  
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
                        <input type="submit" name="enter_button" value="Register" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
