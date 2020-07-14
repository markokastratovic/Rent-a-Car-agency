/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import domen.Klijent;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class IzmeniKlijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Invalid object (Klijent)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        dbb.izmeni((Klijent) entity);
    }

}
