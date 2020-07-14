/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.model;

import domen.ApstraktniDomenskiObjekat;
import domen.Model;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiModelPoIdSO extends ApstraktnaGenerickaOperacija {

    ApstraktniDomenskiObjekat model;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Model)) {
            throw new Exception("Invalid object (Model)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        model = dbb.vratiPoPrimarnomKljucu((Model) entity, null);
    }

    public ApstraktniDomenskiObjekat getModel() {
        return model;
    }

}
