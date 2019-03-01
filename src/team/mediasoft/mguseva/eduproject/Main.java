package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.*;

import java.lang.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //Main.printTestFilm();

        String res = FileWorker.getStringFromFile("src/files/test.txt");
        System.out.println(res);
    }

    public static void printTestFilm() {
        String titanicDescription = "Молодые влюбленные Джек и Роза находят друг друга в первом и последнем плавании «непотопляемого» Титаника.\nОни не могли знать, что шикарный лайнер столкнется с айсбергом в холодных водах Северной Атлантики, и их страстная любовь превратится в схватку со смертью.";

        Film titanic = new Film("Титаник", 1997, titanicDescription);

        ArrayList<Director> titanicDirectors = new ArrayList<Director>(1);
        titanicDirectors.add(new Director("Джеймс Кэмерон"));

        titanic.setDirectors(titanicDirectors);

        ArrayList<ActorCharacter> titanicActors = new ArrayList<ActorCharacter>(3);

        titanicActors.add(new ActorCharacter(new Actor("Леонардо ДиКаприо"), "Jack Dawson"));
        titanicActors.add(new ActorCharacter(new Actor("Кейт Уинслет"), "Rose Dewitt Bukater"));
        titanicActors.add(new ActorCharacter(new Actor("Билли Зейн")));

        titanic.setActors(titanicActors);

        ArrayList<Genre> titanicGenre = new ArrayList<Genre>(2);
        titanicGenre.add(new Genre("драма"));
        titanicGenre.add(new Genre("мелодрама"));

        titanic.setGenres(titanicGenre);

        System.out.println(titanic.getFullInfo());




        Critic valera = new Critic("Валера");
        Critic valera2 = new Critic("Валера");

        CriticRate valeraTitanic = new CriticRate(valera);

        valeraTitanic.setRate(5);
        valeraTitanic.setComment("Мне понравилось");

        CriticRate valera2Titanic = new CriticRate(valera2, 2, "А мне нет");

        titanic.setRates(new ArrayList<CriticRate>() {{
            add(valeraTitanic);
            add(valera2Titanic);
        }});

        System.out.println("\n***\n" + titanic.getCriticRates());
    }
}
