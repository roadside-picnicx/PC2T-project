package projekt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {



    public static void main(String[] args) throws IOException, ClassNotFoundException {

            Scanner sc=new Scanner(System.in);
            Database myDatabase=new Database();
           int ID = myDatabase.readID();
            String name;
            String surname;
            int day;
            int month;
            int year;
            //float average;
            ArrayList<Double> grades;

            int option;
            boolean run=true;
            while(run)
            {
                System.out.println("\nVyberte pozadovanu cinnost:");
                System.out.println("1. Pridat studenta "); //DONE
                System.out.println("2. Zadat znamku studentovi"); //DONE
                System.out.println("3. Odstranenie studenta"); //DONE
                System.out.println("4. Vypis informacii o studentovi podla ID");//DONE
                System.out.println("5. Specialna vlastnost studenta podla ID");//DONE
                System.out.println("6. Vypis studentov abecedne"); //done
                System.out.println("7. Vypis studijneho priemeru v obore");//done
                System.out.println("8. Vypis poctu studentov v obore"); //done
                System.out.println("9. Ulozit databazu do suboru");//done
                System.out.println("10. Nacitat databazu zo suboru");//done
                System.out.println("11. SQL");
                System.out.println("12. Zmazat databazu");
                System.out.println("13. Ukoncenie aplikacie");
                option= Commands.intOnly(sc);
                switch(option)
                {
                   case 1:
                        System.out.println("Zadajte meno studenta:");
                        name = sc.next();
                        System.out.println("Zadajte priezvisko studenta:");
                        surname = sc.next();
                        System.out.println("Zadajte datum narodenia (den):");
                        day = Commands.intOnly(sc);
                        System.out.println("Zadajte datum narodenia (mesiac):");
                        month = Commands.intOnly(sc);
                        System.out.println("Zadajte datum narodenia (rok):");
                        year = Commands.intOnly(sc);
                        System.out.println("ID studenta je:"+ID);

                        System.out.println("Vyberte obor do ktoreho bude student priradeny:");
                        System.out.println("1. Technicky obor");
                        System.out.println("2. Humanitarny obor");
                        System.out.println("3. Kombinovany obor");
                        option= Commands.intOnly(sc);


                        switch(option)
                            {
                                case 1:
                                    myDatabase.addStudentTech(ID++,name,surname,day,month,year);
                                    System.out.println("Student bol priradeny do technickeho oboru.");
                                    break;

                                case 2:
                                    myDatabase.addStudentHum(ID++,name,surname,day,month,year);
                                    System.out.println("Student bol priradeny do humanitarneho oboru.");
                                    break;

                                case 3:
                                    myDatabase.addStudentKom(ID++,name,surname,day,month,year);
                                    System.out.println("Student bol priradeny do kombinovaneho oboru.");
                                    break;

                            }


                        break;
                   case 2:
                       System.out.println("Zadajte ID studenta ktoremu chcete pridat znamku");
                       ID = sc.nextInt();
                       Student info = myDatabase.getStudent(ID);
                       if (info instanceof Student) {
                           System.out.println("Zadajte znamku (1 az 5)");
                           double grade = Commands.doubleOnly(sc);
                           if (grade >= 1 && grade <= 5)
                               myDatabase.addGrades(ID, grade);
                           else
                               System.out.println("Znamka zvolena zo zleho rozsahu");
                       }
                       else
                           System.out.println("Student s takymto ID neexistuje.");
                        break;
                    case 3:
                        System.out.println("Zadajte ID studenta ktoreho chcete odstranit");
                        ID = sc.nextInt();
                        if(myDatabase.deleteStudent(ID))
                            System.out.println(ID+ "- bol zmazany");
                        else
                            System.out.println(ID +" - nebol najdeny");
                        break;
                    case 4:
                        System.out.println("Zadajte ID studenta");
                        ID = sc.nextInt();
                        info = myDatabase.getStudent(ID);
                        if(info instanceof Student)
                            System.out.println("Meno: "+info.getName() +"\tPriezvisko:"+info.getSurname() +" \tDatum narodenia:"+info.getDay()+"."+info.getMonth()+"."+ info.getYear() + "\tPriemer: "+info.getAverage());
                        else
                            System.out.println("Student s takymto ID neexistuje.");
                        break;

                    case 5:
                        System.out.println("Zadajte ID studenta");
                        ID = sc.nextInt();
                        info = myDatabase.getStudent(ID);
                        if(info instanceof Student)
                            info.specialSkill();
                            else
                        System.out.println("Student s takymto ID neexistuje.");
                        break;
                    case 6:
                        myDatabase.StudentsByAlphabet();
                        break;
                    case 7:
                        System.out.println("Celkovy priemer techncikeho oboru je: "+myDatabase.studentsTAverage());
                        System.out.println("Celkovy priemer humanitarneho oboru je: "+myDatabase.studentsHAverage());
                        System.out.println("Celkovy priemer kombinovaneho oboru je: "+myDatabase.studentsKAverage());
                        break;

                    case 8:
                        myDatabase.numberOfStudents();
                        break;
                    case 9:
                        System.out.println("Zadajte meno souboru");
                            String savename = sc.next();
                            myDatabase.saveToFile(savename);
                        break;
                    case 10:
                        System.out.println("Zadajte meno souboru");
                       try {
                           String loadname = sc.next();
                        myDatabase.readFromFile(loadname);
                        ID = myDatabase.readID(); //pouzije funkciu readID ktora nacita najvacsie ID zo suboru
                       }
                       catch (FileNotFoundException e){
                           System.out.println("Subor s takymto nazvom neexistuje");
                       }
                        break;
                    case 11:
                        DBConnection connection = new DBConnection();
                        if (connection.getDBConnection()) {
                            System.out.println("1. Ulozit do SQL");
                            System.out.println("2. Nacitat z SQL");
                            System.out.println("3. Return");
                            option = Commands.intOnly(sc);
                            switch (option) {
                                case 1: {
                                    try {
                                        connection.saveDB(myDatabase);
                                        System.out.println("Databaza ulozena");
                                    } catch (Exception e) {
                                        System.out.println("Databazu sa nepodarilo ulozit");
                                    }

                                    break;
                                }

                                case 2: {
                                    try {
                                        connection.getDB(myDatabase);
                                        System.out.println("Databaza nacitana");
                                    } catch (Exception e) {
                                        System.out.println("Nepodarilo sa nacitat databazu");
                                    }

                                    break;
                                }
                            }
                            connection.closeConnection();

                        }
                        else
                            System.out.println("Spojenie s databazou sa nezdarilo");

                        break;
                    case 12:
                        System.out.println("Naozaj chcete zmazat databazu?");
                        System.out.println("Stlacte 1 ak ano. \nStlacte akekolvek ine cislo ak nie.");
                        int volba =1;
                        volba = sc.nextInt();
                        if(volba ==1) {
                            myDatabase = new Database();
                            System.out.println("Databaza uspesne zmazana.");
                            break;
                        }
                        else {
                            System.out.println("Databaza nebola zmazana.");
                        }
                        break;
                    case 13:
                        run=false;
                        break;

               }
            }
    }
}
