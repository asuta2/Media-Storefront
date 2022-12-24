package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Purchases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class purchasesDaoImpl extends AbstractDao<Purchases>implements PurchasesDao {
    private Connection conn;
    public purchasesDaoImpl() {
        super("Purchases");
    }



    @Override
    public Map<String, Object> object2row(Purchases object) {
        return null;
    }

    @Override
    public Purchases row2object(ResultSet rs) {
        return null;
    }

    @Override
    public List<Purchases> getAllPurchasesById(int id) {
       //gets all purchases from a user
        return executeQuery("SELECT * FROM Purchases WHERE idUser = " + id,new Object[] {id});
    }
}
