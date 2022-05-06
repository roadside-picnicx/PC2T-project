package projekt;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentTech extends Student implements Serializable {



    public StudentTech(int ID, String name, String surname, int day, int month, int year){
        super(ID,name,surname,day,month,year);


    }


    public void specialSkill() { //specialna schopnost kedy dokaze povedat ze sa narodil v prestupnom roku
        int year = this.getYear();
        boolean isLeap = false;
        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                if ( year % 400 == 0)
                    isLeap = true;
                else
                    isLeap = false;
            }
            else
                isLeap = true;
        }
        else {
            isLeap = false;
        }

        if(isLeap==true)
            System.out.println("Student sa narodil v prestupnom roku \n");
        else
            System.out.println("Student sa nenarodil v prestupnom roku \n");
    }


}

