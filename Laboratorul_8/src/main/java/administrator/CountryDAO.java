package administrator;

import java.sql.*;

public class CountryDAO {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";
    private static final String CONTINENT = "continent";

    public void create(String name, String code, int continentId) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries values ((select count(*) + 1 from countries), ?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continentId);
            pstmt.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public Country findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where name='" + name + "'")) {

            boolean hasNext = rs.next();
            int id = hasNext ? rs.getInt("id") : -1;
            String code = hasNext ? rs.getString("code") : null;
            int continent = hasNext ? rs.getInt("continent") : null;
            con.close();
            return new Country(id, name, code, continent);
        }
    }
    public Country findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where id=" + id)) {

            boolean hasNext = rs.next();
            String name = hasNext ? rs.getString("name") : null;
            String code = hasNext ? rs.getString("code") : null;
            int continent = hasNext ? rs.getInt("continent") : null;
            con.close();
            return new Country(id, name, code, continent);
        }
    }

    public Country findByCode(String code) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where code='" + code + "'")) {

            boolean hasNext = rs.next();
            int id = hasNext ? rs.getInt("id") : null;
            String name = hasNext ? rs.getString("name") : null;
            int continent = hasNext ? rs.getInt("continent") : null;
            con.close();
            return new Country(id, name, code, continent);
        }
    }
}
