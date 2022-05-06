package projekt;

import java.io.*;
import java.util.*;

public class Database implements Serializable {


    public Database() {
        databaseComponents = new HashMap<Integer, Student>();
    }

    private HashMap<Integer, Student> databaseComponents;


    public void addStudentTech(int ID, String name, String surname, int day, int month, int year) //pridanie studenta do technickeho oboru
    {
        StudentTech studT = new StudentTech(ID, name, surname, day, month, year);
        this.databaseComponents.put(ID, studT);

    }



    public void addStudentHum(int ID, String name, String surname, int day, int month, int year) //pridanie studenta do humanitarneho oboru
    {
        StudentHum studH = new StudentHum(ID, name, surname, day, month, year);
        this.databaseComponents.put(ID, studH);

    }

    public void addStudentKom(int ID, String name, String surname, int day, int month, int year) //pridanie studenta do kombinovaneho oboru
    {
        StudentKom studK = new StudentKom(ID, name, surname, day, month, year);
        this.databaseComponents.put(ID, studK);

    }


    public Student getStudent(int ID) //najdenie studenta podla ID
    {
        return databaseComponents.get(ID);
    }

    public void addGrades(int ID, double grade) {  //pridanie znamky studentovi podla ID
        if (databaseComponents.containsKey(ID)) {
            databaseComponents.get(ID).newGrade(grade);
        } else {
            System.out.println("Student s ID " + ID + " neexistuje");
        }

    }

    public boolean deleteStudent(int ID) {  //vymazanie studenta
        if (databaseComponents.containsKey(ID)) {
            Student temp = this.getStudent(ID);
            databaseComponents.remove(ID);
            return true;
        }
        return false;
    }


