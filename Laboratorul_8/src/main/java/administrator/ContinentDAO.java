package administrator;

import model.Continent;

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

    private Continent getContinentByResultSet(ResultSet rs, Connection con) throws SQLException {
        boolean hasNext = rs.next();
        int id = hasNext ? rs.getInt("id") : -1;
        String name = hasNext ? rs.getString("name") : null;
        con.close();
        if (id == -1)
            return null;
        return new Continent(id, name);
    }

    public Continent findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from continents where name='" + name + "'")) {
            return getContinentByResultSet(rs, con);
        }
    }
    public Continent findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from continents where id='" + id + "'")) {
            return getContinentByResultSet(rs, con);
        }
    }
}
