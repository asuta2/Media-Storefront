package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.mn.Media;

import java.util.List;

public class MediaManager {
    public List<Media> getAll() {
        return DaoFactory.getMediaDao().getAll();
    }
    public void add(Media media) {
        DaoFactory.getMediaDao().add(media);
    }
    public void update(Media media) {
        DaoFactory.getMediaDao().update(media,"idMedia");
    }
    public void delete(int id) {
        DaoFactory.getMediaDao().delete(id,"idMedia");
    }
    public Media getById(int id) {
        return DaoFactory.getMediaDao().getById(id);
    }
    public List<Media> getMediaByTypeAsc(int id) {
        return DaoFactory.getMediaDao().getMediaByTypeAsc(id);
    }
    public List<Media> getMediaByTypeDesc(int id) {
        return DaoFactory.getMediaDao().getMediaByTypeDesc(id);
    }
    public List<Media> getMediaAsc() {
        return DaoFactory.getMediaDao().getMediaAsc();
    }
    public List<Media> getMediaDesc() {
        return DaoFactory.getMediaDao().getMediaDesc();
    }
    public List<Media> getMediaOnSale() {
        return DaoFactory.getMediaDao().getMediaOnSale();
    }
    public List<Media> getMediaByPriceAsc() {
        return DaoFactory.getMediaDao().getMediaByPriceAsc();
    }
    public List<Media> getMediaByPriceDesc() {
        return DaoFactory.getMediaDao().getMediaByPriceDesc();
    }
    public Media getMediaById(int id) {
        return DaoFactory.getMediaDao().getMediaById(id);
    }

}
