package team.mediasoft.mguseva.eduproject;

public class CriticToFilm extends FilmParameter {

    private int rate;
    private String comment;

    public CriticToFilm(Critic critic, Film film) {
        super(critic, film);
    }

    public CriticToFilm(Critic critic, Film film, int rate, String comment) {
        super(critic, film);

        this.rate = rate;
        this.comment = comment;
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

    @Override
    public String toString() {
        String info = super.toString();

        info += "\n" +
                ((this.rate != 0) ? this.rate : "Оценка отсутствует") +
                "\n" +
                ((this.comment != null) ? this.comment : "Комментарий отсутствует");

        return info;
    }
}
