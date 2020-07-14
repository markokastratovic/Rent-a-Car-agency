/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.tablemodel;

import domen.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class KlijentTableModel extends AbstractTableModel {

    private final List<Klijent> klijenti;

    private String[] columnNames = new String[]{"ID", "Ime", "Prezime", "JMBG", "Adresa", "Broj telefona"};
    private Class[] columnClasses = new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class};

    public KlijentTableModel(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    @Override
    public int getRowCount() {
        return klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKlijentId();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getJmbg();
            case 4:
                return k.getAdresa();
            case 5:
                return k.getBrojTelefona();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    public Klijent vratiKlijenta(int red) {
        return klijenti.get(red);
    }

}
