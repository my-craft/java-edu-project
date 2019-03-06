package team.mediasoft.mguseva.eduproject.comments;

import team.mediasoft.mguseva.eduproject.InputChecker;

import java.io.*;

public abstract class Reader extends InputChecker {

    public Reader() {}

    /**
     * Ввод информации пользователем
     *
     * @return
     */
    public Object getInfoFromUser() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                this.outputTask();
                Object object = this.inputInfo(reader);

                if (object != null) {
                    break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {}
            }
        }

        return null;
    }

    /**
     * Вывод задачи на ввод
     */
    protected abstract void outputTask() throws Exception;

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     */
    protected abstract Object inputInfo(BufferedReader reader) throws Exception;
}
