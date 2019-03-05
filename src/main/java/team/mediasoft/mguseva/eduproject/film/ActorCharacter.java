package team.mediasoft.mguseva.eduproject.film;

import java.util.Objects;

public class ActorCharacter extends FilmParameter {

    private String character;

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

        characterString += "\nРоль: ";

        if (this.character != null && !this.character.isEmpty()) {
            characterString += this.character;
        } else {
            characterString += "-";
        }

        return characterString;
    }
}
