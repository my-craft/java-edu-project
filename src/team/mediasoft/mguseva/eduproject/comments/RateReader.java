package team.mediasoft.mguseva.eduproject.comments;

import java.io.BufferedReader;

public class RateReader extends StringReader {
    public RateReader() {
        super();

        this.setInputMessage("Введите оценку (от 1 до 5): ");
        this.setOutputMessage("Ваша оценка: ");
    }

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     */
    @Override
    protected Object inputInfo(BufferedReader reader) throws Exception {
        try {
            String rateNumber = reader.readLine();

            int rate = this.getIntegerFromString(rateNumber);

            if (rate > 0 && rate < 6) {
                System.out.println(this.getOutputMessage() + rate);
                return (Integer)rate;
            }
        } catch (Exception e) {}

        System.out.println("Вы ввели некорретную оценку, попробуйте ещё раз");

        return null;
    }
}