    public void StudentsByAlphabet() {   //zoradenie studentov abecedne podla priezviska

        ArrayList<Student> studentiT = new ArrayList<>(); // studenti technickeho oboru
        ArrayList<Student> studentiH = new ArrayList<>(); // studenti humanitarneho oboru
        ArrayList<Student> studentiK = new ArrayList<>(); //studenti kombinovaneho oboru
        Iterator<Integer> iter = databaseComponents.keySet().iterator();
        while (iter.hasNext()) {

            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentTech) { //pre studentov technickeho oboru
                studentiT.add(temp);
            } else if (temp instanceof StudentHum) { //pre studentov humanitarneho oboru
                studentiH.add(temp);
            } else if (temp instanceof StudentKom) { //pre studentov kombinovaneho oboru
                studentiK.add(temp);
            }

        }
        Collections.sort(studentiT);
        Collections.sort(studentiH);
        Collections.sort(studentiK);
        System.out.println("Studenti technickeho oboru:");
        if (studentiT.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        for (Student s : studentiT) {
            System.out.println("Priezvisko: " + s.getSurname() + "\tMeno:" + s.getName() + "\tID:" + s.getID() + " \tDatum narodenia:" + s.getDay() + "." + s.getMonth() + "." + s.getYear() + "\tPriemer: " + s.getAverage());
        }
        System.out.println("\nStudenti humanitarneho oboru:");
        if (studentiH.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        for (Student h : studentiH) {
            System.out.println("Priezvisko: " + h.getSurname() + "\tMeno:" + h.getName() + "\tID:" + h.getID() + " \tDatum narodenia:" + h.getDay() + "." + h.getMonth() + "." + h.getYear() + "\tPriemer: " + h.getAverage());

        }
        System.out.println("\nStudenti kombinovaneho oboru:");
        if (studentiK.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        for (Student k : studentiK) {
            System.out.println("Priezvisko: " + k.getSurname() + "\tMeno:" + k.getName() + "\tID:" + k.getID() + " \tDatum narodenia:" + k.getDay() + "." + k.getMonth() + "." + k.getYear() + "\tPriemer: " + k.getAverage());

        }
    }

    public void numberOfStudents() {   //pocet studentov v obore

        ArrayList<Student> studentiT = new ArrayList<>(); // studenti technickeho oboru
        ArrayList<Student> studentiH = new ArrayList<>(); // studenti humanitarneho oboru
        ArrayList<Student> studentiK = new ArrayList<>(); //studenti kombinovaneho oboru
        Iterator<Integer> iter = databaseComponents.keySet().iterator();
        while (iter.hasNext()) {

            int key = iter.next(); //iterator prechadza databazu a podla toho sa pridelia studenti do arraylistov
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentTech) { //pre studentov technickeho oboru
                studentiT.add(temp);
            }
            if (temp instanceof StudentHum) { //pre studentov humanitarneho oboru
                studentiH.add(temp);
            }
            if (temp instanceof StudentKom) { //pre studentov kombinovaneho oboru
                studentiK.add(temp);
            }
        }
        System.out.println("Tehcnicky obor:");
        if (studentiT.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        else {
            System.out.println("Pocet studentov v technickom obore je: " + studentiT.size());
        }
        System.out.println("\nHumanitarny obor:");
        if (studentiH.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        else {
            System.out.println("Pocet studentov v humanitarnom obore je: " + studentiH.size());
        }
        System.out.println("\nKombinovany obor:");
        if (studentiK.isEmpty())
            System.out.println("V tomto obore nie su ziadni studenti");
        else {
            System.out.println("Pocet studentov v kombinovanom obore je: " + studentiK.size());
        }
    }

    public void saveToFile(String savename) throws IOException { //serializacia
        try (FileOutputStream f = new FileOutputStream(savename);
             ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(databaseComponents);
        }



    }


    public  int readID(){  //nacita najvacsie ID z databaze a zacne indexovat od neho
        int id =0;
        for (int i : databaseComponents.keySet()){
             id = i;
        }
        return id+=1;       //+1 aby nezacalo indexovat od najvacsieho ID ale od jedneho vacsieho nez max ID, inak by prepisalo studenta ktory tam uz je
    }


    public void readFromFile(String loadname) throws IOException, ClassNotFoundException {
        try (FileInputStream in = new FileInputStream(loadname);
             ObjectInputStream s = new ObjectInputStream(in)) {
            databaseComponents = (HashMap<Integer, Student>) s.readObject();
        }


    }


    public float studentsTAverage() {
        ArrayList<Student> studentiT = new ArrayList<>(); // studenti technickeho oboru
        Iterator<Integer> iter = databaseComponents.keySet().iterator();

        while (iter.hasNext()) {

            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentTech) { //pre studentov technickeho oboru
                studentiT.add(temp);
            }
        }

        float numOfStudentsT = studentiT.size();
        float sum = 0;
        if (numOfStudentsT != 0) {
                for (Student stud: studentiT) //foreach loop ktory scita priemery studentov do jedneho cisla
                    sum += stud.getAverage();
                float totalAverage = sum / numOfStudentsT;  //delenie scitanych vsetky priemerov poctom studentov
                return totalAverage;
            }
            else {
                return 0;
        }
    }


    public float studentsHAverage() {
        ArrayList<Student> studentiH = new ArrayList<>(); // studenti technickeho oboru
        Iterator<Integer> iter = databaseComponents.keySet().iterator();

        while (iter.hasNext()) {

            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentHum) { //pre studentov technickeho oboru
                studentiH.add(temp);
            }
        }

        float numOfStudentsH = studentiH.size();
        float sum = 0;
        if (numOfStudentsH != 0) {
            for (Student stud: studentiH) //foreach loop ktory scita priemery studentov do jedneho cisla
                sum += stud.getAverage();
            float totalAverage = sum / numOfStudentsH;
            return totalAverage;
        }
        else {
            return 0;
        }
    }

    public float studentsKAverage() {
        ArrayList<Student> studentiK = new ArrayList<>(); // studenti technickeho oboru
        Iterator<Integer> iter = databaseComponents.keySet().iterator();

        while (iter.hasNext()) {

            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentKom) { //pre studentov technickeho oboru
                studentiK.add(temp);
            }
        }

        float numOfStudentsK = studentiK.size();
        float sum = 0;
        if (numOfStudentsK != 0) {
            for (Student stud: studentiK) //foreach loop ktory scita priemery studentov do jedneho cisla
                sum += stud.getAverage();
            float totalAverage = sum / numOfStudentsK;
            return totalAverage;
        }
        else {
            return 0;
        }
    }


    public HashSet<Student> getStudentsT() //ziskava studentov tech z databaze
    {
        HashSet<Student> result = new HashSet<>();
        Iterator<Integer> iter = databaseComponents.keySet().iterator();
        while (iter.hasNext())
        {
            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentTech) {
                result.add(temp);
            }
        }
        return result;
    }

    public HashSet<Student> getStudentsH() //ziskava studentov hum z databaze
    {
        HashSet<Student> result = new HashSet<>();
        Iterator<Integer> iter = databaseComponents.keySet().iterator();
        while (iter.hasNext())
        {
            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentHum) {
                result.add(temp);
            }
        }
        return result;
    }

    public HashSet<Student> getStudentsK() //ziskava studentov kom z databaze
    {
        HashSet<Student> result = new HashSet<>();
        Iterator<Integer> iter = databaseComponents.keySet().iterator();
        while (iter.hasNext())
        {
            int key = iter.next();
            Student temp = databaseComponents.get(key);
            if (temp instanceof StudentKom) {
                result.add(temp);
            }
        }
        return result;
    }

    public void deleteDatabase(){  //funkcia na premazanie db
        this.databaseComponents = new HashMap<Integer, Student>();
    }
}
