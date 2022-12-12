package ba.unsa.etf.rpr.mn;

import java.io.Serializable;
import java.util.Date;

public class borrows implements Serializable {
    private int borrowsId;
    private int userId;
    private int mediaId;
    private Date takeDate;
    private Date broughtDate;

    public borrows() {}

    public int getBorrowsId() {
        return borrowsId;
    }

    public void setBorrowsId(int borrowsId) {
        this.borrowsId = borrowsId;
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

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getBroughtDate() {
        return broughtDate;
    }

    public void setBroughtDate(Date broughtDate) {
        this.broughtDate = broughtDate;
    }
}
