/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import config.Konfiguracija;
import domen.DodatnaOpcija;
import domen.Klijent;
import domen.Korisnik;
import domen.Marka;
import domen.Model;
import domen.Vozilo;
import domen.Zaduzenje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import util.Operation;
import util.ResponseStatus;
import java.lang.reflect.Type;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class Controller {

    private static Controller instance;
    private final Map<String, Object> map;
    private FMainController fMainController;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    Gson gson;

    private Controller() {
        map = new HashMap<>();
        //gson = new Gson();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    private void otvoriSocket() {
        try {
            socket = new Socket(Konfiguracija.getInstance().getProperty("ip"), Integer.parseInt(Konfiguracija.getInstance().getProperty("port")));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greska pri povezivanju sa serverom");
        }
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public void setfMainController(FMainController fMainController) {
        this.fMainController = fMainController;
    }

    public Korisnik login(String username, String password) {
        otvoriSocket();
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(username);
        k.setKorisnickaSifra(password);
        RequestObject request = new RequestObject(Operation.OPERATION_LOGIN, k);
        Controller.getInstance().sendRequest(request);

        Korisnik korisnik = gson.fromJson(readResponse(), Korisnik.class);
        System.out.println(korisnik);
        return korisnik;
    }

    public List<Marka> vratiMarke() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_MARKE, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Marka>>() {
        }.getType();
        ArrayList<Marka> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    public List<Model> vratiModele(Marka marka) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_MODELE_ZA_MARKU, marka);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Model>>() {
        }.getType();
        ArrayList<Model> lista = gson.fromJson(readResponse(), listType);
        System.out.println(lista.get(0));
        return lista;
    }

    public List<Vozilo> vratiVozila() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_VOZILA, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Vozilo>>() {
        }.getType();
        ArrayList<Vozilo> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    public Model vratiModel(int id) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_MODEL_PO_ID, id);
        Controller.getInstance().sendRequest(request);
        Model model = gson.fromJson(readResponse(), Model.class);
        return model;
    }

    public Marka vratiMarku(int id) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_MARKU_PO_ID, id);
        Controller.getInstance().sendRequest(request);
        Marka marka = gson.fromJson(readResponse(), Marka.class);
        return marka;
    }

    public List<Vozilo> vratiVozila(Vozilo voziloKriterijumPretrage) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_VOZILA_PO_KRITERIJUMU, voziloKriterijumPretrage);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Vozilo>>() {
        }.getType();
        ArrayList<Vozilo> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void postaviTrenutnoIzabranoVozilo(Vozilo v) {
        map.put("izabranoVozilo", v);
    }

    public boolean sacuvajVozilo(Vozilo v) {
        RequestObject request = new RequestObject(Operation.OPERATION_SACUVAJ_VOZILO, v);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    public boolean izmeniVozilo(Vozilo v) {
        RequestObject request = new RequestObject(Operation.OPERATION_IZMENI_VOZILO, v);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    public boolean sacuvajKlijenta(Klijent k) {
        RequestObject request = new RequestObject(Operation.OPERATION_SACUVAJ_KLIJENTA, k);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    public List<Klijent> vratiKlijente() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_KLIJENTE, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Klijent>>() {
        }.getType();
        ArrayList<Klijent> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    public List<Klijent> vratiKlijente(String kriterijumPretrage) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_KLIJENTE_PO_KRITERIJUMU, kriterijumPretrage);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Klijent>>() {
        }.getType();
        ArrayList<Klijent> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    public void postaviTrenutnoIzabranogKlijenta(Klijent k) {
        map.put("izabraniKlijent", k);
    }

    public boolean izmeniKlijenta(Klijent k) {
        RequestObject request = new RequestObject(Operation.OPERATION_IZMENI_KLIJENTA, k);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    DodatnaOpcija vratiDodatnuOpciju(String nazivDodatneOpcije) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_DODATNU_OPCIJU, nazivDodatneOpcije);
        Controller.getInstance().sendRequest(request);
        DodatnaOpcija dodatnaOpcija = gson.fromJson(readResponse(), DodatnaOpcija.class);
        return dodatnaOpcija;
    }

    List<Zaduzenje> vratiZaduzenja() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_ZADUZENJA, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Zaduzenje>>() {
        }.getType();
        ArrayList<Zaduzenje> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    List<Zaduzenje> vratiZaduzenja(String kriterijum) {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_ZADUZENJA_PO_KRITERIJUMU, kriterijum);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Zaduzenje>>() {
        }.getType();
        ArrayList<Zaduzenje> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    boolean sacuvajZaduzenje(Zaduzenje zaduzenje) {
        RequestObject request = new RequestObject(Operation.OPERATION_SACUVAJ_ZADUZENJE, zaduzenje);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    void postaviTrenutnoIzabranoZaduzenje(Zaduzenje z) {
        map.put("izabranoZaduzenje", z);
    }

    List<Korisnik> vratiKorisnike() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_KORISNIKE, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Korisnik>>() {
        }.getType();
        ArrayList<Korisnik> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    boolean razduziZaduzenje(Zaduzenje z) {
        RequestObject request = new RequestObject(Operation.OPERATION_RAZDUZI_ZADUZENJE, z);
        Controller.getInstance().sendRequest(request);
        boolean uspesnost = gson.fromJson(readResponse(), boolean.class);
        return uspesnost;
    }

    public List<Vozilo> vratiSlobodnaVozila() {
        RequestObject request = new RequestObject(Operation.OPERATION_VRATI_SLOBODNA_VOZILA, null);
        Controller.getInstance().sendRequest(request);
        Type listType = new TypeToken<ArrayList<Vozilo>>() {
        }.getType();
        ArrayList<Vozilo> lista = gson.fromJson(readResponse(), listType);
        return lista;
    }

    void sendRequest(RequestObject requestObject) {
        out.println(gson.toJson(requestObject));
    }

    JsonElement readResponse() {
        String jsonResponse = null;
        JsonElement jsonElementData = null;
        try {
            jsonResponse = in.readLine();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonElement responseStatusJSON = jsonObject.get("status");
            ResponseStatus rs = gson.fromJson(responseStatusJSON, ResponseStatus.class);

            if (rs == ResponseStatus.SUCCESS) {
                jsonElementData = jsonObject.get("data");
            }
            if (rs == ResponseStatus.ERROR) {
                jsonElementData = jsonObject.get("errorMessage");
            }
        } catch (SocketException se) {
            JOptionPane.showMessageDialog(null, "Server je zaustavljen!", "Greska pri komunikaciji sa serverom", JOptionPane.ERROR_MESSAGE);
            fMainController.zatvoriSve();
            throw new RuntimeException("Server je zaustavljen!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(jsonElementData);
        return jsonElementData;
    }

}
