package team.mediasoft.mguseva.eduproject.film;

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

    /**
     * Пребразовать в строку с разделителем ;
     *
     * @return
     */
    public String toCsvString() {
        return this.getParameter().getName() + ";" +
                this.rate + ";" +
                this.comment;
    }

    /**
     * Добавить в фильм текущий текущий экземпляр параметра
     * Оценка критика
     *
     * @param film
     */
    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addCriticRate(this);
        }
    }

    /**
     * Инициализировать экземпляр параметра с помощью parameterName и записать его в параметр текущего экземпляра
     * Привязать оценку критика
     *
     * @param criticName
     */
    @Override
    public void setParameterByName(String criticName) {
        if (criticName != null && criticName.length() > 0) {
            this.setParameter(new Critic(criticName));
        }
    }

    /**
     * Инициализировать дополнительные поля значениями из списка строк
     * Оценка и комментарий
     *
     * @param addParameters
     */
    @Override
    public void setAddParameter(String[] addParameters) {
        if (addParameters != null && addParameters.length > 1) {
            // оценка
            this.rate = this.getIntegerFromString(addParameters[0]);

            // комментарий
            this.comment = addParameters[1];
        }
    }
}
