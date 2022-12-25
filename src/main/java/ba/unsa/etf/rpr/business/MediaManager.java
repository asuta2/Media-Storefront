package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.mn.Media;

import java.util.List;

public class MediaManager {
    public List<Media> getAll() {
        return DaoFactory.getMediaDao().getAll();
    }
    public void addMedia(Media media) {
        DaoFactory.getMediaDao().add(media);
    }
    public void updateMedia(Media media) {
        DaoFactory.getMediaDao().update(media);
    }
    public void deleteMedia(int id) {
        DaoFactory.getMediaDao().delete(id);
    }
    public Media getMediaById(int id) {
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

}
