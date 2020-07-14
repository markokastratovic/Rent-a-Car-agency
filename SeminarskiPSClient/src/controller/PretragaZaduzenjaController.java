/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Zaduzenje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import ui.component.tablemodel.ZaduzenjeTableModel;
import ui.form.FPretragaZaduzenja;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class PretragaZaduzenjaController {

    FPretragaZaduzenja fPretragaZaduzenja;

    public void otvoriPretraguZaduzenja(JFrame parent) {
        fPretragaZaduzenja = new FPretragaZaduzenja(parent, true);
        fPretragaZaduzenja.getBtnPretrazi().addActionListener(new OsluskivacPretraziZaduzenja());
        fPretragaZaduzenja.getBtnPrikaziDetalje().addActionListener(new OsluskivacPrikaziDetaljeZaduzenja());
        popuniTabelu();
        fPretragaZaduzenja.setVisible(true);
    }

    public void popuniTabelu() {
        List<Zaduzenje> listaZaduzenja = Controller.getInstance().vratiZaduzenja();
        fPretragaZaduzenja.getTblZaduzenja().setModel(new ZaduzenjeTableModel(listaZaduzenja));
        formatirajTabelu();

    }

    private void formatirajTabelu() {
        //sirina prve kolone
        fPretragaZaduzenja.getTblZaduzenja().getColumnModel().getColumn(0).setMaxWidth(80);
        fPretragaZaduzenja.getTblZaduzenja().getColumnModel().getColumn(1).setMaxWidth(80);
        fPretragaZaduzenja.getTblZaduzenja().getColumnModel().getColumn(2).setMaxWidth(80);
        fPretragaZaduzenja.getTblZaduzenja().getColumnModel().getColumn(3).setMaxWidth(60);
        fPretragaZaduzenja.getTblZaduzenja().getColumnModel().getColumn(4).setMinWidth(200);

        //centriranje teksta
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        fPretragaZaduzenja.getTblZaduzenja().setDefaultRenderer(Integer.class, centerRenderer);
        fPretragaZaduzenja.getTblZaduzenja().setDefaultRenderer(Double.class, centerRenderer);
        fPretragaZaduzenja.getTblZaduzenja().setDefaultRenderer(String.class, centerRenderer);

    }

    private class OsluskivacPretraziZaduzenja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String kriterijum = fPretragaZaduzenja.getTxtKriterijumPretrage().getText();
            List<Zaduzenje> listaZaduzenja;
            if (!kriterijum.equals("")) {
                listaZaduzenja = Controller.getInstance().vratiZaduzenja(kriterijum);
            } else {
                listaZaduzenja = Controller.getInstance().vratiZaduzenja();
            }
            if (listaZaduzenja.size() == 0) {
                JOptionPane.showMessageDialog(fPretragaZaduzenja, "Sistem ne moze da nadje zaduzenja po zadatoj vrednosti!");
            }
            fPretragaZaduzenja.getTblZaduzenja().setModel(new ZaduzenjeTableModel(listaZaduzenja));
            formatirajTabelu();
        }
    }

    private class OsluskivacPrikaziDetaljeZaduzenja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = fPretragaZaduzenja.getTblZaduzenja().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(fPretragaZaduzenja, "Niste izabrali red", "Greska", JOptionPane.ERROR_MESSAGE);
            }
            ZaduzenjeTableModel ztm = (ZaduzenjeTableModel) fPretragaZaduzenja.getTblZaduzenja().getModel();
            Zaduzenje z = ztm.vratiZaduzenje(row);

            Controller.getInstance().postaviTrenutnoIzabranoZaduzenje(z);
            GuiCoordinator.getInstance().otvoriZaduzenje(null, FormMode.FORM_UPDATE);

        }
    }

}
