/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.jpaejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.rpar.entity.PetrolInfo;

/**
 *
 * @author angelis
 */
@Stateless
public class PetrolInfoFacade extends AbstractFacade<PetrolInfo> {

    @PersistenceContext(unitName = "PARPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PetrolInfoFacade() {
        super(PetrolInfo.class);
    }
    
}
