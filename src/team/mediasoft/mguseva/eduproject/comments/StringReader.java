package team.mediasoft.mguseva.eduproject.comments;

import java.io.BufferedReader;

public abstract class StringReader extends Reader {

    private String inputMessage;
    private String outputMessage;

    public StringReader() {
        super();
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
        try {
            String str = reader.readLine();
            if (str.length() > 0) {
                System.out.println(this.outputMessage + str);
                return str;
            }
        } catch (Exception e) {}

        System.out.println("Вы ничего не ввели, попробуйте ещё раз");

        return null;
    }
}
