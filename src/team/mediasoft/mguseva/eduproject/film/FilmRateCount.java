package team.mediasoft.mguseva.eduproject.film;

import java.util.List;

public class FilmRateCount implements Countable<Film> {
    @Override
    public float count(Film film) {
        List<CriticRate> filmRates = film.getRates();
        boolean hasRates = (filmRates != null && !filmRates.isEmpty());

        if (hasRates) {
            int rateSum = 0;
            int rateCount = 0;

            for (CriticRate rate : filmRates) {
                rateSum += rate.getRate();
                rateCount++;
            }

            return Math.round((float)rateSum / (float)rateCount);
        }

        return 0;
    }
}
