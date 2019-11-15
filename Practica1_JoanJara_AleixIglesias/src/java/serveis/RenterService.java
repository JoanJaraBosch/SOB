/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveis;

import classes.Renter;
import classes.Room;
import classes.Tenant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joan Jara
 */
public class RenterService extends AbstractFacade<Renter>{
    @PersistenceContext(unitName = "sob_grup_04")
    private EntityManager em;

    public RenterService() {
        super(Renter.class);
    }
    
    public Renter find(Integer id){
        return super.find(id);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
