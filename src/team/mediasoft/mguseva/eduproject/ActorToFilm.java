package team.mediasoft.mguseva.eduproject;

public class ActorToFilm extends FilmParameter {

    private String character;

    public ActorToFilm(int actorId, int filmId) {
        super(actorId, filmId);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
