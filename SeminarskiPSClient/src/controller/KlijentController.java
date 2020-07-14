/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Klijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ui.form.FKlijent;
import ui.form.FMain;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class KlijentController {

    FKlijent fKlijent;

    public KlijentController() {
    }

    void otvoriFKlijent(FMain parent, FormMode formMode) {
        fKlijent = new FKlijent(parent, true, formMode);
        fKlijent.getBtnOmoguciIzmenu().addActionListener(new OsluskivacOmoguciIzmene());
        fKlijent.getBtnSacuvaj().addActionListener(new OsluskivacSacuvaj());
        fKlijent.getBtnSacuvajPromene().addActionListener(new OsluskivacSacuvajPromene());
        prepareForm(formMode);
        fKlijent.setVisible(true);
    }

    private void prepareForm(FormMode formMode) {
        fKlijent.getTxtKlijentID().setEnabled(false);

        if (formMode.equals(FormMode.FORM_VIEW)) {
            fKlijent.getTxtAdresa().setEnabled(false);
            fKlijent.getTxtBrojTelefona().setEnabled(false);
            fKlijent.getTxtIme().setEnabled(false);
            fKlijent.getTxtJMBG().setEnabled(false);
            fKlijent.getTxtPrezime().setEnabled(false);

            fKlijent.getBtnOmoguciIzmenu().setVisible(true);
            fKlijent.getBtnSacuvaj().setVisible(false);
            fKlijent.getBtnSacuvajPromene().setVisible(false);
            prikaziIzabranogKlijenta();
        }

        if (formMode.equals(FormMode.FORM_ADD)) {
            fKlijent.getTxtAdresa().setEnabled(true);
            fKlijent.getTxtBrojTelefona().setEnabled(true);
            fKlijent.getTxtIme().setEnabled(true);
            fKlijent.getTxtJMBG().setEnabled(true);
            fKlijent.getTxtPrezime().setEnabled(true);

            fKlijent.getBtnOmoguciIzmenu().setVisible(false);
            fKlijent.getBtnSacuvaj().setVisible(true);
            fKlijent.getBtnSacuvajPromene().setVisible(false);

        }

        if (formMode.equals(FormMode.FORM_UPDATE)) {
            fKlijent.getTxtAdresa().setEnabled(true);
            fKlijent.getTxtBrojTelefona().setEnabled(true);
            fKlijent.getTxtIme().setEnabled(false);
            fKlijent.getTxtJMBG().setEnabled(false);
            fKlijent.getTxtPrezime().setEnabled(false);

            fKlijent.getBtnOmoguciIzmenu().setVisible(false);
            fKlijent.getBtnSacuvaj().setVisible(false);
            fKlijent.getBtnSacuvajPromene().setVisible(true);
        }

    }

    private void prikaziIzabranogKlijenta() {
        Klijent k = (Klijent) Controller.getInstance().getMap().get("izabraniKlijent");
        fKlijent.getTxtKlijentID().setText(k.getKlijentId() + "");
        fKlijent.getTxtAdresa().setText(k.getAdresa());
        fKlijent.getTxtBrojTelefona().setText(k.getBrojTelefona());
        fKlijent.getTxtIme().setText(k.getIme());
        fKlijent.getTxtPrezime().setText(k.getPrezime());
        fKlijent.getTxtJMBG().setText(k.getJmbg());

    }

    private class OsluskivacSacuvaj implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String adresa = fKlijent.getTxtAdresa().getText();
            String brojTelefona = fKlijent.getTxtBrojTelefona().getText();
            String ime = fKlijent.getTxtIme().getText();
            String jmbg = fKlijent.getTxtJMBG().getText();
            String prezime = fKlijent.getTxtPrezime().getText();
            Klijent k = new Klijent(0, ime, prezime, jmbg, adresa, brojTelefona);
            if (ime.equals("") || prezime.equals("") || jmbg.equals("")) {
                JOptionPane.showMessageDialog(fKlijent, "Ime,Prezime i JMBG moraju biti popunjeni!", "GRESKA!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean uspesno = Controller.getInstance().sacuvajKlijenta(k);
            fKlijent.dispose();
            if (uspesno) {
                JOptionPane.showMessageDialog(fKlijent, "Sistem je zapamtio novog klijenta!");
            } else {
                JOptionPane.showMessageDialog(fKlijent, "Sistem ne moze da zapamti novog klijenta!", "GRESKA!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class OsluskivacSacuvajPromene implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Klijent k = (Klijent) Controller.getInstance().getMap().get("izabraniKlijent");
            k.setAdresa(fKlijent.getTxtAdresa().getText());
            k.setBrojTelefona(fKlijent.getTxtBrojTelefona().getText());

            boolean izmenjeno = Controller.getInstance().izmeniKlijenta(k);
            fKlijent.dispose();
            if (izmenjeno) {
                JOptionPane.showMessageDialog(fKlijent, "Sistem je zapamtio klijenta!");
                GuiCoordinator.getInstance().osveziFormuPretragaKlijenata();
            } else {
                JOptionPane.showMessageDialog(fKlijent, "Sistem ne moze da zapamti klijenta!", "Greska!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class OsluskivacOmoguciIzmene implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prepareForm(FormMode.FORM_UPDATE);
        }
    }

}
