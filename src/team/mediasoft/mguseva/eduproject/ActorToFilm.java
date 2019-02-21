package team.mediasoft.mguseva.eduproject;

public class ActorToFilm extends FilmParameter {

    private String character;

    public ActorToFilm(Actor actor, Film film) {
        super(actor, film);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
