package administrator;

import model.Country;

import java.sql.*;

public class CountryDAO {

    public void create(String name, String code, String continent) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries values ((select count(*) + 1 from countries), ?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setString(3, continent);
            pstmt.executeUpdate();
        }
    }

    private Country getCountryByResultSet(ResultSet rs) throws SQLException {
        boolean hasNext = rs.next();
        int id = hasNext ? rs.getInt("id") : -1;
        String name = hasNext ? rs.getString("name") : null;
        String code = hasNext ? rs.getString("continent") : null;
        String continent = hasNext ? rs.getString("continent") : null;
        if (id == -1)
            return null;
        return new Country(id, name, code, continent);
    }

    public Country findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where name='" + name + "'")) {
            return getCountryByResultSet(rs);
        }
    }

    public Country findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where id=" + id)) {
            return getCountryByResultSet(rs);
        }
    }

    public Country findByCode(String code) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where code='" + code + "'")) {
            return getCountryByResultSet(rs);
        }
    }
}
