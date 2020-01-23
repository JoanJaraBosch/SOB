<jsp:useBean id="user" class="frontend.Client" scope="request" />
<html>
    <head>
        <title>Registre d'usuari</title>
    </head>
    <body>
        <h2>Dades de l'usuari</h2>  
        <form method="post" action="write.do">
            <table>
                <tr>
                    <td>
                        First Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="first_name" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="last_name" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" 
                               name="email" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Username:
                    </td>
                    <td>
                        <input type="text" 
                               name="username" required/>
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
                        Age:
                    </td>
                    <td>
                        <input type="text" 
                               name="age" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Sex:
                    </td>
                    <td>
                        <input type="text" 
                               name="sex" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" 
                               name="password" required/>
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
