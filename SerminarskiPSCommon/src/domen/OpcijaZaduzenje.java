/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marko
 */
public class OpcijaZaduzenje extends ApstraktniDomenskiObjekat {

    private DodatnaOpcija dodatnaOpcija;
    private int zaduzenjeID;

    public OpcijaZaduzenje() {
    }

    public OpcijaZaduzenje(DodatnaOpcija dodatnaOpcija, int zaduzenjeID) {
        this.dodatnaOpcija = dodatnaOpcija;
        this.zaduzenjeID = zaduzenjeID;
    }

    public DodatnaOpcija getDodatnaOpcija() {
        return dodatnaOpcija;
    }

    public void setDodatnaOpcija(DodatnaOpcija dodatnaOpcija) {
        this.dodatnaOpcija = dodatnaOpcija;
    }

    public int getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(int zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpcijaZaduzenje other = (OpcijaZaduzenje) obj;
        if (!Objects.equals(this.dodatnaOpcija, other.dodatnaOpcija)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "opcijazaduzenje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> listaOpcijaZaduzenje = new ArrayList<>();
        while (rs.next()) {
            int idOpcije = rs.getInt("oz.OpcijaID");
            String nazivOpcije = rs.getString("do.NazivOpcije");
            double cenaOpcije = rs.getDouble("do.Cena");
            int idZaduzenja = rs.getInt("oz.ZaduzenjeID");
            DodatnaOpcija dodatnaOpcija = new DodatnaOpcija(idOpcije, nazivOpcije, cenaOpcije);
            OpcijaZaduzenje oz = new OpcijaZaduzenje(dodatnaOpcija, idZaduzenja);
            listaOpcijaZaduzenje.add(oz);
        }
        return listaOpcijaZaduzenje;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "OpcijaID,ZaduzenjeID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return dodatnaOpcija.getOpcijaId() + ", " + zaduzenjeID;
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinUslovZaSlabObjekat() {
        return " oz join dodatnaOpcija do on (oz.OpcijaID=do.OpcijaID)";
    }

}
