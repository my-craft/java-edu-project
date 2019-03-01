package team.mediasoft.mguseva.eduproject.film;

public class CriticRate extends FilmParameter {

    private int rate;
    private String comment;

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

        info += "\n" +
                ((this.rate != 0) ? this.rate : "Оценка отсутствует") +
                "\n" +
                ((this.comment != null) ? this.comment : "Комментарий отсутствует");

        return info;
    }
}
