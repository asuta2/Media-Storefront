package ba.unsa.etf.rpr.mn;

import java.io.Serializable;

public class Media implements Serializable {
    /**
     * Media class is used to store information about media.
     */
    private int idMedia;
    private String mediaName;
    private String mediaCreator;
    private int typeId;
    private Double Sales_pct;
    private Double Price;
    private String Description;

    public Media() {
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public Double getSales_pct() {
        return Sales_pct;
    }
    public void setSales_pct(Double sales_pct) {
        Sales_pct = sales_pct;
    }

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
    @Override
    public String toString() {
        return mediaName + " " + mediaCreator + " " + Math.round((Price-Price*Sales_pct/100.00)*100)/100.00 + "$";
    }



}
