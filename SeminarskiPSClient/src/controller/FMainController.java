/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.form.FMain;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class FMainController {

    FMain fMain;

    void otvoriFormu() {
        fMain = new FMain();
        Controller.getInstance().setfMainController(this);
        fMain.getMenuItemUnosVozila().addActionListener(new OsluskivacUnosVozila());
        fMain.getMenuItemPretragaVozila().addActionListener(new OsluskivacPretragaVozila());
        fMain.getMenuItemUnosKlijenta().addActionListener(new OsluskivacUnosKlijenata());
        fMain.getMenuItemPretragaKlijenata().addActionListener(new OsluskivacPretragaKlijenata());
        fMain.getMenuItemUnosZaduzenja().addActionListener(new OsluskivacUnosZaduzenja());
        fMain.getMenuItemPretragaZaduzenja().addActionListener(new OsluskivacPretragaZaduzenja());
        fMain.getLblTrenutniKorisnik().setText((Controller.getInstance().getMap().get("ulogovaniKorisnik")).toString());
        fMain.setVisible(true);
    }

    public void zatvoriSve() {
        fMain.dispose();
    }

    private class OsluskivacUnosVozila implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriUnosVozila(fMain, FormMode.FORM_ADD);
        }
    }

    private class OsluskivacPretragaVozila implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriPretraguVozila(fMain);
        }
    }

    private class OsluskivacUnosKlijenata implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriUnosKlijenta(fMain, FormMode.FORM_ADD);
        }
    }

    private class OsluskivacPretragaKlijenata implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriPretraguKlijenata(fMain);
        }
    }

    private class OsluskivacUnosZaduzenja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriZaduzenje(fMain, FormMode.FORM_ADD);
        }
    }

    private class OsluskivacPretragaZaduzenja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriPretraguZaduzenja(fMain);
        }
    }
}
