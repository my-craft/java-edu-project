package team.mediasoft.mguseva.eduproject;

public class ActorCharacter extends FilmParameter {

    private String character;

    public ActorCharacter(Actor actor) {
        super(actor);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
