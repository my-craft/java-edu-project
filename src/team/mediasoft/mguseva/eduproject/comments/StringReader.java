package team.mediasoft.mguseva.eduproject.comments;

import java.io.BufferedReader;

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
     */
    @Override
    protected void outputTask() throws Exception {
        System.out.print(this.inputMessage);
    }

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     */
    @Override
    protected Object inputInfo(BufferedReader reader) throws Exception {
        String newValue = reader.readLine();

        try {
            return this.getValueFromBuffer(newValue);
        } catch (Exception e) {
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
    protected Object getValueFromBuffer(String inputStr) throws Exception {
        if (inputStr.length() > 0) {
            System.out.println(this.outputMessage + inputStr);
            return inputStr;
        }

        throw new Exception(this.errorMessage);
    }
}
