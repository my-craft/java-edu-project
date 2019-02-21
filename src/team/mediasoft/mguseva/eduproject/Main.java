package team.mediasoft.mguseva.eduproject;

import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Critic valera = new Critic("Валера");
        System.out.println(valera);

        Critic valera2 = new Critic("Валера");

        System.out.println(valera.equals(valera2));

        Critic valera3 = null;
        try {
            valera3 = (Critic) valera2.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (valera3 != null) {
            System.out.println(valera3.equals(valera));
        } else {
            System.out.println("valera3 failed");
        }
    }
}
