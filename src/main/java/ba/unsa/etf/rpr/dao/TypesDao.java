package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.mn.Types;

public interface TypesDao extends dao<Types>{
    Types getTypeById(int id);
    int getIdByType(String type);
}
