package team.mediasoft.mguseva.eduproject.comments;

import java.io.BufferedReader;

public class RateReader extends StringReader {
    public RateReader() {
        super();

        this.setInputMessage("Введите оценку (от 1 до 5): ");
        this.setOutputMessage("Ваша оценка: ");
        this.setErrorMessage("Вы ввели некорретную оценку, попробуйте ещё раз");
    }

    /**
     * Проверка введеной строки
     *
     * @param inputStr
     * @return
     * @throws Exception
     */
    @Override
    protected Object getValueFromBuffer(String inputStr) throws Exception {
        int rate = this.getIntegerFromString(inputStr);

        if (rate > 0 && rate < 6) {
            System.out.println(this.getOutputMessage() + rate);
            return (Integer)rate;
        }

        throw new Exception(this.getErrorMessage());
    }
}
