import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
    Класс предоставляет метод для загрузки html-кода страницы по URL и возвращает String-объект страницы
 */

public class Downloader {


    public Downloader() {
    }

    //This method is downloading page by post-request and return string-object
    public static String readPageFromUrl(String strURL) throws IOException {
        URL pURL = new URL(strURL);
        URLConnection urlCon = pURL.openConnection();
        urlCon.setConnectTimeout(30000000);
        urlCon.setReadTimeout(30000000);
        urlCon.setRequestProperty("User-Agent", "Mozilla");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
        StringBuilder result = new StringBuilder();
        String readLine;
        readLine = in.readLine();

        while (readLine != null) {
            result.append(readLine);
            readLine = in.readLine();
        }
        in.close();

        return result.toString();
    }
}
