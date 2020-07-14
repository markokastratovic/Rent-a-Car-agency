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
public class Vozilo extends ApstraktniDomenskiObjekat {

    private int voziloId;
    private String registarskiBroj;
    private String brojSasije;
    private int godiste;
    private int zapreminaMotora;
    private int snaga;
    private double dnevnaCena;
    private Model model;

    public Vozilo() {
    }

    public Vozilo(int voziloId, String registarskiBroj, String brojSasije, int godiste, int zapreminaMotora, int snaga, double dnevnaCena, Model model) {
        this.voziloId = voziloId;
        this.registarskiBroj = registarskiBroj;
        this.brojSasije = brojSasije;
        this.godiste = godiste;
        this.zapreminaMotora = zapreminaMotora;
        this.snaga = snaga;
        this.dnevnaCena = dnevnaCena;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getVoziloId() {
        return voziloId;
    }

    public void setVoziloId(int voziloId) {
        this.voziloId = voziloId;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getBrojSasije() {
        return brojSasije;
    }

    public void setBrojSasije(String brojSasije) {
        this.brojSasije = brojSasije;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getZapreminaMotora() {
        return zapreminaMotora;
    }

    public void setZapreminaMotora(int zapreminaMotora) {
        this.zapreminaMotora = zapreminaMotora;
    }

    public int getSnaga() {
        return snaga;
    }

    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

    public double getDnevnaCena() {
        return dnevnaCena;
    }

    public void setDnevnaCena(double dnevnaCena) {
        this.dnevnaCena = dnevnaCena;
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
        final Vozilo other = (Vozilo) obj;
        if (this.voziloId != other.voziloId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return model.getMarka().getNazivMarke() + " " + model.getNazivModela() + " " + godiste + " " + registarskiBroj;
    }

    @Override
    public String vratiNazivTabele() {
        return "Vozilo";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> vozila = new ArrayList<>();
        while (rs.next()) {
            int voziloID = rs.getInt(1);
            String registarskiBroj = rs.getString(2);
            String brojSasije = rs.getString(3);
            int godiste = rs.getInt(4);
            int zapreminaMotora = rs.getInt(5);
            int snaga = rs.getInt(6);
            Double dnevnaCena = rs.getDouble(7);

            int markaId = rs.getInt("MarkaID");
            String nazivMarke = rs.getString("NazivMarke");
            int modelID = rs.getInt("ModelID");
            String modelNaziv = rs.getString("NazivModela");
            Marka marka = new Marka(markaId, nazivMarke);
            Model model = new Model(modelID, modelNaziv, godiste, marka);

            Vozilo v = new Vozilo(voziloID, registarskiBroj, brojSasije, godiste, zapreminaMotora, snaga, dnevnaCena, model);
            vozila.add(v);
        }
        return vozila;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "RegistarskiBroj,BrojSasije,Godiste,ZapreminaMotora,Snaga,DnevnaCena,ModelID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + registarskiBroj + "', '" + brojSasije + "', " + godiste + ", " + zapreminaMotora + ", " + snaga + ", " + dnevnaCena + ", " + model.getModelId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "VoziloID=" + voziloId;
    }

    @Override
    public String vratiKriterijumPretrage() {
        String kriterijum = "";
        if (voziloId != -1) {
            kriterijum += "VoziloID like '%" + voziloId + "%' AND ";
        }
        kriterijum += "RegistarskiBroj like'%" + registarskiBroj + "%' AND ";
        kriterijum += "BrojSasije like'%" + brojSasije + "%' AND ";
        if (godiste != -1) {
            kriterijum += "Godiste like'%" + godiste + "%' AND ";
        }
        kriterijum += "m.ModelID like'%" + model.getModelId() + "%'";
        return kriterijum;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "RegistarskiBroj='" + registarskiBroj + "', DnevnaCena=" + dnevnaCena;
    }

}
