/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.ApstraktniDomenskiObjekat;
import domen.Korisnik;
import java.util.List;
import so.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Marko
 */
public class PrijavljivanjeSO extends ApstraktnaGenerickaOperacija {

    private List<ApstraktniDomenskiObjekat> lista;

    @Override
    protected void validiraj(Object entity) throws Exception {
        if (!(entity instanceof Korisnik)) {
            throw new Exception("Invalid object (Korisnik)");
        }
    }

    @Override
    protected void izvrsi(Object entity) throws Exception {
        lista = dbb.vratiSve((ApstraktniDomenskiObjekat) entity, null);
    }

    public List<Korisnik> getListaKorisnika() {
        //ako listu<ApstraktniDomenskiObjekat> kastujem u listu<?>, ta lista se moze kastovati u listu<Korisnik>
        List<?> generickaLista = lista;
        List<Korisnik> listaKorisnika = (List<Korisnik>) generickaLista;
        return listaKorisnika;
    }

}
