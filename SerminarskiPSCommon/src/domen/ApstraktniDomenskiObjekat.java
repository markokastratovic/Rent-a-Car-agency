/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Marko
 */
public abstract class ApstraktniDomenskiObjekat {

    public abstract String vratiNazivTabele();

    public abstract List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    public abstract String vratiKoloneZaInsert();

    public abstract String vratiVrednostiZaInsert();

    public abstract String vratiPrimarniKljuc();

    public ApstraktniDomenskiObjekat vratiObjekatIzResultSeta(ResultSet rs) throws Exception {
        return null;
    }

    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String vratiKriterijumPretrage() {
        //mozda cu ovde kao parametar da prosledim apstraktni domenski objekat da bi se na osnovu voziloKriterijumPretrage mogao definisati upit
        return null;
    }

    public boolean slozenObjekat() {
        return false;
    }

    public void veziSlabObjekatZaJakObjekat(List<ApstraktniDomenskiObjekat> listaSlabih) {
        throw new UnsupportedOperationException("Objekat ove klase nema slab objekat");
    }

    public String joinUslovZaSlabObjekat() {
        return null;
    }

    public List<ApstraktniDomenskiObjekat> vratiListuSlabih() {
        throw new UnsupportedOperationException("Objekat ove klase nema slab objekat");
    }

    public ApstraktniDomenskiObjekat vratiTipSlabogObjekta() {
        throw new UnsupportedOperationException("Objekat ove klase nema slab objekat");
    }

    public void postaviID(int id) {
        throw new UnsupportedOperationException("Objekat ove klase nema slab objekat");
    }

}
