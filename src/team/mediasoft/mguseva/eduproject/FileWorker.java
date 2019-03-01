package team.mediasoft.mguseva.eduproject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWorker {

    public static String getStringFromFile(String fileName) {
        StringBuilder outString = new StringBuilder("");
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));

            String str = "";

            // добавляем перенос строки, а чтобы вначале его не было, инициализируем уже в цикле
            String prefix = "";
            while ((str = in.readLine()) != null) {
                outString.append(prefix);
                prefix = "\n";
                outString.append(str);
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

        return outString.toString();
    }

    public static void setStringToFile(String fileName, String outString) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));

            String[] strArray = outString.split("\n");

            for (int i = 0; i < strArray.length; i++) {
                out.write(strArray[i]);

                // если не последняя строка, дописать перенос
                if (i < (strArray.length - 1)) {
                    out.write("\n");
                }
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
