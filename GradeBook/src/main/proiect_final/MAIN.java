package teme.proiect_final;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MAIN {

    private static final List<STUDENTdto> students = List.of(
            new STUDENTdto(1, "Ionut", "Andrei", LocalDate.of(2002,6,26), "ionuta@gmail.com", 502062604),
            new STUDENTdto(2, "Marian", "Apostol", LocalDate.of(2004,11,16), "apomarian@gmail.com",504111607),
            new STUDENTdto(3, "Dragos", "Condurache", LocalDate.of(2003,2,4), "condudra@gmail.com",503020622),
            new STUDENTdto(4, "George", "Popa", LocalDate.of(2000,12,1), "popgeo@outlook.com",500120119),
            new STUDENTdto(5, "Sorin", "Parvulescu", LocalDate.of(1998,3,11), "parvulesco@hotmail.com",198031117),
            new STUDENTdto(6, "Matei", "Avrig", LocalDate.of(1994,10,2), "agrigmatei@gmail.com",194100211),
            new STUDENTdto(7, "Corneliu", "Leu", LocalDate.of(1999,9,12), "leuco@gmail.com",199091210),
            new STUDENTdto(8, "Adelin", "Damian", LocalDate.of(1988,3,5), "addedam@hotmail.com",188030541),
            new STUDENTdto(9, "Toma", "Calin", LocalDate.of(1997,11,12), "calito@gmail.com",19711120),
            new STUDENTdto(10, "Denis", "Dobos", LocalDate.of(1991,12,12), "dobomar@gmail.com",197111201)
    );

    private static final List<COURSEdto> courses = List.of(
            new COURSEdto(1, "Curs Project Management"),
            new COURSEdto(2, "Curs Programare Java"),
            new COURSEdto(3, "Curs Programare .NET"),
            new COURSEdto(4, "Curs Programare Python"),
            new COURSEdto(5, "Curs Testare Manuala"),
            new COURSEdto(6, "Curs Testare Automata"),
            new COURSEdto(7, "Curs REACT"),
            new COURSEdto(8, "Curs C/C++")
    );

    private static final List<SCOREdto > grades = List.of(
            new SCOREdto(60,students.get(0), courses.get(0)),
            new SCOREdto(70, students.get(3),courses.get(1)),
            new SCOREdto(100,students.get(4),courses.get(2)),
            new SCOREdto(90,students.get(3),courses.get(3)),
            new SCOREdto(80,students.get(2),courses.get(4)),
            new SCOREdto(70, students.get(2),courses.get(5)),
            new SCOREdto(30,students.get(4),courses.get(6)),
            new SCOREdto(50,students.get(5),courses.get(7)),
            new SCOREdto(50,students.get(6),courses.get(1)),
            new SCOREdto(70,students.get(7),courses.get(2)),
            new SCOREdto(65,students.get(8),courses.get(0)),
            new SCOREdto(40,students.get(9),courses.get(3)),
            new SCOREdto(20,students.get(9),courses.get(5)),
            new SCOREdto(100,students.get(6),courses.get(7)),
            new SCOREdto(90,students.get(8),courses.get(7)),
            new SCOREdto(80,students.get(1),courses.get(0)),
            new SCOREdto(90,students.get(2),courses.get(0)),
            new SCOREdto(100,students.get(3),courses.get(2)),
            new SCOREdto(90,students.get(6),courses.get(3)),
            new SCOREdto(70,students.get(7),courses.get(6)),
            new SCOREdto(80,students.get(9),courses.get(4)),
            new SCOREdto(100,students.get(0),courses.get(7))
    );


    public static void main(String[] args){
        List<SCOREdto> scores = new ArrayList<>();
        MessageScoreBook catalog = new MessageScoreBook(scores);

        //creation tables
        System.out.println("Creating table (if missing)...");
        STUDENTdao.createTable();
        COURSEdao.createTable();
        SCOREdao.createTable();

        //insertion
        System.out.println("Inserting information...");
            for (STUDENTdto student : students) {
            STUDENTdao.insert(student);
            }

            for(COURSEdto course : courses){
            COURSEdao.insert(course);
            }

            for(SCOREdto score : grades){
            SCOREdao.insert(score);
            }

            //get all info from tables
        List<STUDENTdto> allFromStud = STUDENTdao.getAll();
        System.out.println("\n" + allFromStud.size() + " students present in database:");
        allFromStud.forEach(System.out::println);

        List<COURSEdto> allFromCourse = COURSEdao.getAll();
        System.out.println("\n" + allFromCourse.size() + " courses present in database:");
        allFromCourse.forEach(System.out::println);

        List<SCOREdto> allFromScore = SCOREdao.getAll();
        System.out.println("\n" + allFromScore.size() + " scores present in database:");
        allFromScore.forEach(System.out::println);

        //get one by one
        STUDENTdto s1 = STUDENTdao.getById(5);
        STUDENTdto s2 = STUDENTdao.getById(254); //invalid
        System.out.println("\nLoaded student with id " + 5 + ": " + s1);
        System.out.println("Loaded student with id " + 254 + ": " + s2);

        COURSEdto c1 = COURSEdao.getById(8);
        System.out.print("Loaded course with id " + 8 + ": " + c1);

        SCOREdto sc1 = SCOREdao.getByStudentId(10);
        System.out.println("Loaded score for student with ID " + 10 + ":" + sc1);

        List<SCOREdto> scoresFromDb = SCOREdao.getAll();
        MessageScoreBook messageScoreBook = new MessageScoreBook(scoresFromDb);

        boolean doesResponseRecived = false;
        messageScoreBook.getOpenMessageScoreBook();
        while(!doesResponseRecived) {
            System.out.println("Press 1 for show min scores");
            System.out.println("Press 2 for show max scores");
            System.out.println("Press 3 for show average scores");
            System.out.println("Press 4 for exit");
            java.util.Scanner sb = new java.util.Scanner(System.in);
            String resp = sb.next();
            if(resp.equals("1") || resp.equals("2") || resp.equals("3") || resp.equals("4")) {
                System.out.println("We have got your response");
                        doesResponseRecived = true;
            }else{
                System.out.println("Enter either 1,2,3 or 4");
            }
        }
        messageScoreBook.getScoreProcess();

            //messageScoreBook.getMaxScore(); // for Max Score
            //messageScoreBook.getMinScore(); // for Min Score
            //messageScoreBook.getAverageScore(); // for Average Score
            messageScoreBook.getScoreProcess(); // All info's ^^

    //updates for table course
        System.out.println("\nUpdating course with ID 4...");
        COURSEdto updCourse = new COURSEdto(4, "Curs Instalare Windows");
        COURSEdao.update(updCourse);
        System.out.println("\nAfter update, course loaded from db:\n" + COURSEdao.getAll().get(4));


    }

}
