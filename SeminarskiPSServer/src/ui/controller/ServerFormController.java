/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import controller.Controller;
import controller.PodesavanjeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import server.Server;
import ui.form.ServerForm;

/**
 *
 * @author Marko
 */
public class ServerFormController {

    ServerForm serverForm;
    PodesavanjeController podesavanjeController;

    public ServerFormController() {
        serverForm = new ServerForm();
        serverForm.getBtnPokreniServer().addActionListener(new OsluskivacPokreniServer());
        serverForm.getBtnPodesavanja().addActionListener(new OsluskivacPodesavanja());
        serverForm.getBtnZaustaviServer().addActionListener(new OsluskivacZaustaviServer());
        serverForm.setVisible(true);
    }

    private class OsluskivacPokreniServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Server server = new Server(serverForm);
            server.start();
            //serverForm.getBtnPokreniServer().setEnabled(false);
            //serverForm.getBtnPodesavanja().setEnabled(false);
        }
    }

    private class OsluskivacPodesavanja implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (podesavanjeController == null) {
                podesavanjeController = new PodesavanjeController();
            }
            podesavanjeController.otvoriFPodesavanja();
        }
    }

    private class OsluskivacZaustaviServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().zaustaviServer();
        }
    }

}
