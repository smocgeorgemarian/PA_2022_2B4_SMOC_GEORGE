package administrator;

import java.sql.*;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into continents values ((select count(*) + 1 from continents), ?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
        con.commit();
        con.close();
    }

    public Continent findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from continents where name='" + name + "'")) {

            boolean hasNext = rs.next();
            int id = hasNext ? rs.getInt("id") : -1;
            con.close();
            return new Continent(id, name);
        }
    }
    public Continent findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from continents where id='" + id + "'")) {

            boolean hasNext = rs.next();
            String name = hasNext ? rs.getString("name") : null;
            con.close();
            return new Continent(id, name);
        }
    }
}
