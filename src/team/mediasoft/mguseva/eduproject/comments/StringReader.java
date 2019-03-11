package team.mediasoft.mguseva.eduproject.comments;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class StringReader extends Reader {

    private String inputMessage;
    private String outputMessage;
    private String errorMessage;

    public StringReader() {
        super();

        this.errorMessage = "Вы ничего не ввели, попробуйте ещё раз";
    }

    protected String getInputMessage() {
        return inputMessage;
    }

    protected void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    protected String getOutputMessage() {
        return outputMessage;
    }

    protected void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    protected String getErrorMessage() {
        return errorMessage;
    }

    protected void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Вывод задачи на ввод
     *
     * @throws IllegalArgumentException
     */
    @Override
    protected void outputTask() throws IllegalArgumentException {
        System.out.print(this.inputMessage);
    }

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     */
    @Override
    protected Object inputInfo(BufferedReader reader) throws IOException, IllegalArgumentException {
        String newValue = reader.readLine();

        try {
            return this.getValueFromBuffer(newValue);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Проверка введеной строки
     *
     * @param inputStr
     * @return
     * @throws Exception
     */
    protected Object getValueFromBuffer(String inputStr) throws IllegalArgumentException {
        if (inputStr.length() > 0) {
            System.out.println(this.outputMessage + inputStr);
            return inputStr;
        }

        throw new IllegalArgumentException(this.errorMessage);
    }
}
