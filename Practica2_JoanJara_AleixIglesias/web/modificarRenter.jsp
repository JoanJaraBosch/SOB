<jsp:useBean id="user" class="backend.Renter" scope="request" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Registre d'usuari</title>
    </head>
    <body>
        <% if (response.getStatus() != 201) { %>
        <p>Error al registrar el Renter</p><%}%>
        <h2>Dades de l'usuari</h2>  
        <form method="post" action="writeModificatRenter.do">
            <table>
                <tr>
                    <td>
                        First Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="first_nameR" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="last_nameR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" 
                               name="emailR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Pet:
                    </td>
                    <td>
                        <input type="radio" name="petR" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="petR" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Smoker:
                    </td>
                    <td>
                        <input type="radio" name="smokerR" value="true" checked/>
                          Yes
                        </label>
                        <label class="radio">
                         <input type="radio" name="smokerR" value="false" />
                          No
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        Age Min:
                    </td>
                    <td>
                        <input type="text" 
                               name="age_minR" required/>
                    </td>
                </tr>
                 <tr>
                    <td>
                        Age Max
                    </td>
                    <td>
                        <input type="text" 
                               name="age_maxR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Sex:
                    </td>
                    <td>
                        <input type="text" 
                               name="sexR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Zip:
                    </td>
                    <td>
                        <input type="text" 
                               name="zipR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Adress:
                    </td>
                    <td>
                        <input type="text" 
                               name="adressR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        City:
                    </td>
                    <td>
                        <input type="text" 
                               name="cityR" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" 
                               name="passwordR" required/>
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
