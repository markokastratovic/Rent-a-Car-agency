/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import domen.ApstraktniDomenskiObjekat;
import domen.Klijent;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiSveKlijenteSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Invalid object (Klijent)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        lista = dbb.vratiSve((Klijent) entity, null);
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

}
