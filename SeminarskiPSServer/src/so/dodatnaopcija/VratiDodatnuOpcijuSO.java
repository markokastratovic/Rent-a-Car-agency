/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dodatnaopcija;

import domen.ApstraktniDomenskiObjekat;
import domen.DodatnaOpcija;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class VratiDodatnuOpcijuSO extends ApstraktnaGenerickaOperacija {

    ApstraktniDomenskiObjekat dodatnaOpcija;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof DodatnaOpcija)) {
            throw new Exception("Invalid object (DodatnaOpcija)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        String condition = "NazivOpcije='" + ((DodatnaOpcija) entity).getNazivOpcije() + "'";
        dodatnaOpcija = dbb.vratiPoUslovu((DodatnaOpcija) entity, null, condition).get(0);
    }

    public ApstraktniDomenskiObjekat getDodatnaOpcija() {
        return dodatnaOpcija;
    }

}
