package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.borrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static ba.unsa.etf.rpr.dao.UsersDaoSQLImpl.*;

public class borrowsDaoImpl implements borrowsDao{
    private Connection conn;
    public borrowsDaoImpl() {
        try{
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public borrows getById(int id) {
        return null;
    }

    @Override
    public borrows add(borrows item) {
        return null;
    }

    @Override
    public borrows update(borrows item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<borrows> getAll() {
        return null;
    }

    @Override
    public List<borrows> getAllBorrowsById(int id) {
        String upit = "SELECT * FROM borrows WHERE borrowsId = ?";
        List<borrows> ispis = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                borrows bb = new borrows();
                bb.setBorrowsId(rs.getInt("borrowsId"));
                bb.setBroughtDate(rs.getDate("broughtDate"));
                bb.setTakeDate(rs.getDate("broughtDate"));
                bb.setMediaId(rs.getInt("mediaId"));
                bb.setUserId(rs.getInt("userId"));
                ispis.add(bb);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ispis;
    }
}
