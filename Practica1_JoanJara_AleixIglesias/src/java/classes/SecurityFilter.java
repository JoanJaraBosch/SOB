/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import com.sun.xml.messaging.saaj.util.Base64;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Priority;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Joan Jara
 */



@Priority(Priorities.AUTHENTICATION)
@Provider
@Secured
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORITATION_HEADER= "Authorization";
    private static final String AUTHORITATION_HEADER_PREFIX= "Basic ";      
    String username, auth;
    String password;
    
    @Context
    private ResourceInfo resourceInfo;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        
        if (method != null) {
            Secured secured = method.getAnnotation(Secured.class);
            if(secured!=null){
                List<String> headers = requestContext.getHeaders().get(AUTHORITATION_HEADER);
                if(headers!=null && headers.size()>0){
                    String auth = headers.get(0);
                    auth = auth.replace(AUTHORITATION_HEADER_PREFIX, "");
                    String decode = Base64.base64Decode(auth);
                    StringTokenizer tokenizer = new StringTokenizer(decode, ":");
                    username = tokenizer.nextToken();
                    password = tokenizer.nextToken();

                    if("sob".equals(username) && "sob".equals(password)){
                        return;
                    }
                }
                Response unauthorized = Response.status(401).entity("User cannot access because don't have the suficient rigths.").build();
                requestContext.abortWith(unauthorized);
            }
        }
    }
}
