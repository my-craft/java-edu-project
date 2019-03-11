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
                    return object;
                }
            } while (true);
        } catch (IOException e) {
            System.out.println("\nОшибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Вывод задачи на ввод
     *
     * @throws IllegalArgumentException
     */
    protected abstract void outputTask() throws IllegalArgumentException;

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    protected abstract Object inputInfo(BufferedReader reader) throws IOException, IllegalArgumentException;
}
