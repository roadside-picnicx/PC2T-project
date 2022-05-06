package projekt;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class Student implements Comparable<Student>, Serializable {
    int ID;
    private String name;
    private String surname;
    private int day;
    private int month;
    private int year;
    float average;
    private ArrayList<Double> grades = new ArrayList<Double>();


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Double> grades) {
        this.grades = grades;
    }

    public void newGrade(double grade) { //nova znamka
        if (grade >= 1 && grade <= 5)
            this.grades.add(grade);

    }


    public float getAverage() {  //vypocet priemeru
        float numOfGrades = grades.size();
        float total = 0;
        if (numOfGrades != 0) {
            for (Double g : this.grades) {
                total += g;
            }

            average = total / numOfGrades;
            return this.average = average;
        } else
            return this.average = 0;
    }


    public Student(int ID, String name, String surname, int day, int month, int year) {
        this.surname = surname;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.ID = ID;


    }

    public void specialSkill() {
    }

    @Override
    public int compareTo(Student o) { //porovnanie priezviska
        return this.getSurname().compareTo(o.getSurname());
    }
}
