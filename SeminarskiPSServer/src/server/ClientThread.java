/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.Controller;
import domen.Klijent;
import domen.Korisnik;
import domen.Marka;
import domen.Vozilo;
import domen.Zaduzenje;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;
import util.Operation;

/**
 *
 * @author Marko
 */
public class ClientThread extends Thread {

    Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void handleRequest() throws Exception {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            try {
                ResponseObject response = new ResponseObject();
                String json = in.readLine();
                System.out.println(json);
                RequestObject request = gson.fromJson(json, RequestObject.class);

                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                JsonElement dataJSON = jsonObject.get("data");
                System.out.println(dataJSON);

                int id;
                String kriterijum;
                Vozilo voziloKriterijum;
                Klijent klijent;
                Zaduzenje zaduzenje;
                switch (request.getOperation()) {
                    case Operation.OPERATION_LOGIN:
                        Korisnik k = gson.fromJson(dataJSON, Korisnik.class);
                        k = Controller.getInstance().login(k.getKorisnickoIme(), k.getKorisnickaSifra());
                        System.out.println(k);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(k);
                        break;

                    case Operation.OPERATION_VRATI_MARKE:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiMarke());
                        break;

                    case Operation.OPERATION_VRATI_MODELE_ZA_MARKU:
                        Marka marka = gson.fromJson(dataJSON, Marka.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiModele(marka));
                        break;

                    case Operation.OPERATION_VRATI_VOZILA:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiVozila());
                        break;

                    case Operation.OPERATION_VRATI_MODEL_PO_ID:
                        id = gson.fromJson(dataJSON, Integer.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiModel(id));
                        break;

                    case Operation.OPERATION_VRATI_MARKU_PO_ID:
                        id = gson.fromJson(dataJSON, Integer.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiModel(id));
                        break;

                    case Operation.OPERATION_VRATI_VOZILA_PO_KRITERIJUMU:
                        voziloKriterijum = gson.fromJson(dataJSON, Vozilo.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiVozila(voziloKriterijum));
                        break;

                    case Operation.OPERATION_SACUVAJ_VOZILO:
                        voziloKriterijum = gson.fromJson(dataJSON, Vozilo.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().sacuvajVozilo(voziloKriterijum));
                        break;

                    case Operation.OPERATION_IZMENI_VOZILO:
                        voziloKriterijum = gson.fromJson(dataJSON, Vozilo.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().izmeniVozilo(voziloKriterijum));
                        break;

                    case Operation.OPERATION_SACUVAJ_KLIJENTA:
                        klijent = gson.fromJson(dataJSON, Klijent.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().sacuvajKlijenta(klijent));
                        break;

                    case Operation.OPERATION_IZMENI_KLIJENTA:
                        klijent = gson.fromJson(dataJSON, Klijent.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().izmeniKlijenta(klijent));
                        break;

                    case Operation.OPERATION_VRATI_KLIJENTE:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiKlijente());
                        break;

                    case Operation.OPERATION_VRATI_KLIJENTE_PO_KRITERIJUMU:
                        kriterijum = gson.fromJson(dataJSON, String.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiKlijente(kriterijum));
                        break;

                    case Operation.OPERATION_VRATI_DODATNU_OPCIJU:
                        kriterijum = gson.fromJson(dataJSON, String.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiDodatnuOpciju(kriterijum));
                        break;

                    case Operation.OPERATION_VRATI_ZADUZENJA:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiZaduzenja());
                        break;

                    case Operation.OPERATION_VRATI_ZADUZENJA_PO_KRITERIJUMU:
                        kriterijum = gson.fromJson(dataJSON, String.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiZaduzenja(kriterijum));
                        break;

                    case Operation.OPERATION_SACUVAJ_ZADUZENJE:
                        zaduzenje = gson.fromJson(dataJSON, Zaduzenje.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().sacuvajZaduzenje(zaduzenje));
                        break;

                    case Operation.OPERATION_RAZDUZI_ZADUZENJE:
                        zaduzenje = gson.fromJson(dataJSON, Zaduzenje.class);
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().razduziZaduzenje(zaduzenje));
                        break;

                    case Operation.OPERATION_VRATI_KORISNIKE:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiKorisnike());
                        break;

                    case Operation.OPERATION_VRATI_SLOBODNA_VOZILA:
                        response.setStatus(ResponseStatus.SUCCESS);
                        response.setData(Controller.getInstance().vratiSlobodnaVozila());
                        break;

                }
                out.println(gson.toJson(response));
                System.out.println(gson.toJson(response));
            } catch (SocketException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

}
