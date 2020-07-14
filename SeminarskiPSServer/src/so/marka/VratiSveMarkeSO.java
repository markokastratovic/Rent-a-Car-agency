/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.marka;

import domen.ApstraktniDomenskiObjekat;
import domen.Marka;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiSveMarkeSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Marka)) {
            throw new Exception("Invalid object (Marka)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        String join = " ORDER BY NazivMarke"; //ovde je join uslov iskoriscen da se doda order by!
        lista = dbb.vratiSve((Marka) entity, join);
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

}
