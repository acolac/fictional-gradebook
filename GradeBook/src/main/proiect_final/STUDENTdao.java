package teme.proiect_final;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class STUDENTdao {
    private static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection("jdbc:sqlite:proiect_final.db", config.toProperties());
    }

    public static void createTable(){
        String sql = "create table STUDENT (\n" +
                "id integer primary key,\n" +
                "SNAME varchar (50) not null unique,\n" +
                "FNAME varchar (50) not null unique,\n" +
                "BIRTH_DATE datetime,\n" +
                "EMAIL text,\n" +
                "CNP integer not null unique\n" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
    }

    public static void insert(STUDENTdto student) {

        String sql = "insert into STUDENT(id, SNAME, FNAME, BIRTH_DATE, EMAIL, CNP)"
                + "values (?,?,?,?,?,?)";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getsName());
            pstmt.setString(3, student.getfName());
            pstmt.setDate(4, Date.valueOf(student.getBirthDate()));
            pstmt.setString(5, student.getEmail());
            pstmt.setLong(6, student.getCnp());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while inserting person " + student + " : " + e.getMessage());
        }
    }

    public static void update(STUDENTdto student) {

        String sql = "update STUDENT " +
                "SET id=?, SNAME=?, FNAME=?, BIRTH_DATE=?, EMAIL=?, CNP=? " +
                "WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getsName());
            pstmt.setString(3, student.getfName());
            pstmt.setDate(4, Date.valueOf(student.getBirthDate()));
            pstmt.setString(5, student.getEmail());
            pstmt.setLong(6, student.getCnp());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while updating person " + student + " : " + e.getMessage());
        }
    }

    public static void delete(int id) {

        String sql = "delete from STUDENT where id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while deleting person " + id + " : " + e.getMessage());
        }
    }

    public static void deleteAll() {

        String sql = "delete from STUDENT";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error while deleting all persons: " + e.getMessage());
        }
    }

        public static List<STUDENTdto> getAll() {

            String sql = "select * from STUDENT order by id";

            List<STUDENTdto> studenTdtoList = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    studenTdtoList.add(extractSTUDENTdto(rs));
                }
            } catch (SQLException e) {
                System.out.println("Error while selecting all students: " + e.getMessage());
            }

            return studenTdtoList;
        }

    private static STUDENTdto extractSTUDENTdto(ResultSet rs) throws SQLException {
        return new STUDENTdto(
                rs.getInt("id"),
                rs.getString("SNAME"),
                rs.getString("FNAME"),
                rs.getDate("BIRTH_DATE").toLocalDate(),
                rs.getString("EMAIL"),
                rs.getLong("CNP"));
    }

    public static STUDENTdto getById(int id) {

        String sql = "select * from STUDENT where id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return extractSTUDENTdto(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while selecting student " + id + " : " + e.getMessage());
        }

        return null;
    }

}




