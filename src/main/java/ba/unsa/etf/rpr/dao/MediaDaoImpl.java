package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Media;

import java.sql.*;
import java.util.*;


public class MediaDaoImpl extends AbstractDao<Media> implements MediaDao{


    public MediaDaoImpl() {
        super("Media");
    }

    @Override
    public Media getById(int id) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Media object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("idMedia", object.getIdMedia());
        row.put("mediaName", object.getMediaName());
        row.put("mediaCreator", object.getMediaCreator());
        row.put("typeId", object.getTypeId());
        row.put("Sales_pct", object.getSales_pct());
        row.put("Price", object.getPrice());
        row.put("Description", object.getDescription());
        return row;
    }

    @Override
    public Media row2object(ResultSet rs) {
        try {
            Media cat = new Media();
            cat.setIdMedia(rs.getInt("idMedia"));
            cat.setMediaName(rs.getString("mediaName"));
            cat.setMediaCreator(rs.getString("mediaCreator"));
            cat.setTypeId(rs.getInt("typeId"));
            cat.setSales_pct(rs.getDouble("Sales_pct"));
            cat.setPrice(rs.getDouble("Price"));
            cat.setDescription(rs.getString("Description"));
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Media> getMediaByTypeAsc(int id) {
        return executeQuery("SELECT * FROM Media WHERE typeId = ? ORDER BY mediaName ASC", new Object[]{id});

    }

    @Override
    public List<Media> getMediaByTypeDesc(int id) {
        return executeQuery("SELECT * FROM Media WHERE typeId = ? ORDER BY mediaName DESC", new Object[]{id});
    }

    @Override
    public List<Media> getMediaAsc() {
        return executeQuery("SELECT * FROM Media ORDER BY mediaName ASC", new Object[]{});
    }

    @Override
    public List<Media> getMediaDesc() {
        return executeQuery("SELECT * FROM Media ORDER BY mediaName DESC", new Object[]{});
    }

    @Override
    public List<Media> getMediaOnSale() {
        return executeQuery("SELECT * FROM Media WHERE Sales_pct > 0 order by Sales_pct asc", new Object[]{});
    }

    @Override
    public List<Media> getMediaByPriceAsc() {
        return executeQuery("SELECT * FROM Media order by Price asc", new Object[]{});
    }

    @Override
    public List<Media> getMediaByPriceDesc() {
        return executeQuery("SELECT * FROM Media order by Price desc", new Object[]{});
    }

    @Override
    public Media getMediaById(int id) {
        return executeQueryUnique("SELECT * FROM Media WHERE idMedia = ?", new Object[]{id});
    }

    private List<Media> getMedia(String upit) {
        List<Media> ispis = new ArrayList<>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Media mm = new Media();
                mm.setTypeId(rs.getInt("typeId"));
                mm.setIdMedia(rs.getInt("idMedia"));
                mm.setMediaCreator(rs.getString("mediaCreator"));
                mm.setMediaName(rs.getString("mediaName"));
                mm.setSales_pct(rs.getDouble("Sales_pct"));
                mm.setPrice(rs.getDouble("price"));
                mm.setDescription(rs.getString("mediaDescription"));
                ispis.add(mm);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ispis;
    }
}
