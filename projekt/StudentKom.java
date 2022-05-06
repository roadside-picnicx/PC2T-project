package projekt;

import java.io.Serializable;

public class StudentKom extends Student implements Serializable {
    public StudentKom(int ID, String name, String surname, int day, int month, int year) {
        super(ID, name, surname, day, month, year);
    }

    public void specialSkill() { //specialna schopnost kedy dokaze povedat ci sa narodil v prestupnom roku a v akom znameni
        int day = this.getDay();
        int month = this.getMonth();
        int year = this.getYear();
        boolean isLeap = false;
        String sign = "";
        if (month == 1) {
            if (day < 20)
                sign = "Kozorozec";
            else
                sign = "Vodnar";
        }
        else if (month == 2) {
            if (day < 19)
                sign = "Vodnar";
            else
                sign = "Ryby";
        }
        else if(month == 3) {
            if (day < 21)
                sign = "Ryby";
            else
                sign = "Baran";
        }
        else if (month == 4) {
            if (day < 20)
                sign = "Baran";
            else
                sign = "Byk";
        }
        else if (month ==5) {
            if (day < 21)
                sign = "Byk";
            else
                sign = "Blizenci";
        }
        else if( month == 6) {
            if (day < 21)
                sign = "Blizenci";
            else
                sign = "Rak";
        }
        else if (month == 7) {
            if (day < 23)
                sign = "Rak";
            else
                sign = "Lev";
        }
        else if( month == 8) {
            if (day < 23)
                sign = "Lev";
            else
                sign = "Panna";
        }
        else if (month == 9) {
            if (day < 23)
                sign = "Panna";
            else
                sign = "Vahy";
        }
        else if (month == 10) {
            if (day < 23)
                sign = "Vahy";
            else
                sign = "Skorpion";
        }
        else if (month == 11) {
            if (day < 22)
                sign = "Skorpion";
            else
                sign = "Strelec";
        }
        else if (month == 12) {
            if (day < 22)
                sign = "Strelec";
            else
                sign ="Kozorozec";
        }
        System.out.println("Znamenie studenta narodeneho " + day + "." + month + " je " + sign +"\n");

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

