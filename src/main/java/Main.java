

import java.io.*;

/*
    Главный класс, в котором необходимо указать:
    URL - адрес БД, в которую необходимо сохранить результат,
    LOGIN - логин пользователя БД,
    PASS - пароль учетной записи пользователя БД,
    PATH_TO_FILE - ссылка (название) файла, который будет создан для промежуточных вычислений ссылок.

    !Важно!
    Если указать в PATH_TO_FILE путь к существующему файлу, он будет удален.
 */

public class Main {

    // jdbc:postgresql://localhost:port/db-name
    private final static String URL = "jdbc:postgresql://localhost:5432/antondzubak";
    private final static String LOGIN = "antondzubak";
    private final static String PASS = "";
    private final static String PATH_TO_FILE = "pages/registry_page/links.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        Parser.start();
    }

    public static String getURL() {
        return URL;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getPathToFile() {
        return PATH_TO_FILE;
    }
}
