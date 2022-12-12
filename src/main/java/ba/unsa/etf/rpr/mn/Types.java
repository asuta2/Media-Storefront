package ba.unsa.etf.rpr.mn;

import java.io.Serializable;

public class Types implements Serializable {
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
