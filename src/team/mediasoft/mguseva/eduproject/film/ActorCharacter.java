package team.mediasoft.mguseva.eduproject.film;

import java.util.List;
import java.util.Objects;

public class ActorCharacter extends FilmParameter {

    private String character;

    public ActorCharacter() {}

    public ActorCharacter(Actor actor) {
        super(actor);
    }

    public ActorCharacter(Actor actor, String character) {
        super(actor);

        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object characterObject) {
        if (this == characterObject) {
            return true;
        }

        if (characterObject == null || getClass() != characterObject.getClass()) {
            return false;
        }

        if (!super.equals(characterObject)) {
            return false;
        }

        ActorCharacter that = (ActorCharacter) characterObject;
        return Objects.equals(this.character, that.getCharacter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.character);
    }

    @Override
    public String toString() {
        String characterString = super.toString();

        if (characterString == null || characterString.isEmpty()) {
            characterString += "-";
        }

        characterString += "\n\tРоль: ";

        if (this.character != null && !this.character.isEmpty()) {
            characterString += this.character;
        } else {
            characterString += "-";
        }

        return characterString;
    }

    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addActorCharacter(this);
        }
    }

    @Override
    public void setParameterByName(String actorName) {
        if (actorName != null && actorName.length() > 0) {
            this.setParameter(new Actor(actorName));
        }
    }

    @Override
    public void setAddParameter(List<String> addParameters) {
        if (addParameters != null && addParameters.size() > 0) {
            this.character = addParameters.iterator().next();
        }
    }
}
