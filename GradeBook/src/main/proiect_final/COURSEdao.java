package teme.proiect_final;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class COURSEdao {
    private static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection("jdbc:sqlite:proiect_final.db", config.toProperties());
    }

    public static void createTable(){
        String sql = "create table COURSE (\n" +
                "id integer primary key,\n" +
                "DESCRIPTION text not null unique\n" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
    }

    public static void insert(COURSEdto course) {

        String sql = "insert into COURSE(id, description)"
                + "values (?,?)";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getDescription());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while inserting course " + course + " : " + e.getMessage());
        }
    }

    public static List<COURSEdto> getAll() {

        String sql = "select * from COURSE order by id";

        List<COURSEdto> coursEdtoList = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                coursEdtoList.add(extractCOURSEdto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while selecting all courses: " + e.getMessage());
        }

        return coursEdtoList;
    }

    public static COURSEdto getById(int id) {

        String sql = "select * from COURSE where id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return extractCOURSEdto(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while selecting course " + id + " : " + e.getMessage());
        }

        return null;
    }

    private static COURSEdto extractCOURSEdto(ResultSet rs) throws SQLException {
        return new COURSEdto(
                rs.getInt("id"),
                rs.getString("description"));
    }

    public static void update(COURSEdto course) {

        String sql = "update COURSE " +
                "SET id=?, description=? " +
                "WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getDescription());


            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while updating courses " + course + " : " + e.getMessage());
        }
    }
}

