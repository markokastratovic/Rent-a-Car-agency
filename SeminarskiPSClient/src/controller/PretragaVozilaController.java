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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import ui.component.tablemodel.VoziloTableModel;
import ui.form.FPretragaVozila;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class PretragaVozilaController {

    FPretragaVozila fPretragaVozila;

    public void otvoriPretraguVozila(JFrame parent) {
        fPretragaVozila = new FPretragaVozila(parent, true);
        fPretragaVozila.getBtnPretrazi().addActionListener(new OsluskivacPretrazi());
        fPretragaVozila.getBtnPrikaziDetalje().addActionListener(new OsluskivacPrikaziDetalje());
        fPretragaVozila.getCmbMarka().addActionListener(new OsluskivacCmbMarka());
        napuniComboMarka();
        popuniTabelu();
        fPretragaVozila.setVisible(true);
    }

    private void napuniComboMarka() {
        List<Marka> marke = controller.Controller.getInstance().vratiMarke();
        for (Marka marka : marke) {
            fPretragaVozila.getCmbMarka().addItem(marka);
        }
    }

    private void napuniComboModeli(Marka marka) {
        fPretragaVozila.getCmbModel().removeAllItems();
        List<Model> modeli = Controller.getInstance().vratiModele(marka);
        for (Model model : modeli) {
            fPretragaVozila.getCmbModel().addItem(model);
        }
    }

    public void popuniTabelu() {
        List<Vozilo> vozila = Controller.getInstance().vratiVozila();
        fPretragaVozila.getTblVozila().setModel(new VoziloTableModel(vozila));

        formatirajTabelu();
    }

    private void formatirajTabelu() {
        //sirina prve kolone
        fPretragaVozila.getTblVozila().getColumnModel().getColumn(0).setMaxWidth(60);

        //centriranje teksta
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        fPretragaVozila.getTblVozila().setDefaultRenderer(Integer.class, centerRenderer);
        fPretragaVozila.getTblVozila().setDefaultRenderer(String.class, centerRenderer);
        fPretragaVozila.getTblVozila().setDefaultRenderer(Double.class, centerRenderer);
        fPretragaVozila.getTblVozila().setDefaultRenderer(Model.class, centerRenderer);
        fPretragaVozila.getTblVozila().setDefaultRenderer(Marka.class, centerRenderer);
    }

    private class OsluskivacPretrazi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int id, god;
            try {
                id = Integer.valueOf(fPretragaVozila.getTxtVoziloID().getText());
            } catch (NumberFormatException e) {
                id = -1;
            }
            String reg = fPretragaVozila.getTxtRegistarskiBroj().getText();
            try {
                god = Integer.valueOf(fPretragaVozila.getTxtGodiste().getText());
            } catch (NumberFormatException e) {
                god = -1;
            }
            String brojSasije = fPretragaVozila.getTxtBrojSasije().getText();
            Model model = (Model) fPretragaVozila.getCmbModel().getSelectedItem();
            Vozilo v = new Vozilo(id, reg, brojSasije, god, -1, -1, -1, model);
            List<Vozilo> lista = Controller.getInstance().vratiVozila(v);
            if (lista.size() == 0) {
                JOptionPane.showMessageDialog(fPretragaVozila, "Sistem ne moze da nadje vozila po zadatoj vrednosti!");
            }
            fPretragaVozila.getTblVozila().setModel(new VoziloTableModel(lista));
            formatirajTabelu();
            //System.err.println(Controller.getInstance().vratiVozila(v).size());
        }
    }

    private class OsluskivacPrikaziDetalje implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = fPretragaVozila.getTblVozila().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(fPretragaVozila, "Niste izabrali red", "Greska", JOptionPane.ERROR_MESSAGE);
            }
            //int idVozila=(int) tblVozila.getModel().getValueAt(row, 0);
            VoziloTableModel vtm = (VoziloTableModel) fPretragaVozila.getTblVozila().getModel();
            Vozilo v = vtm.vratiVozilo(row);

            Controller.getInstance().postaviTrenutnoIzabranoVozilo(v);
            GuiCoordinator.getInstance().otvoriUnosVozila(null, FormMode.FORM_VIEW);
        }
    }

    private class OsluskivacCmbMarka implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            napuniComboModeli((Marka) fPretragaVozila.getCmbMarka().getSelectedItem());
        }
    }

}
