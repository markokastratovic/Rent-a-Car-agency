/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ui.form.FLogin;

/**
 *
 * @author Marko
 */
public class LoginController {

    FLogin fLogin;

    public LoginController() {
    }

    public void otvoriFlogin() {
        fLogin = new FLogin();
        fLogin.getBtnLogin().addActionListener(new OsluskivacLogin());
        fLogin.getBtnCancel().addActionListener(new OsluskivacCancel());
        fLogin.getBtnPodesavanja().addActionListener(new OsluskivacPodesavanja());
        fLogin.setVisible(true);
    }

    private void validate(JTextField jtxtUsername, JPasswordField jtxtPassword) throws Exception {
        fLogin.getLabUsernameError().setText("");
        fLogin.getLabPasswordError().setText("");

        if (jtxtUsername.getText().isEmpty()) {
            fLogin.getLabUsernameError().setText("Username polje nije popunjeno!");
            throw new Exception("Username polje nije popunjeno");
        }

        if (String.valueOf(jtxtPassword.getPassword()).isEmpty()) {
            fLogin.getLabPasswordError().setText("Password polje nije popunjeno!");
            throw new Exception("Password polje nije popunjeno");
        }

        Korisnik korisnik = Controller.getInstance().login(jtxtUsername.getText(), String.valueOf(jtxtPassword.getPassword()));
        if (korisnik != null) {
            Controller.getInstance().getMap().put("ulogovaniKorisnik", korisnik);
            return;
        }

        JOptionPane.showMessageDialog(fLogin, "Ne postoji ta kombinacija username-password", "Neuspesno prijavljivanje", JOptionPane.ERROR_MESSAGE);
        throw new Exception("Ne postoji ta kombinacija username-password");
    }

    private class OsluskivacLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                validate(fLogin.getTxtUsername(), fLogin.getTxtPassword());

                fLogin.dispose();
                GuiCoordinator.getInstance().otvoriFMain();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private class OsluskivacCancel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fLogin.dispose();
            //System.exit(0);
        }
    }

    private class OsluskivacPodesavanja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuiCoordinator.getInstance().otvoriFormuPodesavanje();
        }
    }

}
