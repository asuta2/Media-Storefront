package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Types;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class TypesDaoSQLImpl extends AbstractDao<Types> implements TypesDao {

    public TypesDaoSQLImpl() {
        super("Types");
    }



    @Override
    public Map<String, Object> object2row(Types object) {
        return null;
    }

    @Override
    public Types row2object(ResultSet rs) {
        return null;
    }


}
