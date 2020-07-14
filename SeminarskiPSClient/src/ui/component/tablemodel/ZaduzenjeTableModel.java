/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.tablemodel;

import domen.Zaduzenje;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class ZaduzenjeTableModel extends AbstractTableModel {

    private final List<Zaduzenje> zaduzenja;
    SimpleDateFormat sdf;

    public ZaduzenjeTableModel(List<Zaduzenje> zaduzenja) {
        this.zaduzenja = zaduzenja;
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }

    private String[] columnNames = new String[]{"ID Zaduzenja", "DatumOd", "DatumDo", "Cena", "Vozilo", "Klijent"};
    private Class[] columnClasses = new Class[]{Integer.class, String.class, String.class, Double.class, String.class, String.class};

    @Override
    public int getRowCount() {
        if (zaduzenja == null) {
            return 0;
        }
        return zaduzenja.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Zaduzenje z = zaduzenja.get(row);
        switch (column) {
            case 0:
                return z.getZaduzenjeId();
            case 1:
                return sdf.format(z.getDatumOd());
            case 2:
                if (z.getDatumDo() != null) {
                    return sdf.format(z.getDatumDo());
                } else {
                    return "/";
                }
            case 3:
                return z.getUkupnaCena();
            case 4:
                return z.getVozilo().toString();
            case 5:
                return z.getKlijent().toString();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return columnClasses[column];
    }

    public Zaduzenje vratiZaduzenje(int red) {
        return zaduzenja.get(red);
    }

}
