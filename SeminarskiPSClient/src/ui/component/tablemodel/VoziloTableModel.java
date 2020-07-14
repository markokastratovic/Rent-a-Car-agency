/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.tablemodel;

import domen.Marka;
import domen.Model;
import domen.Vozilo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class VoziloTableModel extends AbstractTableModel {

    private final List<Vozilo> vozila;

    private String[] columnNames = new String[]{"ID Vozila", "Registracioni broj", "Broj sasije", "Marka", "Model", "Cena po danu"};
    private Class[] columnClasses = new Class[]{Integer.class, String.class, String.class, Marka.class, Model.class, Double.class};

    public VoziloTableModel(List<Vozilo> vozila) {
        this.vozila = vozila;
    }

    @Override
    public int getRowCount() {
        if (vozila == null) {
            return 0;
        }
        return vozila.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Vozilo v = vozila.get(row);
        switch (column) {
            case 0:
                return v.getVoziloId();
            case 1:
                return v.getRegistarskiBroj();
            case 2:
                return v.getBrojSasije();
            case 3:
                return v.getModel().getMarka();
            case 4:
                return v.getModel();
            case 5:
                return v.getDnevnaCena();
            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return columnClasses[column];
    }

    public Vozilo vratiVozilo(int red) {
        return vozila.get(red);
    }

}
