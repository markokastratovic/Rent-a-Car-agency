/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenje;

import domen.ApstraktniDomenskiObjekat;
import domen.Zaduzenje;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class PretraziZaduzenjaSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Zaduzenje)) {
            throw new Exception("Invalid object (Zaduzenje)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        Zaduzenje z = (Zaduzenje) entity;
        String join = " z join klijent k on(z.KlijentID=k.KlijentID) join Vozilo v on (v.VoziloID=z.VoziloID) join korisnik zaduzio on (z.ZaduzioID=zaduzio.KorisnikID) "
                + "left join korisnik razduzio on (z.RazduzioID=razduzio.KorisnikID) join Model m on (v.ModelID=m.ModelID) join Marka ma on (m.Marka=ma.MarkaID)";
        lista = dbb.vratiPoUslovu((Zaduzenje) entity, join, z.vratiKriterijumPretrage());
    }

    public List<ApstraktniDomenskiObjekat> getLista() {
        return lista;
    }

}
