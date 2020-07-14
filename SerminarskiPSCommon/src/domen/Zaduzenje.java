/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marko
 */
public class Zaduzenje extends ApstraktniDomenskiObjekat {

    private int zaduzenjeId;
    private Date datumOd;
    private Date datumDo;
    private double ukupnaCena;
    private Vozilo vozilo;
    private Klijent klijent;
    private Korisnik zaduzio;
    private Korisnik razduzio;
    private List<DodatnaOpcija> dodatneOpcije;
    private String kriterijumPretrage;

    public Zaduzenje() {
    }

    public Zaduzenje(int zaduzenjeId, Date datumOd, Vozilo vozilo, Klijent klijent, Korisnik zaduzio) {
        this.zaduzenjeId = zaduzenjeId;
        this.datumOd = datumOd;
        this.vozilo = vozilo;
        this.klijent = klijent;
        this.zaduzio = zaduzio;
        dodatneOpcije = new ArrayList<>();
    }

    public Zaduzenje(int zaduzenjeId, Date datumOd, Date datumDo, double ukupnaCena, Vozilo vozilo, Klijent klijent, Korisnik zaduzio, Korisnik razduzio, List<DodatnaOpcija> dodatneOpcije) {
        this.zaduzenjeId = zaduzenjeId;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupnaCena = ukupnaCena;
        this.vozilo = vozilo;
        this.klijent = klijent;
        this.zaduzio = zaduzio;
        this.razduzio = razduzio;
        this.dodatneOpcije = dodatneOpcije;
    }

    public int getZaduzenjeId() {
        return zaduzenjeId;
    }

    public void setZaduzenjeId(int zaduzenjeId) {
        this.zaduzenjeId = zaduzenjeId;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Korisnik getZaduzio() {
        return zaduzio;
    }

    public void setZaduzio(Korisnik zaduzio) {
        this.zaduzio = zaduzio;
    }

    public Korisnik getRazduzio() {
        return razduzio;
    }

    public void setRazduzio(Korisnik razduzio) {
        this.razduzio = razduzio;
    }

    public List<DodatnaOpcija> getDodatneOpcije() {
        return dodatneOpcije;
    }

    public void setDodatneOpcije(List<DodatnaOpcija> dodatneOpcije) {
        this.dodatneOpcije = dodatneOpcije;
    }

    public String getKriterijumPretrage() {
        return kriterijumPretrage;
    }

    public void setKriterijumPretrage(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaduzenje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> zaduzenja = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ZaduzenjeID");
            Date datumOd = rs.getDate("DatumOd");
            Date datumDo = rs.getDate("DatumDo");
            double cena = rs.getDouble("UkupnaCena");

            int klijentId = rs.getInt("KlijentID");
            String ime = rs.getString("k.Ime");
            String prezime = rs.getString("k.Prezime");
            String jmbg = rs.getString("k.JMBG");
            String adresa = rs.getString("k.Adresa");
            String brojTelefona = rs.getString("k.BrojTelefona");
            Klijent klijent = new Klijent(klijentId, ime, prezime, jmbg, adresa, brojTelefona);

            int voziloID = rs.getInt("VoziloID");
            String registarskiBroj = rs.getString("RegistarskiBroj");
            String brojSasije = rs.getString("BrojSasije");
            int godiste = rs.getInt("Godiste");
            int zapreminaMotora = rs.getInt("ZapreminaMotora");
            int snaga = rs.getInt("Snaga");
            Double dnevnaCena = rs.getDouble("DnevnaCena");
            int markaId = rs.getInt("MarkaID");
            String nazivMarke = rs.getString("NazivMarke");
            int modelID = rs.getInt("ModelID");
            String modelNaziv = rs.getString("NazivModela");
            Marka marka = new Marka(markaId, nazivMarke);
            Model model = new Model(modelID, modelNaziv, godiste, marka);
            Vozilo v = new Vozilo(voziloID, registarskiBroj, brojSasije, godiste, zapreminaMotora, snaga, dnevnaCena, model);

            int zaduzioID = rs.getInt("ZaduzioID");
            String zime = rs.getString("zaduzio.Ime");
            String zprezime = rs.getString("zaduzio.Prezime");
            String zjmbg = rs.getString("zaduzio.JMBG");
            String zadresa = rs.getString("zaduzio.Adresa");
            String zbrojTelefona = rs.getString("zaduzio.BrojTelefona");
            String zusername = rs.getString("zaduzio.KorisnickoIme");
            String zpass = rs.getString("zaduzio.KorisnickaSifra");
            Korisnik zaduzio = new Korisnik(zaduzioID, zusername, zpass, zime, zprezime, zjmbg, zadresa, zbrojTelefona);

            int razduzioID = rs.getInt("RazduzioID");
            String rime = rs.getString("razduzio.Ime");
            String rprezime = rs.getString("razduzio.Prezime");
            String rjmbg = rs.getString("razduzio.JMBG");
            String radresa = rs.getString("razduzio.Adresa");
            String rbrojTelefona = rs.getString("razduzio.BrojTelefona");
            String rusername = rs.getString("razduzio.KorisnickoIme");
            String rpass = rs.getString("razduzio.KorisnickaSifra");
            Korisnik raduzio = new Korisnik(razduzioID, rusername, rpass, rime, rprezime, rjmbg, radresa, rbrojTelefona);

            Zaduzenje zaduzenje = new Zaduzenje(id, datumOd, datumDo, cena, v, klijent, zaduzio, raduzio, null);
            zaduzenja.add(zaduzenje);
        }
        return zaduzenja;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "DatumOd,KlijentID,VoziloID,ZaduzioID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + (new java.sql.Date(datumOd.getTime())) + "', " + klijent.getKlijentId() + ", " + vozilo.getVoziloId() + ", " + zaduzio.getKorisnikId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "ZaduzenjeID=" + zaduzenjeId;
    }

