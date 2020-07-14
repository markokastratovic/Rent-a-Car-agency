/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.model;

import domen.ApstraktniDomenskiObjekat;
import domen.Model;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiModelePoMarkiSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Model)) {
            throw new Exception("Invalid object (Model)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        String condition = "Marka=" + ((Model) entity).getMarka().getMarkaId();
        lista = dbb.vratiPoUslovu((Model) entity, null, condition);
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

}
