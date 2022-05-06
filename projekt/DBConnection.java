package projekt;

import java.sql.*;
import java.util.HashSet;
import java.sql.DriverManager;
import java.sql.Connection;


public class DBConnection {
    private Connection connection;

    public boolean getDBConnection() { // connect to DB
        if (connection == null) ;
        {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:db/myDatabase.db"); //sem treba dat cestu kde sa uklada .db subor
                createTableStudT();
                createTableStudH();
                createTableStudK();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return  false;
            }
        }
        return true;
    }

    public void closeConnection() { //prerusi connection do db
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTableStudT() //vytvori tabulku studentov tech oboru
    {

        String sql = "CREATE TABLE IF NOT EXISTS studt (ID int, name varchar(50), surname varchar(50), day int, month int, year int);";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean resetTableStudT() //premaze tabulku studentov tech oboru
    {
        if (connection==null)
            return false;
        String sql = "DROP TABLE IF EXISTS studt;";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void createTableStudH()// to iste co pri studT
    {

        String sql = "CREATE TABLE IF NOT EXISTS studh (ID int, name varchar(50), surname varchar(50), day int, month int, year int);";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean resetTableStudH()//to iste co pri studT
    {
        if (connection==null)
            return false;
        String sql = "DROP TABLE IF EXISTS studh;";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void createTableStudK() //vytvori tabulku studentov kom oboru
    {

        String sql = "CREATE TABLE IF NOT EXISTS studk (ID int, name varchar(50), surname varchar(50), day int, month int, year int);";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean resetTableStudK() //premaze tabulku studentov kom oboru
    {
        if (connection==null)
            return false;
        String sql = "DROP TABLE IF EXISTS studk;";
        try
        {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void getDB(Database myDatabase) //nacita z sql databaze
    {

        this.createTableStudT();
        this.createTableStudH();
        this.createTableStudK();
        myDatabase.deleteDatabase();
        String sql = "SELECT * FROM studt;";  //nacitanie z tabulky studentov tech oboru
        try
        {
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql);
            while (rs1.next()) {
                int ID = rs1.getInt("ID");
                String name = rs1.getString("name");
                String surname = rs1.getString("surname");
                int day = rs1.getInt("day");
                int month = rs1.getInt("month");
                int year = rs1.getInt("year");
                myDatabase.addStudentTech(ID, name, surname, day, month, year);
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sql = "SELECT * FROM studh;"; // nacitanie z tabulky studentov hum oboru
        try
        {
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                int ID = rs2.getInt("ID");
                String name = rs2.getString("name");
                String surname = rs2.getString("surname");
                int day = rs2.getInt("day");
                int month = rs2.getInt("month");
                int year = rs2.getInt("year");
                myDatabase.addStudentHum(ID, name, surname, day, month, year);
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        sql = "SELECT * FROM studk;"; // nacitanie z tabulky studentov kom oboru
        try
        {
            Statement stmt3 = connection.createStatement();
            ResultSet rs3 = stmt3.executeQuery(sql);
            while (rs3.next()) {
                int ID = rs3.getInt("ID");
                String name = rs3.getString("name");
                String surname = rs3.getString("surname");
                int day = rs3.getInt("day");
                int month = rs3.getInt("month");
                int year = rs3.getInt("year");
                myDatabase.addStudentKom(ID, name, surname, day, month, year);
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveDB(Database myDatabase) //uklada do sql databaze
    {
        this.resetTableStudT();
        this.resetTableStudH();
        this.resetTableStudK();
        createTableStudT();
        createTableStudH();
        createTableStudK();
        HashSet<Student> studentsT = myDatabase.getStudentsT();
        String sql = "INSERT INTO studt (ID, name, surname, day, month , year) VALUES (?,?,?,?,?,?)"; //ulozenie studentov tech oboru
        try
        {
            for (Student o: studentsT)
            {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, o.getID());
                pstmt.setString(2, o.getName());
                pstmt.setString(3, o.getSurname());
                pstmt.setInt(4, o.getDay());
                pstmt.setInt(5, o.getMonth());
                pstmt.setInt(6, o.getYear());
                pstmt.executeUpdate();
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        HashSet<Student> studentsH = myDatabase.getStudentsH();
        String sqll = "INSERT INTO studh (ID, name, surname, day, month , year) VALUES (?,?,?,?,?,?)"; //ulozenie studentov hum oboru
        try
        {
            for (Student o: studentsH)
            {
                PreparedStatement pstmtt = connection.prepareStatement(sqll);
                pstmtt.setInt(1, o.getID());
                pstmtt.setString(2, o.getName());
                pstmtt.setString(3, o.getSurname());
                pstmtt.setInt(4, o.getDay());
                pstmtt.setInt(5, o.getMonth());
                pstmtt.setInt(6, o.getYear());
                pstmtt.executeUpdate();
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        HashSet<Student> studentsK = myDatabase.getStudentsK();
        String sqlll = "INSERT INTO studk (ID, name, surname, day, month , year) VALUES (?,?,?,?,?,?)"; //ulozenie studentov kom oboru
        try
        {
            for (Student o: studentsK)
            {
                PreparedStatement pstmttt = connection.prepareStatement(sqlll);
                pstmttt.setInt(1, o.getID());
                pstmttt.setString(2, o.getName());
                pstmttt.setString(3, o.getSurname());
                pstmttt.setInt(4, o.getDay());
                pstmttt.setInt(5, o.getMonth());
                pstmttt.setInt(6, o.getYear());
                pstmttt.executeUpdate();
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }



}
