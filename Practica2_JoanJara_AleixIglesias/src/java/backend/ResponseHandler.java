/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Joan Jara
 */
@Entity
public class ResponseHandler {
    private static String ERROR_404= "Error 404 the resource is not defined.";
    private static String ERROR_403= "Error 403 forbidden action.";
    private static String OK_201= "201 all okay. You created the resource.";
    private static String OK_202= "202 all okay. You updated the resource.";
    private static String ERROR_400= "Error 400 syntax error. You need to put all correctly.";
    private static String ERROR_401= "Error 401 You dont have rigths.";
    private static String ERROR_422= "Error 422 Server undertands the request but you need query parameters.";
    @Id
    private Integer id;
    public String response;
    
    public ResponseHandler() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        if(response.equals("201")) response=OK_201;
        else if(response.equals("202"))response=OK_202;
        else if(response.equals("400"))response=ERROR_400;
        else if(response.equals("401"))response=ERROR_401;
        else if(response.equals("403"))response=ERROR_403;
        else if(response.equals("404"))response=ERROR_404;
        this.response = response;
    }
    
    
}
