/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Klijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import ui.component.tablemodel.KlijentTableModel;
import ui.form.FMain;
import ui.form.FPretragaKlijenata;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class PretragaKlijenataController {

    FPretragaKlijenata fPretragaKlijenata;

    public PretragaKlijenataController() {
    }

    void otvoriFPretragaKlijenata(FMain parent) {
        fPretragaKlijenata = new FPretragaKlijenata(parent, true);
        fPretragaKlijenata.getBtnPretrazi().addActionListener(new OsluskivacPretrazi());
        fPretragaKlijenata.getBtnPrikaziDetalje().addActionListener(new OsluskivacPretraziDetalje());
        popuniTabelu();
        fPretragaKlijenata.setVisible(true);
    }

    public void popuniTabelu() {
        List<Klijent> klijenti = Controller.getInstance().vratiKlijente();
        fPretragaKlijenata.getTblKlijenti().setModel(new KlijentTableModel(klijenti));
        formatirajTabelu();
    }

    private void formatirajTabelu() {
        //sirina prve kolone
        fPretragaKlijenata.getTblKlijenti().getColumnModel().getColumn(0).setMaxWidth(40);
        fPretragaKlijenata.getTblKlijenti().getColumnModel().getColumn(4).setMinWidth(200);

        //centriranje teksta
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        //tblKlijenti.setDefaultRenderer(String.class, centerRenderer);
        fPretragaKlijenata.getTblKlijenti().setDefaultRenderer(Integer.class, centerRenderer);
    }

    private class OsluskivacPretrazi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String kriterijumPretrage = fPretragaKlijenata.getTxtKriterijumPretrage().getText().trim();
            List<Klijent> lista = Controller.getInstance().vratiKlijente(kriterijumPretrage);
            if (lista.size() == 0) {
                JOptionPane.showMessageDialog(fPretragaKlijenata, "Sistem ne moze da nadje klijente po zadatoj vrednosti!");
            }
            fPretragaKlijenata.getTblKlijenti().setModel(new KlijentTableModel(lista));
            formatirajTabelu();
        }
    }

    private class OsluskivacPretraziDetalje implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = fPretragaKlijenata.getTblKlijenti().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(fPretragaKlijenata, "Niste izabrali red", "Greska", JOptionPane.ERROR_MESSAGE);
            }
            KlijentTableModel ktm = (KlijentTableModel) fPretragaKlijenata.getTblKlijenti().getModel();
            Klijent k = ktm.vratiKlijenta(row);

            Controller.getInstance().postaviTrenutnoIzabranogKlijenta(k);
            GuiCoordinator.getInstance().otvoriUnosKlijenta(null, FormMode.FORM_VIEW);
        }
    }

}
