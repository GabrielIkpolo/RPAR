/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rpar.jpaejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.rpar.entity.NsukkaPetrolStations;

/**
 *
 * @author angelis
 */
@Stateless
public class NsukkaPetrolStationsFacade extends AbstractFacade<NsukkaPetrolStations> {

    @PersistenceContext(unitName = "PARPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NsukkaPetrolStationsFacade() {
        super(NsukkaPetrolStations.class);
    }
    
}
