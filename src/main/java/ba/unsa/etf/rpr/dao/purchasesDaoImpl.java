package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Purchases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class purchasesDaoImpl implements PurchasesDao {
    private Connection conn;
    public purchasesDaoImpl() {
        try{
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("conn.properties").openStream());
            conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Purchases getById(int id) {
        String upit = "SELECT * FROM borrows WHERE borrowsId = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Purchases bb = new Purchases();
                bb.setPurchasesId(rs.getInt("borrowsId"));
                bb.setBoughtDate(rs.getDate("boughtDate"));
                bb.setMediaId(rs.getInt("mediaId"));
                bb.setUserId(rs.getInt("userId"));
                rs.close();
                return bb;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Purchases add(Purchases item) {
        return null;
    }

    @Override
    public Purchases update(Purchases item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Purchases> getAll() {
        return null;
    }

    @Override
    public List<Purchases> getAllPurchasesById(int id) {
        String upit = "SELECT * FROM borrows WHERE borrowsId = ?";
        List<Purchases> ispis = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Purchases bb = new Purchases();
                bb.setPurchasesId(rs.getInt("borrowsId"));
                bb.setBoughtDate(rs.getDate("boughtDate"));
                bb.setMediaId(rs.getInt("mediaId"));
                bb.setUserId(rs.getInt("userId"));
                ispis.add(bb);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ispis;
    }
}
