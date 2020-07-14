/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.connection.ConnectionFactory;
import domen.ApstraktniDomenskiObjekat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Marko
 */
public class DatabaseBroker {

    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat ado, String join) {
        List<ApstraktniDomenskiObjekat> list = null;
        try {
            String query = "SELECT * FROM " + ado.vratiNazivTabele();
            if (join != null) {
                query += join;
            }
            System.out.println(query);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            list = ado.vratiListu(rs);

            if (ado.slozenObjekat()) {
                List<ApstraktniDomenskiObjekat> listaSlabih = null;
                for (ApstraktniDomenskiObjekat jakObjekat : list) {
                    ApstraktniDomenskiObjekat slabObjekat = jakObjekat.vratiTipSlabogObjekta();
                    query = "SELECT * FROM " + slabObjekat.vratiNazivTabele();
                    query += slabObjekat.joinUslovZaSlabObjekat();
                    query += " where " + jakObjekat.vratiPrimarniKljuc();
                    st = conn.createStatement();
                    rs = st.executeQuery(query);
                    listaSlabih = slabObjekat.vratiListu(rs);
                    jakObjekat.veziSlabObjekatZaJakObjekat(listaSlabih);
                }
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ApstraktniDomenskiObjekat> vratiPoUslovu(ApstraktniDomenskiObjekat ado, String join, String condition) {
        List<ApstraktniDomenskiObjekat> list = null;
        try {
            String query = "SELECT * FROM " + ado.vratiNazivTabele();
            if (join != null) {
                query += join;
            }
            query += " where " + condition;
            System.out.println(query);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            list = ado.vratiListu(rs);

            if (ado.slozenObjekat()) {
                List<ApstraktniDomenskiObjekat> listaSlabih = null;
                for (ApstraktniDomenskiObjekat jakObjekat : list) {
                    ApstraktniDomenskiObjekat slabObjekat = jakObjekat.vratiTipSlabogObjekta();
                    query = "SELECT * FROM " + slabObjekat.vratiNazivTabele();
                    query += slabObjekat.joinUslovZaSlabObjekat();
                    query += " where " + jakObjekat.vratiPrimarniKljuc();
                    st = conn.createStatement();
                    rs = st.executeQuery(query);
                    listaSlabih = slabObjekat.vratiListu(rs);
                    jakObjekat.veziSlabObjekatZaJakObjekat(listaSlabih);
                }
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ApstraktniDomenskiObjekat vratiPoPrimarnomKljucu(ApstraktniDomenskiObjekat ado, String join) throws Exception {
        ApstraktniDomenskiObjekat objekat = null;
        String query = "SELECT * FROM " + ado.vratiNazivTabele();
        if (join != null) {
            query += join;
        }
        query += " WHERE " + ado.vratiPrimarniKljuc();
        System.out.println(query);
        Connection conn = ConnectionFactory.getInstance().getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        objekat = ado.vratiObjekatIzResultSeta(rs);

        rs.close();
        st.close();

        return objekat;
    }

    public boolean ubaci(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            String query = "INSERT INTO " + ado.vratiNazivTabele() + "(" + ado.vratiKoloneZaInsert() + ") VALUES(" + ado.vratiVrednostiZaInsert() + ")";
            System.out.println(query);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            if (ado.slozenObjekat()) {
                ResultSet rs = st.getGeneratedKeys();
                int id = -1;
                if (rs.next()) {
                    id = rs.getInt(1);
                    ado.postaviID(id);
                }
                for (int i = 0; i < ado.vratiListuSlabih().size(); i++) {
                    ApstraktniDomenskiObjekat slabObjekat = ado.vratiListuSlabih().get(i);
                    query = "INSERT INTO " + slabObjekat.vratiNazivTabele() + "(" + slabObjekat.vratiKoloneZaInsert() + ") VALUES(" + slabObjekat.vratiVrednostiZaInsert() + ")";
                    System.out.println(query);
                    st.executeUpdate(query);
                }

            }

            st.close();
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean izmeni(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            String query = "UPDATE " + ado.vratiNazivTabele() + " SET " + ado.vratiVrednostiZaUpdate() + " WHERE " + ado.vratiPrimarniKljuc();
            System.out.println(query);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
