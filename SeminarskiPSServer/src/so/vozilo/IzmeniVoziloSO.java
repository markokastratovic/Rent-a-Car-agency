/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vozilo;

import domen.Vozilo;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class IzmeniVoziloSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Vozilo)) {
            throw new Exception("Invalid object (Vozilo)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        dbb.izmeni((Vozilo) entity);
    }

}
