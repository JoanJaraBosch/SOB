/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import com.sun.xml.messaging.saaj.util.Base64;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Joan Jara
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORITATION_HEADER= "Authoritation";
    private static final String AUTHORITATION_HEADER_PREFIX= "Basic ";    
    private static final String SECURED_URL= "room ";    
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(requestContext.getUriInfo().getPath().contains(SECURED_URL)){
            List<String> headers = requestContext.getHeaders().get(AUTHORITATION_HEADER);
            if(headers!=null && headers.size()>0){
                String auth = headers.get(0);
                auth = auth.replace(AUTHORITATION_HEADER_PREFIX, "");
                String decode = Base64.base64Decode(auth);
                StringTokenizer tokenizer = new StringTokenizer(decode, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                if("sob".equals(username) && "sob".equals(password)){
                    return;
                }
            }
            Response unauthorized = Response.status(401).entity("User cannot access because don't have the suficient rigths.").build();
            requestContext.abortWith(unauthorized);
        }
    }
    
}
