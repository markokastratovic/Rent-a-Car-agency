/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vozilo;

import domen.ApstraktniDomenskiObjekat;
import domen.Vozilo;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiSvaVozilaSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Vozilo)) {
            throw new Exception("Invalid object (Vozilo)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        String join = " v join Model m on (v.ModelID=m.ModelID) join Marka ma on (m.Marka=ma.MarkaID)";
        lista = dbb.vratiSve((ApstraktniDomenskiObjekat) entity, join);
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

}
