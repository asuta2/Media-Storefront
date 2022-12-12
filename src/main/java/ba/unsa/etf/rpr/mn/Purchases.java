package ba.unsa.etf.rpr.mn;

import java.io.Serializable;
import java.util.Date;

public class Purchases implements Serializable {
    private int purchasesId;
    private int userId;
    private int mediaId;
    private Date boughtDate;


    public int getPurchasesId() {
        return purchasesId;
    }

    public void setPurchasesId(int purchasesId) {
        this.purchasesId = purchasesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }



    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }
}
