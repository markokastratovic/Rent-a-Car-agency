/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Marko
 */
public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties properties;

    private Konfiguracija() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("./podesavanja.properties"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "greska pri ucitavanju konfiguracionog fajla");
            e.printStackTrace();
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try {
            properties.store(new FileOutputStream("./podesavanja.properties"), null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "greska pri cuvanju konfiguracionog fajla");

        }
    }

}
