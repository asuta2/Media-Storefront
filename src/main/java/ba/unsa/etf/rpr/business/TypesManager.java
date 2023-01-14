package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.mn.Types;

import java.util.List;

public class TypesManager {
    public static void add(Types type) {
        DaoFactory.getTypesDao().add(type);
    }
    public static void update(Types type) {
        DaoFactory.getTypesDao().update(type,"idTypes");
    }
    public static void delete(int id) {
        DaoFactory.getTypesDao().delete(id,"idTypes");
    }
    public static Types getTypeById(int id) {
        return DaoFactory.getTypesDao().getTypeById(id);
    }
    public static int getIdByTypeName(String type) {
        return DaoFactory.getTypesDao().getIdByTypeName(type);
    }
    public List<Types> getAll() {
        return DaoFactory.getTypesDao().getAll();
    }

}
