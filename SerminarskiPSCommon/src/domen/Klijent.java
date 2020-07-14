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
public class Klijent extends ApstraktniDomenskiObjekat {

    private int klijentId;
    private String ime;
    private String prezime;
    private String jmbg;
    private String adresa;
    private String brojTelefona;
    private String kriterijumPretrage;

    public Klijent() {
    }

    public Klijent(int klijentId, String ime, String prezime, String jmbg, String adresa, String brojTelefona) {
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKriterijumPretrage() {
        return kriterijumPretrage;
    }

    public void setKriterijumPretrage(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Klijent other = (Klijent) obj;
        if (this.klijentId != other.klijentId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> klijenti = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String ime = rs.getString(2);
            String prezime = rs.getString(3);
            String jmbg = rs.getString(4);
            String adresa = rs.getString(5);
            String brojTelefona = rs.getString(6);
            Klijent k = new Klijent(id, ime, prezime, jmbg, adresa, brojTelefona);
            klijenti.add(k);
        }
        return klijenti;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "Ime,Prezime,JMBG,Adresa,BrojTelefona";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + jmbg + "', '" + adresa + "', '" + brojTelefona + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "KlijentID=" + klijentId;
    }

    @Override
    public String vratiKriterijumPretrage() {
        return "Ime like '%" + kriterijumPretrage + "%' or Prezime like '%" + kriterijumPretrage + "%' or JMBG like '%" + kriterijumPretrage + "%' or "
                + "Adresa like '%" + kriterijumPretrage + "%' or brojTelefona like '%" + kriterijumPretrage + "%'";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "BrojTelefona=" + brojTelefona + ", Adresa='" + adresa + "'";
    }
}
