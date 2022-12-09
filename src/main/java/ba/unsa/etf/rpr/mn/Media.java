package ba.unsa.etf.rpr.mn;

public class Media {
    private int idMedia;
    private String mediaName;
    private String mediaCreator;
    private int typeId;

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public void setMediaCreator(String mediaCreator) {
        this.mediaCreator = mediaCreator;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    public int getIdMedia() {
        return idMedia;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getMediaCreator() {
        return mediaCreator;
    }

    public int getTypeId() {
        return typeId;
    }



}
