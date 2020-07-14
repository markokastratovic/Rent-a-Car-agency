/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import database.connection.ConnectionFactory;
import database.DatabaseBroker;

/**
 *
 * @author Marko
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected DatabaseBroker dbb;

    public ApstraktnaGenerickaOperacija() {
        dbb = new DatabaseBroker();
    }

    public final void templateExecute(Object entity) throws Exception {
        try {
            validiraj(entity);
            zapocniTransakciju();
            izvrsi(entity);
            potvrdiTransakciju();
        } catch (Exception ex) {
            ex.printStackTrace();
            ponistiTransakciju();
            throw ex;
        }
    }

    protected abstract void validiraj(Object entity) throws Exception;

    protected abstract void izvrsi(Object entity) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ConnectionFactory.getInstance().getConnection().setAutoCommit(false);
    }

    private void potvrdiTransakciju() throws Exception {
        ConnectionFactory.getInstance().getConnection().commit();
    }

    private void ponistiTransakciju() throws Exception {
        ConnectionFactory.getInstance().getConnection().rollback();
    }
}
