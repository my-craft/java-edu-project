package team.mediasoft.mguseva.eduproject.film;

import java.util.Comparator;

public class CharactersSort implements Comparator<ActorCharacter> {
    @Override
    public int compare(ActorCharacter character1, ActorCharacter character2) {
        return character1.getParameter().getName().compareTo(character2.getParameter().getName());
    }
}
