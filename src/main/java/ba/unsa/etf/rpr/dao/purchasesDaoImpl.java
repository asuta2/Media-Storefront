package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Purchases;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public class purchasesDaoImpl extends AbstractDao<Purchases>implements PurchasesDao {

    public purchasesDaoImpl() {
        super("purchases");
    }



    @Override
    public Map<String, Object> object2row(Purchases object) {
        Map<String, Object> map = Map.of("purchasesId", object.getPurchasesId(), "userId", object.getUserId(), "mediaId", object.getMediaId(), "boughtDate", object.getBoughtDate());
        return map;
    }

    @Override
    public Purchases row2object(ResultSet rs) {
        try{
            Purchases temp=new Purchases();
            temp.setPurchasesId(rs.getInt(1));
            temp.setUserId(rs.getInt(2));
            temp.setMediaId(rs.getInt(3));
            temp.setBoughtDate(rs.getDate(4));
            return temp;
    }catch (Exception e){
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public List<Purchases> getAllPurchasesById(int id) {
       //gets all purchases from a user
        return executeQuery("SELECT * FROM Purchases WHERE idUser = " + id,new Object[] {id});
    }
}
