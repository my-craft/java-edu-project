package team.mediasoft.mguseva.eduproject;

import java.lang.*;
public class Main {

    public static void main(String[] args) {
        Critic valera = new Critic("Валера");
        System.out.println(valera);

        Critic valera2 = new Critic("Валера");

        System.out.println(valera.equals(valera2));

        Critic valera3 = (Critic) valera2.clone();

        if (valera3 != null) {
            System.out.println(valera3.equals(valera));
        } else {
            System.out.println("valera3 failed");
        }

        Film titanic = new Film("Титаник", 1997, "");
        Film titanic2 = (Film) titanic.clone();

        System.out.println(titanic);
        System.out.println(titanic2);

        titanic2.setYear(1990);

        System.out.println(titanic);
        System.out.println(titanic2);
    }
}
