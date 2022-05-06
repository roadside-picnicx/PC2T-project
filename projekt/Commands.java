package projekt;

import java.util.Scanner;

public class Commands {




    public static int intOnly(Scanner sc)
    {
        int number = 0;
        try
        {
            number = sc.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Zadejte prosim cele cislo ");
            sc.nextLine();
            number = intOnly(sc);
        }
        return number;
    }

    public static double doubleOnly(Scanner sc)
    {
        double number = 0;
        try
        {
            number = sc.nextDouble();
        }
        catch(Exception e)
        {
            System.out.println("Zadejte prosim cele cislo ");
            sc.nextLine();
            number = doubleOnly(sc);
        }
        return number;
    }

}
