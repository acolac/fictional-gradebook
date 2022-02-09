package teme.proiect_final;

import java.time.LocalDate;
import java.util.Objects;

public class STUDENTdto {
    private int id;
    private String sName = "";
    private String fName = "";
    private LocalDate birthDate;
    private String email = "";
    private long cnp;

    public STUDENTdto(int stud_id, String sName, String fName, LocalDate sBirthDate, String sEmail, long idNumber) {
        this.id = stud_id;
        this.sName = sName;
        this.fName = fName;
        this.birthDate = sBirthDate;
        this.email = sEmail;
        this.cnp = idNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        STUDENTdto that = (STUDENTdto) o;
        return id == that.id && cnp == that.cnp && Objects.equals(fName, that.fName) && Objects.equals(sName, that.sName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, sName, birthDate, email, cnp);
    }

    @Override
    public String toString() {
        return "STUDENTdto{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", cnp=" + cnp +
                '}';
    }
}
