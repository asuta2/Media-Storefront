package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final MediaDao mediaDao = new MediaDaoImpl();
    private static final TypesDao typesDao = new TypesDaoSQLImpl();
    private static final PurchasesDao purchasesDao = new purchasesDaoImpl();
    private static final UsersDao usersDao = new UsersDaoSQLImpl();
    private DaoFactory() {
    }
    public static MediaDao getMediaDao() {
        return mediaDao;
    }
    public static TypesDao getTypesDao() {
        return typesDao;
    }
    public static PurchasesDao getPurchasesDao() {
        return purchasesDao;
    }
    public static UsersDao getUsersDao() {
        return usersDao;
    }
}
