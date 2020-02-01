<%-- 
    Document   : menutenant
    Created on : 30-ene-2020, 18:19:06
    Author     : Joan
--%>

<%-- 
    Document   : menu
    Created on : 23-ene-2020, 17:48:23
    Author     : Joan
--%>

<jsp:useBean id="user" class="backend.Tenant" scope="request" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Registre d'usuari</title>
    </head>
    <body>
        <% if (response.getStatus() != 201) { %>
        <p>Error al crear l'habitacio</p><%}%>
        <h2>Dades de l'habitacio</h2>  
        <form method="post" action="writeRoom.do">
          <table>
                <tr>
                    <td>
                        Description:
                    </td>
                    <td>
                        <input type="text" style="WIDTH: 228px; HEIGHT: 98px"
size=32
                               name="description" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        City:
                    </td>
                    <td>
                        <input type="text" 
                               name="city" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Adress:
                    </td>
                    <td>
                        <input type="text" 
                               name="adresa" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Zip:
                    </td>
                    <td>
                        <input type="text" 
                               name="zip" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price:
                    </td>
                    <td>
                        <input type="text" 
                               name="price" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Image url:
                    </td>
                    <td>
                        <input type="text" 
                               name="image_url" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Pet:
                    </td>
                    <td>
                        <input type="radio" name="pet" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="pet" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Smoker:
                    </td>
                    <td>
                        <input type="radio" name="smoker" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="smoker" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Simple: 
                    </td>
                    <td>
                        <input type="radio" name="simple" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="simple" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                 <tr>
                    <td>
                        Furnished: 
                    </td>
                    <td>
                        <input type="radio" name="furnished" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="furnished" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                 <tr>
                    <td>
                        Outdoor: 
                    </td>
                    <td>
                        <input type="radio" name="outdoor" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="outdoor" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Register Room" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>