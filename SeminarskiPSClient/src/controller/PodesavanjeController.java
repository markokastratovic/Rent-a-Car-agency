/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.Konfiguracija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ui.form.FPodesavanje;

/**
 *
 * @author Marko
 */
public class PodesavanjeController {

    FPodesavanje fPodesavanje;

    public void otvoriFPodesavanja() {
        fPodesavanje = new FPodesavanje(null, true);
        prikaziTrenutnuKonfiguraciju();
        fPodesavanje.getBtnSacuvaj().addActionListener(new OsluskivacSacuvajPodesavanja());
        fPodesavanje.setVisible(true);
    }

    public void prikaziTrenutnuKonfiguraciju() {
        fPodesavanje.getTxtAdresa().setText(Konfiguracija.getInstance().getProperty("ip"));
        fPodesavanje.getTxtPort().setText(Konfiguracija.getInstance().getProperty("port"));
    }

    private class OsluskivacSacuvajPodesavanja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String ipAdresa = fPodesavanje.getTxtAdresa().getText();
            String port = fPodesavanje.getTxtPort().getText();

            if (ispravanPort(port)) {
                Konfiguracija.getInstance().setProperty("ip", fPodesavanje.getTxtAdresa().getText());
                Konfiguracija.getInstance().setProperty("port", fPodesavanje.getTxtPort().getText());
                Konfiguracija.getInstance().sacuvajIzmene();
                JOptionPane.showMessageDialog(null, "Podesavanja uspesno sacuvana");
                fPodesavanje.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Uneta adresa(port) nije ispravan");
            }
        }
    }

    private boolean ispravanPort(String p) {
        int port = 0;
        try {
            port = Integer.parseInt(p);
            if (port < 1024 || port > 65535) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
