package team.mediasoft.mguseva.eduproject.film;

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

    /**
     * Добавить в фильм текущий текущий экземпляр параметра
     * Персонаж
     *
     * @param film
     */
    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addActorCharacter(this);
        }
    }

    /**
     * Инициализировать экземпляр параметра с помощью parameterName и записать его в параметр текущего экземпляра
     * Привязать актера к персонажу
     *
     * @param actorName
     */
    @Override
    public void setParameterByName(String actorName) {
        if (actorName != null && actorName.length() > 0) {
            this.setParameter(new Actor(actorName));
        }
    }

    /**
     * Инициализировать дополнительные поля значениями из списка строк - имя персонажа
     *
     * @param addParameters
     */
    @Override
    public void setAddParameter(String[] addParameters) {
        if (addParameters != null && addParameters.length > 0) {
            this.character = addParameters[0];
        }
    }
}
