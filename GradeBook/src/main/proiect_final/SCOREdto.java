package teme.proiect_final;

public class SCOREdto {
    private COURSEdto course;
    private STUDENTdto student;
    private int grade;

    public SCOREdto(int scorePerStud, STUDENTdto student, COURSEdto course) {
        this.course = course;
        this.student = student;
        this.grade = scorePerStud;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public STUDENTdto getStudent() {
        return student;
    }

    public void setStudent(STUDENTdto student) {
        this.student = student;
    }

    public COURSEdto getCourse() {
        return course;
    }

    public void setCourse(COURSEdto course) {
        this.course = course;
    }
}



