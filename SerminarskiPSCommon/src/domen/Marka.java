/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marko
 */
public class Marka extends ApstraktniDomenskiObjekat {

    private int markaId;
    private String nazivMarke;

    public Marka() {
    }

    public Marka(int markaId, String nazivMarke) {
        this.markaId = markaId;
        this.nazivMarke = nazivMarke;
    }

    public String getNazivMarke() {
        return nazivMarke;
    }

    public void setNazivMarke(String nazivMarke) {
        this.nazivMarke = nazivMarke;
    }

    public int getMarkaId() {
        return markaId;
    }

    public void setMarkaId(int markaId) {
        this.markaId = markaId;
    }

    @Override
    public String toString() {
        return nazivMarke;
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
        final Marka other = (Marka) obj;
        if (this.markaId != other.markaId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "Marka";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> marke = new ArrayList<>();
        while (rs.next()) {
            int markaID = rs.getInt(1);
            String nazivMarke = rs.getString(2);
            Marka m = new Marka(markaID, nazivMarke);
            marke.add(m);
        }
        return marke;
    }

    @Override
    public String vratiKoloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "MarkaID='" + markaId + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzResultSeta(ResultSet rs) throws Exception {
        Marka m = null;
        if (rs.next()) {
            int markaID = rs.getInt(1);
            String nazivMarke = rs.getString(2);
            m = new Marka(markaID, nazivMarke);
        }
        return m;
    }

}
