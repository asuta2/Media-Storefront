package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ba.unsa.etf.rpr.dao.UsersDaoSQLImpl.*;

public class MediaDaoImpl implements MediaDao{
    private Connection conn;


    public MediaDaoImpl() {

        try {
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Media getById(int id) {
        return null;
    }

    @Override
    public Media add(Media item) {
        return null;
    }

    @Override
    public Media update(Media item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Media> getMediaByTypeAsc(int id) {
        String upit = "SELECT * FROM Media WHERE typeId = ? order by mediaName asc";
        return getMedia(upit);
    }

    @Override
    public List<Media> getMediaByTypeDesc(int id) {
        String upit = "SELECT * FROM Media WHERE typeId = ? order by mediaName desc";
        return getMedia(upit);
    }

    private List<Media> getMedia(String upit) {
        List<Media> ispis = new ArrayList<Media>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Media mm = new Media();
                mm.setTypeId(rs.getInt("typeId"));
                mm.setIdMedia(rs.getInt("idMedia"));
                mm.setMediaCreator(rs.getString("mediaCreator"));
                mm.setMediaName(rs.getString("mediaName"));
                ispis.add(mm);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ispis;
    }

    @Override
    public List<Media> getAll() {
        String upit = "SELECT * FROM Media";
        List<Media> ispis = new ArrayList<Media>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Media mid = new Media();

                mid.setIdMedia(rs.getInt("idMedia"));
                mid.setMediaName(rs.getString("mediaName"));
                mid.setMediaCreator(rs.getString("mediaCreator"));
                mid.setTypeId(rs.getInt("typeId"));
                ispis.add(mid);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ispis;
    }
}
