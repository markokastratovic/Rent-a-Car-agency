/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marko
 */
public class Model extends ApstraktniDomenskiObjekat {

    private int modelId;
    private String nazivModela;
    private int brojSedista;
    private Marka marka;

    public Model() {
    }

    public Model(int modelId, String nazivModela, int brojSedista, Marka marka) {
        this.modelId = modelId;
        this.nazivModela = nazivModela;
        this.brojSedista = brojSedista;
        this.marka = marka;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public int getBrojSedista() {
        return brojSedista;
    }

    public void setBrojSedista(int brojSedista) {
        this.brojSedista = brojSedista;
    }

    @Override
    public String toString() {
        return nazivModela;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Model other = (Model) obj;
        if (this.modelId != other.modelId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "Model";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> modeli = new ArrayList<>();
        while (rs.next()) {
            int modelID = rs.getInt(1);
            String nazivModela = rs.getString(2);
            int brojSedista = rs.getInt(3);
            Model m = new Model(modelID, nazivModela, brojSedista, marka);
            modeli.add(m);
        }
        return modeli;
    }

    @Override
    public String vratiKoloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "ModelID='" + modelId + "'";
    }

}
