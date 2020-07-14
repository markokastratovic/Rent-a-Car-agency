/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenje;

import domen.Zaduzenje;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class RazduziZaduzenjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Zaduzenje)) {
            throw new Exception("Invalid object (Zaduzenje)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        dbb.izmeni((Zaduzenje) entity);
    }

}
