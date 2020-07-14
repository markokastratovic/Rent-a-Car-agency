/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import config.Konfiguracija;
import controller.Controller;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ui.form.ServerForm;

/**
 *
 * @author Marko
 */
public class Server extends Thread {

    List<ClientThread> klijenti;
    ServerSocket serverSocket;
    ServerForm serverForm;

    public Server(ServerForm serverForm) {
        this.serverForm = serverForm;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(Konfiguracija.getInstance().getProperty("port")));
            serverForm.pokrenut(InetAddress.getLocalHost().getHostAddress() + " : " + serverSocket.getLocalPort());
            klijenti = new ArrayList<>();
            while (true) {
                Controller.getInstance().setServer(this);
                System.out.println("Cekam konekciju...");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ClientThread clientThread = new ClientThread(socket);
                klijenti.add(clientThread);
                clientThread.start();
            }
        } catch (BindException be) {
            JOptionPane.showMessageDialog(serverForm, "Port " + "je zauzet! \nServer nije podignut!");
        } catch (IOException ex) {
            System.out.println("Server je zaustavljen!");
            serverForm.zaustavljen();
            ex.printStackTrace();
        }
    }

    public void zaustaviServer() {
        for (ClientThread clientThread : klijenti) {
            if (clientThread != null && clientThread.socket != null) {
                try {
                    clientThread.socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        serverForm.zaustavljen();
    }

}
