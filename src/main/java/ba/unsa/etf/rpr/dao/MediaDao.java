package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Media;

import java.util.List;

public interface MediaDao extends dao<Media>{
    List<Media> getMediaByTypeAsc(int id);
    List<Media> getMediaByTypeDesc(int id);
    List<Media> getMediaAsc();
    List<Media> getMediaDesc();
    List<Media> getMediaOnSale();
}
