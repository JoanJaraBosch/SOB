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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private static final String AUTHORITATION_HEADER= "Authorization";
    private static final String AUTHORITATION_HEADER_PREFIX= "Basic ";    
    private static final String SECURED_URL= "room";    
    String username, auth;
    String password;
    
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(requestContext.getUriInfo().getPath().contains(SECURED_URL)){
            List<String> headers = requestContext.getHeaders().get(AUTHORITATION_HEADER);
            if(headers!=null && headers.size()>0){
                String auth = headers.get(0);
                auth = auth.replace(AUTHORITATION_HEADER_PREFIX, "");
                String decode = Base64.base64Decode(auth);
                StringTokenizer tokenizer = new StringTokenizer(decode, ":");
                username = tokenizer.nextToken();
                password = tokenizer.nextToken();
                javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
                cq.select(cq.from(Client.class));
                List<Client> aux = getEntityManager().createQuery(cq).getResultList();
                if(aux!=null){
                    for(Client c : aux){

                        if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                            return;
                        }
                    }
                }
            }
            Response unauthorized = Response.status(401).entity("User cannot access because don't have the suficient rigths.").build();
            requestContext.abortWith(unauthorized);
        }
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
}
