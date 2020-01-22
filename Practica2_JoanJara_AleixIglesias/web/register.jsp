<jsp:useBean id="user" class="frontend.Client" scope="request" />
<html>
    <head>
        <title>Registre d'usuari</title>
    </head>
    <body>
        <h2>User details</h2>  
        <form method="post" action="write.do">
            <table>
                <tr>
                    <td>
                        First Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="first_name" 
                               value="<jsp:getProperty name="user" property="name" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="last_name" 
                               value="<jsp:getProperty name="user" property="surname" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" 
                               name="email" 
                               value="<jsp:getProperty name="user" property="email" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Username:
                    </td>
                    <td>
                        <input type="text" 
                               name="username" 
                               value="<jsp:getProperty name="user" property="username" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Pet:
                    </td>
                    <td>
                        <input type="text" 
                               name="pet" 
                               value="<jsp:getProperty name="user" property="pet" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Smoker:
                    </td>
                    <td>
                        <input type="text" 
                               name="smoker" 
                               value="<jsp:getProperty name="user" property="smoker" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Age:
                    </td>
                    <td>
                        <input type="text" 
                               name="age" 
                               value="<jsp:getProperty name="user" property="age" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Sex:
                    </td>
                    <td>
                        <input type="text" 
                               name="sex" 
                               value="<jsp:getProperty name="user" property="sex" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" 
                               name="password" 
                               value="<jsp:getProperty name="user" property="password" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Register" />
                    </td>
                    <td>
                    </td>
                </tr>
                <pre>
                    <jsp:getProperty name="user" property="message" />
                </pre>
            </table>
        </form>
    </body>
</html>
