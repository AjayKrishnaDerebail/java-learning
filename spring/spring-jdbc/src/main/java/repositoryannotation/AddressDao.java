package repositoryannotation;

import entities.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    public void create();
    public int insert(Address address);
    public int update(Address address);
    public int delete(int addressId);
    public int insertMultiple (List<Address> addresses) throws SQLException, ClassNotFoundException ;
}
