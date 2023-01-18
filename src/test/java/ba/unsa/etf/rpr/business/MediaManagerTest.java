package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.MediaDaoImpl;
import ba.unsa.etf.rpr.mn.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MediaManagerTest {
    private MediaManager mediaManager;
    private Media media;
    private MediaDaoImpl mediaDao;
    private List<Media> mediaList;
    @BeforeEach
    void setUp() {
        mediaManager = Mockito.mock(MediaManager.class);
        media = new Media();
        media.setMediaName("test");
        media.setMediaCreator("test");
        media.setDescription("test");
        media.setSales_pct(0.0);
        media.setPrice(0.0);
        media.setTypeId(2);

        mediaDao = Mockito.mock(MediaDaoImpl.class);
        mediaList = new ArrayList<>();
        mediaList.add(media);
    }
    @Test
    void addMediaTest(){
        mediaManager.add(media);
        Mockito.verify(mediaManager,Mockito.times(1)).add(media);
    }
    @Test
    void updateMediaTest(){
        mediaManager.update(media);
        Mockito.verify(mediaManager,Mockito.times(1)).update(media);
    }
    @Test
    void deleteMediaTest(){
        mediaManager.delete(media.getIdMedia());
        Mockito.verify(mediaManager,Mockito.times(1)).delete(media.getIdMedia());
    }
    @Test
    void getAllMediaTest(){
        Mockito.when(mediaManager.getAll()).thenReturn(mediaList);
        List<Media> list = mediaManager.getAll();
        assertNotNull(list);
        assertEquals(1,list.size());
        assertEquals("test",list.get(0).getMediaName());
    }
}
