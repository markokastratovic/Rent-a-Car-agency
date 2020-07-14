/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Marka;
import domen.Model;
import domen.Vozilo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.form.FVozilo;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class VoziloController {

    FVozilo fVozilo;

    public VoziloController() {

    }

    public void otvoriFvozilo(JFrame parent, FormMode formMode) {
        fVozilo = new FVozilo(parent, true, formMode);
        fVozilo.getBtnSacuvajVozilo().addActionListener(new OsluskivacSacuvajVozilo());
        fVozilo.getBtnSacuvajIzmene().addActionListener(new OsluskivacIzmeniVozilo());
        fVozilo.getBtnOmoguciIzmenu().addActionListener(new OsluskivacOmoguciIzmenu());
        fVozilo.getCmbMarka().addActionListener(new OsluskivacComboMarka());

        napuniComboMarka();
        napuniComboModeli((Marka) fVozilo.getCmbMarka().getSelectedItem());
        prepareForm(formMode);
        fVozilo.setVisible(true);
    }

    private void prepareForm(FormMode formMode) {
        fVozilo.getTxtVoziloID().setEnabled(false);

        if (formMode.equals(FormMode.FORM_ADD)) {
            fVozilo.getBtnOmoguciIzmenu().setVisible(false);
            fVozilo.getBtnSacuvajIzmene().setVisible(false);
        }

        if (formMode.equals(FormMode.FORM_VIEW)) {
            fVozilo.getBtnSacuvajIzmene().setVisible(false);
            fVozilo.getBtnSacuvajVozilo().setVisible(false);

            fVozilo.getTxtRegistarskiBroj().setEnabled(false);
            fVozilo.getTxtBrojSasije().setEnabled(false);
            fVozilo.getTxtGodiste().setEnabled(false);
            fVozilo.getTxtZapreminaMotora().setEnabled(false);
            fVozilo.getTxtSnaga().setEnabled(false);
            fVozilo.getTxtDnevnaCena().setEnabled(false);
            fVozilo.getCmbMarka().setEnabled(false);
            fVozilo.getCmbModel().setEnabled(false);
            prikaziIzabranoVozilo();
        }

        if (formMode.equals(FormMode.FORM_UPDATE)) {
            fVozilo.getBtnSacuvajVozilo().setVisible(false);
            fVozilo.getBtnOmoguciIzmenu().setVisible(false);
            fVozilo.getBtnSacuvajIzmene().setVisible(true);

            fVozilo.getTxtBrojSasije().setEnabled(false);
            fVozilo.getTxtGodiste().setEnabled(false);
            fVozilo.getTxtZapreminaMotora().setEnabled(false);
            fVozilo.getTxtSnaga().setEnabled(false);
            fVozilo.getCmbMarka().setEnabled(false);
            fVozilo.getCmbModel().setEnabled(false);
            fVozilo.getTxtDnevnaCena().setEnabled(true);
            fVozilo.getTxtRegistarskiBroj().setEnabled(true);

            prikaziIzabranoVozilo();

        }
    }

    private void napuniComboMarka() {
        List<Marka> marke = controller.Controller.getInstance().vratiMarke();
        for (Marka marka : marke) {
            fVozilo.getCmbMarka().addItem(marka);
        }
    }

    private void napuniComboModeli(Marka marka) {
        fVozilo.getCmbModel().removeAllItems();
        List<Model> modeli = Controller.getInstance().vratiModele(marka);
        for (Model model : modeli) {
            fVozilo.getCmbModel().addItem(model);
        }
    }

    private void prikaziIzabranoVozilo() {
        Vozilo v = (Vozilo) Controller.getInstance().getMap().get("izabranoVozilo");
        if (v == null) {
            return;
        }
        fVozilo.getTxtBrojSasije().setText(v.getBrojSasije());
        fVozilo.getTxtDnevnaCena().setText(v.getDnevnaCena() + "");
        fVozilo.getTxtGodiste().setText(v.getGodiste() + "");
        fVozilo.getTxtRegistarskiBroj().setText(v.getRegistarskiBroj());
        fVozilo.getTxtSnaga().setText(v.getSnaga() + "");
        fVozilo.getTxtVoziloID().setText(v.getVoziloId() + "");
        fVozilo.getTxtZapreminaMotora().setText(v.getZapreminaMotora() + "");
        fVozilo.getCmbMarka().getModel().setSelectedItem(v.getModel().getMarka());
        fVozilo.getCmbModel().getModel().setSelectedItem(v.getModel());
    }

    private class OsluskivacSacuvajVozilo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String regBr = fVozilo.getTxtRegistarskiBroj().getText();
            String brojSasije = fVozilo.getTxtBrojSasije().getText();
            int godiste, zapremina, snaga;
            double dnevnaCena;
            try {
                godiste = Integer.parseInt(fVozilo.getTxtGodiste().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fVozilo, "Godiste nije korektno", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                zapremina = Integer.parseInt(fVozilo.getTxtZapreminaMotora().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fVozilo, "Zapremina nije korektna", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                snaga = Integer.parseInt(fVozilo.getTxtSnaga().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fVozilo, "Snaga nije korektna", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                dnevnaCena = Double.parseDouble(fVozilo.getTxtDnevnaCena().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fVozilo, "Dnevna cena nije korektna", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Model model = (Model) fVozilo.getCmbModel().getSelectedItem();
            Marka marka = (Marka) fVozilo.getCmbMarka().getSelectedItem();

            Vozilo v = new Vozilo(0, regBr, brojSasije, godiste, zapremina, snaga, dnevnaCena, model);
            boolean sacuvano = Controller.getInstance().sacuvajVozilo(v);
            fVozilo.dispose();
            if (sacuvano) {
                JOptionPane.showMessageDialog(fVozilo, "Sistem je zapamtio novo vozilo!");
            } else {
                JOptionPane.showMessageDialog(fVozilo, "Sistem ne moze da zapamti novo vozilo!", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class OsluskivacIzmeniVozilo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String regBr = fVozilo.getTxtRegistarskiBroj().getText();
            double dnevnaCena;
            try {
                dnevnaCena = Double.parseDouble(fVozilo.getTxtDnevnaCena().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fVozilo, "Dnevna cena nije korektna", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vozilo v = (Vozilo) Controller.getInstance().getMap().get("izabranoVozilo");
            v.setDnevnaCena(dnevnaCena);
            v.setRegistarskiBroj(regBr);
            boolean izmenjeno = Controller.getInstance().izmeniVozilo(v);
            fVozilo.dispose();
            if (izmenjeno) {
                JOptionPane.showMessageDialog(fVozilo, "Sistem je zapamtio vozilo!");
                GuiCoordinator.getInstance().osveziFormuPretragaVozila();
            } else {
                JOptionPane.showMessageDialog(fVozilo, "Sistem ne moze da zapamti vozilo!", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class OsluskivacComboMarka implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            napuniComboModeli((Marka) fVozilo.getCmbMarka().getSelectedItem());
        }
    }

    private class OsluskivacOmoguciIzmenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prepareForm(FormMode.FORM_UPDATE);
        }
    }

}
