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
public class DodatnaOpcija extends ApstraktniDomenskiObjekat {

    private int opcijaId;
    private String nazivOpcije;
    private double cena;

    public DodatnaOpcija() {
    }

    public DodatnaOpcija(int opcijaId, String nazivOpcije, double cena) {
        this.opcijaId = opcijaId;
        this.nazivOpcije = nazivOpcije;
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getOpcijaId() {
        return opcijaId;
    }

    public void setOpcijaId(int opcijaId) {
        this.opcijaId = opcijaId;
    }

    public String getNazivOpcije() {
        return nazivOpcije;
    }

    public void setNazivOpcije(String nazivOpcije) {
        this.nazivOpcije = nazivOpcije;
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
        final DodatnaOpcija other = (DodatnaOpcija) obj;
        if (this.opcijaId != other.opcijaId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "dodatnaopcija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> dodatneOpcije = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("OpcijaID");
            String naziv = rs.getString("NazivOpcije");
            double cena = rs.getDouble("Cena");
            DodatnaOpcija d = new DodatnaOpcija(id, naziv, cena);
            dodatneOpcije.add(d);
        }
        return dodatneOpcije;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
