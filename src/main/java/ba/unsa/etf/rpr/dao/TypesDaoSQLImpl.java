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
        return Map.of("idTypes", object.getIdTypes(), "typeName", object.getTypeName());
    }

    @Override
    public Types row2object(ResultSet rs) {
        try{
            Types temp=new Types();
            temp.setIdTypes(rs.getInt(1));
            temp.setTypeName(rs.getString(2));
            return temp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Types getTypeById(int id) {
        return executeQueryUnique("SELECT * FROM Types WHERE idTypes = ?", new Object[]{id});
    }

    @Override
    public int getIdByTypeName(String type) {
        return executeQueryUnique("SELECT * FROM Types WHERE typeName = ?", new Object[]{type}).getIdTypes();
    }
}
