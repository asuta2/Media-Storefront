package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.mn.Purchases;

import java.util.List;

public class PurchasesManager {
    public List<Purchases> getAllPurchasesById(int id) {
        return DaoFactory.getPurchasesDao().getAllPurchasesById(id);
    }
    public List<Purchases> getAll() {
        return DaoFactory.getPurchasesDao().getAll();
    }
    public Purchases getById(int id) {
        return DaoFactory.getPurchasesDao().getById(id);
    }
    public Purchases add(Purchases purchase) {
        return DaoFactory.getPurchasesDao().add(purchase);
    }
    public Purchases update(Purchases purchase) {
        return DaoFactory.getPurchasesDao().update(purchase);
    }
    public void delete(int id) {
        DaoFactory.getPurchasesDao().delete(id);
    }



}
