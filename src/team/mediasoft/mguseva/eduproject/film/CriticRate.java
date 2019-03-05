package team.mediasoft.mguseva.eduproject.film;

import java.util.List;

public class CriticRate extends FilmParameter {

    private int rate;
    private String comment;

    public CriticRate() {}

    public CriticRate(Critic critic) {
        super(critic);
    }

    public CriticRate(Critic critic, int rate, String comment) {
        super(critic);

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

        info += "\nОценка: " +
                ((this.rate != 0) ? this.rate : "отсутствует") +
                "\nКомментарий: " +
                ((this.comment != null) ? this.comment : "отсутствует");

        return info;
    }

    @Override
    public void addParameterToFilm(Film film) {

    }

    @Override
    public void setParameterByName(String parameterName) {

    }

    @Override
    public void setAddParameter(List<String> addParameters) {

    }
}
