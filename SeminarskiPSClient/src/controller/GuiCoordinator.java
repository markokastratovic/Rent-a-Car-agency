/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ui.form.FMain;
import ui.form.FormMode;

/**
 *
 * @author Marko
 */
public class GuiCoordinator {

    private static GuiCoordinator instance;

    private LoginController loginController;
    private FMainController fMainController;
    private VoziloController voziloController;
    private PretragaVozilaController pretragaVozilaController;
    private KlijentController klijentController;
    private PretragaKlijenataController pretragaKlijenataController;
    private ZaduzenjeController zaduzenjeController;
    private PretragaZaduzenjaController pretragaZaduzenjaController;
    private PodesavanjeController podesavanjeController;

    public GuiCoordinator() {
    }

    public void otvoriLogin() {
        if (loginController == null) {
            loginController = new LoginController();
        }
        loginController.otvoriFlogin();
    }

    public static GuiCoordinator getInstance() {
        if (instance == null) {
            instance = new GuiCoordinator();
        }
        return instance;
    }

    public void otvoriFMain() {
        if (fMainController == null) {
            fMainController = new FMainController();
        }
        fMainController.otvoriFormu();
    }

    void otvoriUnosVozila(FMain fMain, FormMode formMode) {
        if (voziloController == null) {
            voziloController = new VoziloController();
        }
        voziloController.otvoriFvozilo(fMain, formMode);
    }

    void otvoriPretraguVozila(FMain fMain) {
        if (pretragaVozilaController == null) {
            pretragaVozilaController = new PretragaVozilaController();
        }
        pretragaVozilaController.otvoriPretraguVozila(fMain);
    }

    void otvoriUnosKlijenta(FMain fMain, FormMode formMode) {
        if (klijentController == null) {
            klijentController = new KlijentController();
        }
        klijentController.otvoriFKlijent(fMain, formMode);
    }

    void otvoriPretraguKlijenata(FMain fMain) {
        if (pretragaKlijenataController == null) {
            pretragaKlijenataController = new PretragaKlijenataController();
        }
        pretragaKlijenataController.otvoriFPretragaKlijenata(fMain);
    }

    void osveziFormuPretragaKlijenata() {
        if (pretragaKlijenataController != null) {
            pretragaKlijenataController.popuniTabelu();
        }
    }

    void osveziFormuPretragaVozila() {
        if (pretragaVozilaController != null) {
            pretragaVozilaController.popuniTabelu();
        }
    }

    void otvoriZaduzenje(FMain fmain, FormMode formMode) {
        if (zaduzenjeController == null) {
            zaduzenjeController = new ZaduzenjeController();
        }
        zaduzenjeController.otvoriFZaduzenje(fmain, formMode);
    }

    void otvoriPretraguZaduzenja(FMain fMain) {
        if (pretragaZaduzenjaController == null) {
            pretragaZaduzenjaController = new PretragaZaduzenjaController();
        }
        pretragaZaduzenjaController.otvoriPretraguZaduzenja(fMain);
    }

    void osveziFormuPretragaZaduzenja() {
        if (pretragaZaduzenjaController != null) {
            pretragaZaduzenjaController.popuniTabelu();
        }
    }

    void otvoriFormuPodesavanje() {
        if (podesavanjeController == null) {
            podesavanjeController = new PodesavanjeController();
        }
        podesavanjeController.otvoriFPodesavanja();
    }

}
