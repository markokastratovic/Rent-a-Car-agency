/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.DodatnaOpcija;
import domen.Klijent;
import domen.Korisnik;
import domen.Vozilo;
import domen.Zaduzenje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.form.FZaduzenje;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class ZaduzenjeController {

    FZaduzenje fZaduzenje;

    public ZaduzenjeController() {
    }

    public void otvoriFZaduzenje(JFrame parent, FormMode formMode) {
        fZaduzenje = new FZaduzenje(parent, true);
        fZaduzenje.getCheckFullKasko().addActionListener(new OsluslivacCheckBoxFullKasko());
        fZaduzenje.getBtnSacuvajZaduzenje().addActionListener(new OsluskivacSacuvajZaduzenje());
        fZaduzenje.getBtnRazduziZaduzenje().addActionListener(new OsluskivacRazduzi());

        napuniComboVozila();
        napuniComboZaduzioRazduzio();
        napuniComboKlijenti();

        prepareForm(formMode);

        fZaduzenje.setVisible(true);
    }

    private void prepareForm(FormMode formMode) {
        fZaduzenje.getTxtZaduzenjeID().setEnabled(false);
        fZaduzenje.getTxtDatumOd().setEnabled(false);
        fZaduzenje.getTxtDatumDo().setEnabled(false);
        fZaduzenje.getTxtUkupnaCena().setEnabled(false);
        fZaduzenje.getCmbRazduzio().setEnabled(false);
        fZaduzenje.getCmbZaduzio().setEnabled(false);
        if (formMode == FormMode.FORM_UPDATE) {
            fZaduzenje.getCmbVozilo().setEnabled(false);
            fZaduzenje.getCmbKlijent().setEnabled(false);
            fZaduzenje.getCheckDecijeSediste().setEnabled(false);
            fZaduzenje.getCheckGPS().setEnabled(false);
            fZaduzenje.getCheckKradja().setEnabled(false);
            fZaduzenje.getCheckSteta().setEnabled(false);
            fZaduzenje.getCheckMedjunarodno().setEnabled(false);
            fZaduzenje.getCheckFullKasko().setEnabled(false);
            fZaduzenje.getBtnSacuvajZaduzenje().setVisible(false);
            prikaziIzabranoZaduzenje();
        }
        if (formMode == FormMode.FORM_ADD) {
            fZaduzenje.getCmbVozilo().setEnabled(true);
            fZaduzenje.getCmbKlijent().setEnabled(true);
            fZaduzenje.getCheckGPS().setEnabled(true);
            fZaduzenje.getCheckKradja().setEnabled(true);
            fZaduzenje.getCheckSteta().setEnabled(true);
            fZaduzenje.getCheckMedjunarodno().setEnabled(true);
            fZaduzenje.getCheckFullKasko().setEnabled(true);
            fZaduzenje.getBtnRazduziZaduzenje().setVisible(false);

            napuniComboVozilaSlobodnaVozila();

            fZaduzenje.getCmbZaduzio().setSelectedItem(Controller.getInstance().getMap().get("ulogovaniKorisnik"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            fZaduzenje.getTxtDatumOd().setText(sdf.format(new Date()));

        }
    }

    private void napuniComboVozilaSlobodnaVozila() {
        List<Vozilo> listaVozila = Controller.getInstance().vratiSlobodnaVozila();
        fZaduzenje.getCmbVozilo().removeAllItems();
        for (Vozilo vozilo : listaVozila) {
            fZaduzenje.getCmbVozilo().addItem(vozilo);
        }
    }

    private void napuniComboVozila() {
        List<Vozilo> listaVozila = Controller.getInstance().vratiVozila();
        fZaduzenje.getCmbVozilo().removeAllItems();
        for (Vozilo vozilo : listaVozila) {
            fZaduzenje.getCmbVozilo().addItem(vozilo);
        }
    }

    private void napuniComboZaduzioRazduzio() {
        List<Korisnik> listaKorisnika = Controller.getInstance().vratiKorisnike();
        fZaduzenje.getCmbZaduzio().removeAllItems();
        fZaduzenje.getCmbRazduzio().removeAllItems();
        for (Korisnik korisnik : listaKorisnika) {
            fZaduzenje.getCmbZaduzio().addItem(korisnik);
            fZaduzenje.getCmbRazduzio().addItem(korisnik);
        }
        fZaduzenje.getCmbZaduzio().setSelectedItem(null);
        fZaduzenje.getCmbRazduzio().setSelectedItem(null);
    }

    private void napuniComboKlijenti() {
        List<Klijent> klijenti = Controller.getInstance().vratiKlijente();
        fZaduzenje.getCmbKlijent().removeAllItems();
        for (Klijent klijent : klijenti) {
            fZaduzenje.getCmbKlijent().addItem(klijent);
        }
    }

    private void prikaziIzabranoZaduzenje() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Zaduzenje z = (Zaduzenje) Controller.getInstance().getMap().get("izabranoZaduzenje");
        if (z == null) {
            return;
        }
        fZaduzenje.getTxtZaduzenjeID().setText(z.getZaduzenjeId() + "");
        fZaduzenje.getTxtDatumOd().setText(sdf.format(z.getDatumOd()));
        if (z.getDatumDo() != null) {
            fZaduzenje.getTxtDatumDo().setText(sdf.format(z.getDatumDo()));
        }
        fZaduzenje.getTxtUkupnaCena().setText(z.getUkupnaCena() + "");
        fZaduzenje.getCmbVozilo().setSelectedItem(z.getVozilo());
        fZaduzenje.getCmbZaduzio().setSelectedItem(z.getZaduzio());
        // if(z.getRazduzio().)
        fZaduzenje.getCmbRazduzio().setSelectedItem(z.getRazduzio());
        fZaduzenje.getCmbKlijent().setSelectedItem(z.getKlijent());
        for (DodatnaOpcija d : z.getDodatneOpcije()) {
            if (d.getNazivOpcije().equals("GPS navigacija")) {
                fZaduzenje.getCheckGPS().setSelected(true);
            }
            if (d.getNazivOpcije().equals("Decije sediste")) {
                fZaduzenje.getCheckDecijeSediste().setSelected(true);
            }
            if (d.getNazivOpcije().equals("Osiguranje od stete")) {
                fZaduzenje.getCheckSteta().setSelected(true);
            }
            if (d.getNazivOpcije().equals("Osiguranje od kradje")) {
                fZaduzenje.getCheckKradja().setSelected(true);
            }
            if (d.getNazivOpcije().equals("Medjunarodno Osiguranje")) {
                fZaduzenje.getCheckMedjunarodno().setSelected(true);
            }
        }

        if (z.getDatumDo() != null) {
            fZaduzenje.getBtnRazduziZaduzenje().setVisible(false);
            fZaduzenje.getBtnSacuvajZaduzenje().setVisible(false);
            fZaduzenje.getCmbRazduzio().setSelectedItem(Controller.getInstance().getMap().get("ulogovaniKorisnik"));
        }

    }

    private class OsluskivacSacuvajZaduzenje implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Date datumOd = new Date();
            Vozilo v = (Vozilo) fZaduzenje.getCmbVozilo().getSelectedItem();
            Klijent k = (Klijent) fZaduzenje.getCmbKlijent().getSelectedItem();
            Korisnik zaduzio = (Korisnik) fZaduzenje.getCmbZaduzio().getSelectedItem();
            ArrayList<DodatnaOpcija> listaOpcija = new ArrayList<>();
            if (fZaduzenje.getCheckGPS().isSelected()) {
                listaOpcija.add(Controller.getInstance().vratiDodatnuOpciju("GPS navigacija"));
            }
            if (fZaduzenje.getCheckDecijeSediste().isSelected()) {
                listaOpcija.add(Controller.getInstance().vratiDodatnuOpciju("Decije sediste"));
            }
            if (fZaduzenje.getCheckKradja().isSelected()) {
                listaOpcija.add(Controller.getInstance().vratiDodatnuOpciju("Osiguranje od kradje"));
            }
            if (fZaduzenje.getCheckSteta().isSelected()) {
                listaOpcija.add(Controller.getInstance().vratiDodatnuOpciju("Osiguranje od stete"));
            }
            if (fZaduzenje.getCheckMedjunarodno().isSelected()) {
                listaOpcija.add(Controller.getInstance().vratiDodatnuOpciju("Medjunarodno Osiguranje"));
            }
            Zaduzenje zaduzenje = new Zaduzenje(-1, datumOd, null, -1, v, k, zaduzio, null, listaOpcija);
            boolean uspesno = Controller.getInstance().sacuvajZaduzenje(zaduzenje);
            fZaduzenje.dispose();
            if (uspesno) {
                JOptionPane.showMessageDialog(fZaduzenje, "Sistem ja zapamtio novo zaduzenje!");
            } else {
                JOptionPane.showMessageDialog(fZaduzenje, "Sistem ne moze da zaduzi vozilo!", "GRESKA!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class OsluslivacCheckBoxFullKasko implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fZaduzenje.getCheckFullKasko().isSelected()) {
                fZaduzenje.getCheckMedjunarodno().setSelected(true);
                fZaduzenje.getCheckKradja().setSelected(true);
                fZaduzenje.getCheckSteta().setSelected(true);
            } else {
                fZaduzenje.getCheckMedjunarodno().setSelected(false);
                fZaduzenje.getCheckKradja().setSelected(false);
                fZaduzenje.getCheckSteta().setSelected(false);
            }
        }
    }

    private class OsluskivacRazduzi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Zaduzenje z = (Zaduzenje) Controller.getInstance().getMap().get("izabranoZaduzenje");
            z.setDatumDo(new Date());
            z.setRazduzio((Korisnik) Controller.getInstance().getMap().get("ulogovaniKorisnik"));
            int brojDana = (int) ((z.getDatumDo().getTime() - z.getDatumOd().getTime()) / 86400000) + 1;
            double ukupnaCena = z.getVozilo().getDnevnaCena() * brojDana;
            for (DodatnaOpcija d : z.getDodatneOpcije()) {
                ukupnaCena += d.getCena();
            }
            z.setUkupnaCena(ukupnaCena);
            boolean uspesno = Controller.getInstance().razduziZaduzenje(z);
            fZaduzenje.dispose();
            if (uspesno) {
                JOptionPane.showMessageDialog(fZaduzenje, "Sistem je uspesno razduzio vozilo!");
                GuiCoordinator.getInstance().osveziFormuPretragaZaduzenja();
            } else {
                JOptionPane.showMessageDialog(fZaduzenje, "Sistem ne moze da razduzi vozilo!", "GRESKA!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
