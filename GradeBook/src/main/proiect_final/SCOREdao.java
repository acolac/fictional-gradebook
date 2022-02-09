package teme.proiect_final;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SCOREdao {
    private static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection("jdbc:sqlite:proiect_final.db", config.toProperties());
    }

    public static void createTable(){
        String sql = "create table SCORE (\n" +
                "COURSE_ID integer not null references COURSE(id),\n" +
                "STUD_ID integer not null references STUDENT(id),\n" +
                "GRADE integer not null,\n" +
                "primary key (COURSE_ID, STUD_ID)\n" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
    }

    public static void insert(SCOREdto score) {

        String sql = "insert into COURSE(COURSE_ID, STUD_ID, GRADE)"
                + "values (?,?,?)";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, score.getCourse());
            pstmt.setObject(2, score.getStudent());
            pstmt.setInt(3, score.getGrade());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while inserting score " + score + " : " + e.getMessage());
        }
    }

    public static void update(SCOREdto score) {

        String sql = "update SCORE " +
                "SET GRADE=?" +
                "WHERE STUD_ID=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, score.getCourse());
            pstmt.setObject(2, score.getStudent());
            pstmt.setInt(3, score.getGrade());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("Error while updating grades " + score + " : " + e.getMessage());
        }
    }

    public static List<SCOREdto> getAll() {

        String sql = "select * from SCORE order by STUD_ID";

        List<SCOREdto> scorEdtoList = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                scorEdtoList.add(extractSCOREdto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while selecting all grades: " + e.getMessage());
        }

        return scorEdtoList;
    }

    private static SCOREdto extractSCOREdto(ResultSet rs) throws SQLException {
        int grade = rs.getInt("GRADE");

        int courseId = rs.getInt("COURSE_ID");
        COURSEdto courseDto = COURSEdao.getById(courseId);

        int studentId = rs.getInt("STUD_ID");
        STUDENTdto studentDto = STUDENTdao.getById(studentId);

        return new SCOREdto(grade, studentDto, courseDto);

    }

    public static SCOREdto getByStudentId(int studentId) {

        String sql = "select * from SCORE where STUD_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return extractSCOREdto(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while selecting grades for student with ID: " + studentId + " : " + e.getMessage());
        }

        return null;
    }
}
