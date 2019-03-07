package team.mediasoft.mguseva.eduproject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileWorker {

    private String fileName;
    private List<String> content;

    public FileWorker(String fileName) {
        this.fileName = fileName;
        this.content = new ArrayList<String>();
    }

    public FileWorker(String fileName, List<String> content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    /**
     * Загрузить контент из файла в список строк
     */
    public void loadContentFromFile() {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8));

            String str = "";
            while ((str = in.readLine()) != null) {
                this.content.add(str);
            }

        } catch (Exception e) {
            System.out.println("Oops!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    System.out.println("Oops! [2]");
                }
            }
        }
    }

    /**
     * Выгрузить список строк в файл
     *
     * @param append
     */
    public void putContentToFile(boolean append) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName, append), StandardCharsets.UTF_8));

            for (String tempStr : this.content) {
                out.write(tempStr);
                out.newLine();
            }
        } catch (Exception e) {
            System.out.println("Oops!");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    System.out.println("Oops! [2]");
                }
            }
        }
    }
}
