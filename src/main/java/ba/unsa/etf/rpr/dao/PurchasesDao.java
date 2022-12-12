package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Purchases;

import java.util.List;

public interface PurchasesDao extends dao<Purchases>{
    List<Purchases> getAllPurchasesById(int id);
}
