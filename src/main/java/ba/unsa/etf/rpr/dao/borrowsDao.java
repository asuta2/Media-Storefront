package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.borrows;

import java.util.List;

public interface borrowsDao extends dao<borrows>{
    List<borrows> getAllBorrowsById(int id);
}
