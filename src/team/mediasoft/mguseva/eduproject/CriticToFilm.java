package team.mediasoft.mguseva.eduproject;

public class CriticToFilm extends FilmParameter {

    private int rate;
    private String comment;

    public CriticToFilm(int criticId, int filmId) {
        super(criticId, filmId);
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
