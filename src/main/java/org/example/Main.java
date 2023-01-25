package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    private final String URL = "jdbc:postgresql://localhost:5432/Academy";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

}
    public int getStudents() {
        String SQL = "SELECT count(*) FROM students";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL))
        {        rs.next();
            count = rs.getInt(1);    }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return count;
    }

    public List<String> getStudentsByLetter() {
        List<String> names = new ArrayList<>();
        String SQL = "SELECT * FROM students where name like '%a%'";
        String name = "";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                name = rs.getString("name");
                names.add(name);
            }
        }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return names;
    }

    public int maxAge() {
        String SQL = "SELECT MAX(age) FROM students";
        int age = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL))
        {        rs.next();
            age = rs.getInt(1);    }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return age;
    }
    public int getAverageGrade() {
        String SQL = "SELECT avg(grade) FROM students";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL))
        {        rs.next();
            count = rs.getInt(1);    }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return count;
    }
    public int minAge() {
        String SQL = "SELECT min(age) FROM students";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL))
        {        rs.next();
            count = rs.getInt(1);    }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return count;
    }
    public int getSumOfScholarship() {
        String SQL = "SELECT sum(scholarship) FROM students";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL))
        {        rs.next();
            count = rs.getInt(1);    }
        catch (SQLException ex)
        {        System.out.println(ex.getMessage());    }
        return count;
    }



    public static void main (String[] args){
        Main main = new Main();
        List<String> names = main.getStudentsByLetter();
        System.out.println("Студенты с буквой \'а\' в имени: ");
        for(String e: names){
            System.out.println(e);
        }
        System.out.println();
        System.out.println("Максимальный возраст студента: "+main.maxAge());
        System.out.println("Средняя оценка студентов: "+main.getAverageGrade());
        System.out.println("Количество всех студентов в списке: "+main.getStudents());
        System.out.println("Минимальный возраст студента: "+main.minAge());
        System.out.println("Сумма всех стипендий: "+main.getSumOfScholarship());
    }

}