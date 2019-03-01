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
            while ((str = in.readLine()) != null) {
                System.out.println(str);
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

    }
}
