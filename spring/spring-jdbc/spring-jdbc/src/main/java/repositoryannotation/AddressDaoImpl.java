package repositoryannotation;

import entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component("addressDao")
public class AddressDaoImpl implements AddressDao {
    private JdbcTemplate jdbcTemplate;

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/springjdbc";
        String username = "ajaykdbadmin";
        String password = "Derebail@1234";

        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void create () {
        String sql = "CREATE TABLE IF NOT EXISTS address ("
                + "addressId INT PRIMARY KEY,"
                + "houseNumber VARCHAR(30) NOT NULL,"
                + "area VARCHAR(30) NOT NULL,"
                + "city VARCHAR(20) NOT NULL,"
                + "state VARCHAR(20) NOT NULL)";
        jdbcTemplate.execute(sql);
    }

    @Override
    public int insert (Address address) {
        String insertQuery = "insert into address(addressId,houseNumber,area,city,state) values (?,?,?,?,?)";
        int result;
        result = this.jdbcTemplate.update(insertQuery, address.getAddressId(), address.getHouseNumber(),
                address.getArea(), address.getCity(), address.getState());
        return result;
    }

    @Override
    public int update (Address address) {
        String updateQuery = "update address set city=? where addressId=?";
        int result;
        result = this.jdbcTemplate.update(updateQuery, address.getCity(),address.getAddressId());
        return result;
    }

    @Override
    public int delete (int addressId) {
        String deleteQuery = "delete from address where addressId=?";
        int result;
        result = this.jdbcTemplate.update(deleteQuery, addressId);
        return result;
    }

    public int insertMultiple (List<Address> addresses) throws SQLException, ClassNotFoundException {
        String insertQuery = "insert into address(addressId,houseNumber,area,city,state) values (?,?,?,?,?)";

        int[] updateCount;
        try(Connection conn = getConnection() ){
            try(PreparedStatement ps = conn.prepareStatement(insertQuery)){
                for(Address address : addresses){
                    ps.setInt(1,address.getAddressId());
                    ps.setString(2,address.getHouseNumber());
                    ps.setString(3,address.getArea());
                    ps.setString(4,address.getCity());
                    ps.setString(5,address.getState());
                    ps.addBatch();
                }

                updateCount = ps.executeBatch();
            }
        }

        return updateCount.length;
    }

    @Autowired
    public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
