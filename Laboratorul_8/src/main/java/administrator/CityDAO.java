package administrator;

import model.City;

import java.sql.*;

public class CityDAO {
    public void create(String country, String name, boolean isCapital, double latitude,
                       double longitude) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into cities values ((select count(*) + 1 from cities), ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, country);
            pstmt.setString(2, name);
            pstmt.setInt(3, (isCapital ? 1 : 0));
            pstmt.setDouble(4, latitude);
            pstmt.setDouble(5, longitude);
            pstmt.executeUpdate();
        }
    }

    private City getCityByResultSet(ResultSet rs) throws SQLException {
        boolean hasNext = rs.next();
        int id = hasNext ? rs.getInt("id") : -1;
        String country = hasNext ? rs.getString("country") : null;
        String name = hasNext ? rs.getString("name") : null;
        int capital = hasNext ? rs.getInt("capital") : -1;
        int latitude = hasNext ? rs.getInt("latitude") : -1;
        int longitude = hasNext ? rs.getInt("longitude") : -1;
        if (id == -1)
            return null;
        return new City(id, country, name, capital == 1, latitude, longitude);
    }

    public City findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from cities where name='" + name + "'")) {
            return getCityByResultSet(rs);
        }
    }
    public City findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from cities where id='" + id + "'")) {
            return getCityByResultSet(rs);
        }
    }
}
