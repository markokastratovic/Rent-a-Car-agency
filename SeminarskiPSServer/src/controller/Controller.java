/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.ApstraktniDomenskiObjekat;
import domen.DodatnaOpcija;
import domen.Klijent;
import domen.Korisnik;
import domen.Marka;
import domen.Model;
import domen.Vozilo;
import domen.Zaduzenje;
import java.util.List;
import server.Server;
import so.ApstraktnaGenerickaOperacija;
import so.dodatnaopcija.VratiDodatnuOpcijuSO;
import so.klijent.IzmeniKlijentaSO;
import so.klijent.PretraziKlijenteSO;
import so.klijent.SacuvajKlijentaSO;
import so.klijent.VratiSveKlijenteSO;
import so.korisnik.PrijavljivanjeSO;
import so.marka.VratiMarkuPoIdSO;
import so.marka.VratiSveMarkeSO;
import so.model.VratiModelPoIdSO;
import so.model.VratiModelePoMarkiSO;
import so.vozilo.IzmeniVoziloSO;
import so.vozilo.PretraziVozilaSO;
import so.vozilo.SacuvajVoziloSO;
import so.vozilo.VratiSlobodnaVozilaSO;
import so.vozilo.VratiSvaVozilaSO;
import so.zaduzenje.PretraziZaduzenjaSO;
import so.zaduzenje.RazduziZaduzenjeSO;
import so.zaduzenje.SacuvajZaduzenjeSO;
import so.zaduzenje.VratiSvaZaduzenjaSO;

public class Controller {

    private static Controller instance;
    private Server server;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void zaustaviServer() {
        if (server != null) {
            server.zaustaviServer();
        }
    }

    public Korisnik login(String username, String password) {
        try {
            List<Korisnik> listaKorisnika = Controller.getInstance().vratiKorisnike();
            for (Korisnik korisnik : listaKorisnika) {
                if (korisnik.getKorisnickoIme().equals(username)) {
                    if (korisnik.getKorisnickaSifra().equals(password)) {
                        return korisnik;
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<ApstraktniDomenskiObjekat> vratiMarke() throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiSveMarkeSO();
        so.templateExecute(new Marka());
        return ((VratiSveMarkeSO) so).getLista();
    }

    public List<ApstraktniDomenskiObjekat> vratiModele(Marka marka) throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiModelePoMarkiSO();
        Model m = new Model();
        m.setMarka(marka);
        so.templateExecute(m);
        return ((VratiModelePoMarkiSO) so).getLista();
    }

    public List<ApstraktniDomenskiObjekat> vratiVozila() throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiSvaVozilaSO();
        so.templateExecute(new Vozilo());
        return ((VratiSvaVozilaSO) so).getLista();
    }

    public ApstraktniDomenskiObjekat vratiModel(int id) throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiModelPoIdSO();
        Model m = new Model();
        m.setModelId(id);
        so.templateExecute(m);
        return ((VratiModelPoIdSO) so).getModel();
    }

    public ApstraktniDomenskiObjekat vratiMarku(int id) throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiMarkuPoIdSO();
        Marka m = new Marka();
        m.setMarkaId(id);
        so.templateExecute(m);
        return ((VratiMarkuPoIdSO) so).getMarka();
    }

    public List<ApstraktniDomenskiObjekat> vratiVozila(Vozilo voziloKriterijumPretrage) throws Exception {
        ApstraktnaGenerickaOperacija so = new PretraziVozilaSO();
        so.templateExecute(voziloKriterijumPretrage);
        return ((PretraziVozilaSO) so).getLista();
    }

    public boolean sacuvajVozilo(Vozilo v) {
        try {
            ApstraktnaGenerickaOperacija so = new SacuvajVoziloSO();
            so.templateExecute(v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean izmeniVozilo(Vozilo v) {
        try {
            ApstraktnaGenerickaOperacija so = new IzmeniVoziloSO();
            so.templateExecute(v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sacuvajKlijenta(Klijent k) throws Exception {
        try {
            ApstraktnaGenerickaOperacija so = new SacuvajKlijentaSO();
            so.templateExecute(k);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ApstraktniDomenskiObjekat> vratiKlijente() throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiSveKlijenteSO();
        so.templateExecute(new Klijent());
        return ((VratiSveKlijenteSO) so).getLista();
    }

    public List<ApstraktniDomenskiObjekat> vratiKlijente(String kriterijumPretrage) throws Exception {
        ApstraktnaGenerickaOperacija so = new PretraziKlijenteSO();
        Klijent k = new Klijent();
        k.setKriterijumPretrage(kriterijumPretrage);
        so.templateExecute(k);
        return ((PretraziKlijenteSO) so).getLista();
    }

    public boolean izmeniKlijenta(Klijent k) {
        try {
            ApstraktnaGenerickaOperacija so = new IzmeniKlijentaSO();
            so.templateExecute(k);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApstraktniDomenskiObjekat vratiDodatnuOpciju(String nazivDodatneOpcije) throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiDodatnuOpcijuSO();
        DodatnaOpcija d = new DodatnaOpcija();
        d.setNazivOpcije(nazivDodatneOpcije);
        so.templateExecute(d);
        return ((VratiDodatnuOpcijuSO) so).getDodatnaOpcija();
    }

    public List<ApstraktniDomenskiObjekat> vratiZaduzenja() throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiSvaZaduzenjaSO();
        so.templateExecute(new Zaduzenje());
        return ((VratiSvaZaduzenjaSO) so).getLista();
    }

    public List<ApstraktniDomenskiObjekat> vratiZaduzenja(String kriterijum) throws Exception {
        ApstraktnaGenerickaOperacija so = new PretraziZaduzenjaSO();
        Zaduzenje z = new Zaduzenje();
        z.setKriterijumPretrage(kriterijum);
        so.templateExecute(z);
        return ((PretraziZaduzenjaSO) so).getLista();
    }

    public boolean sacuvajZaduzenje(Zaduzenje zaduzenje) {
        try {
            ApstraktnaGenerickaOperacija so = new SacuvajZaduzenjeSO();
            so.templateExecute(zaduzenje);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Korisnik> vratiKorisnike() throws Exception {
        ApstraktnaGenerickaOperacija so = new PrijavljivanjeSO();
        so.templateExecute(new Korisnik());
        return ((PrijavljivanjeSO) so).getListaKorisnika();
    }

    public boolean razduziZaduzenje(Zaduzenje z) {
        try {
            ApstraktnaGenerickaOperacija so = new RazduziZaduzenjeSO();
            so.templateExecute(z);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ApstraktniDomenskiObjekat> vratiSlobodnaVozila() throws Exception {
        ApstraktnaGenerickaOperacija so = new VratiSlobodnaVozilaSO();
        so.templateExecute(new Vozilo());
        return ((VratiSlobodnaVozilaSO) so).getLista();
    }

}
