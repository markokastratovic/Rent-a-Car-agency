/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.marka;

import domen.ApstraktniDomenskiObjekat;
import domen.Marka;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiMarkuPoIdSO extends ApstraktnaGenerickaOperacija {

    ApstraktniDomenskiObjekat marka;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Marka)) {
            throw new Exception("Invalid object (Marka)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        marka = dbb.vratiPoPrimarnomKljucu((Marka) entity, null);
    }

    public ApstraktniDomenskiObjekat getMarka() {
        return marka;
    }

}