    @Override
    public String vratiKriterijumPretrage() {
        return "z.DatumOd='" + kriterijumPretrage + "' or z.DatumDo='" + kriterijumPretrage + "' or k.Ime='" + kriterijumPretrage + "' or k.Prezime='" + kriterijumPretrage + "' or "
                + "zaduzio.Ime= '" + kriterijumPretrage + "' or zaduzio.Prezime= '" + kriterijumPretrage + "' or razduzio.Ime= '" + kriterijumPretrage + "' or razduzio.Prezime= '" + kriterijumPretrage + "' "
                + "or v.RegistarskiBroj like '" + kriterijumPretrage + "' or m.NazivModela= '" + kriterijumPretrage + "' or ma.NazivMarke= '" + kriterijumPretrage + "' order by z.ZaduzenjeID DESC";
    }

    @Override
    public boolean slozenObjekat() {
        return true;
    }

    @Override
    public void veziSlabObjekatZaJakObjekat(List<ApstraktniDomenskiObjekat> listaSlabih) {
        if (dodatneOpcije == null) {
            dodatneOpcije = new ArrayList<>();
        }
        for (ApstraktniDomenskiObjekat slab : listaSlabih) {
            DodatnaOpcija d = ((OpcijaZaduzenje) slab).getDodatnaOpcija();
            dodatneOpcije.add(d);
        }
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuSlabih() {
        List<ApstraktniDomenskiObjekat> vrati = new ArrayList<>();
        for (DodatnaOpcija dodOp : dodatneOpcije) {
            vrati.add(new OpcijaZaduzenje(dodOp, zaduzenjeId));
        }
        return vrati;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiTipSlabogObjekta() {
        return new OpcijaZaduzenje();
    }

    @Override
    public void postaviID(int id) {
        zaduzenjeId = id;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "DatumDo='" + (new java.sql.Date(datumOd.getTime())) + "',UkupnaCena=" + ukupnaCena + ",RazduzioID=" + razduzio.getKorisnikId();
    }

}
