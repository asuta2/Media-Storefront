package ba.unsa.etf.rpr.mn;

import java.io.Serializable;

public class Types implements Serializable {
    /**
     * Types class is used to store information about types of media.
     */
    private int idTypes;
    private String typeName;

    public Types() {
    }

    public int getIdTypes() {
        return idTypes;
    }

    public void setIdTypes(int idTypes) {
        this.idTypes = idTypes;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
