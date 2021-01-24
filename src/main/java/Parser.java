import me.tongfei.progressbar.ProgressBar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

/*
    Класс предоставляет методы для распарщивания главных страниц реестра, а также страниц функций.
 */
public class Parser {

    private static final int COUNT_PAGES_WITH_LINKS = 526;
    private static final String FIRST_PART_PAGE_URL = "https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-trudovyh-funkcij/?PAGEN_1=";
    private static final String SECOND_PART_PAGE_URL = "&SIZEN_1=20";



    public static void start() {
        DataBase dataBase = new DataBase();
        initialLinks();
        parseLinks();
        System.out.println("Complete.");
    }

    private static void initialLinks(){
        Document document;
        ProgressBar pb = new ProgressBar("Копирование ссылок на функции", COUNT_PAGES_WITH_LINKS);
        pb.start();

        new File(Main.getPathToFile()).delete();

        try{
            for (int i = 1; i <= COUNT_PAGES_WITH_LINKS; i++) {
                document = Jsoup.parse(Downloader.readPageFromUrl(FIRST_PART_PAGE_URL + i + SECOND_PART_PAGE_URL),"UTF-8");
                Elements links = document.select("a[href^=/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-trudovyh-funkcij/index.php?ELEMENT_ID=]");
                Saver.saveInFile(links);
                pb.step();
            }
        }catch (IOException e){
            System.out.println("Возникла проблема с соединением к серверу https://profstandart.rosmintrud.ru");
            System.exit(0);
        }
        pb.stop();
    }

    private static void parseLinks(){
        Document document;
        try {
            DataBase dataBase = new DataBase();
            Scanner scanner = new Scanner(new FileInputStream(new File(Main.getPathToFile())));
            ProgressBar pb = new ProgressBar("Сохранено объектов в БД", countLines());
            pb.start();
            while (scanner.hasNext()){
                String page = Downloader.readPageFromUrl(scanner.next());
                document = Jsoup.parse(page);
                String name = document.select("td[style=width: 44%; border: solid 1px; padding: 5px;]").first().text();
                String code = document.select("td[style=width: 5%; border: solid 1px; padding: 5px;]").first().text();
                String number = document.select("td[style=width: 21%; border: solid 1px; padding: 5px;]").first().text();
                String skill = document.select("td[style=width: 76%; border: solid 1px; padding: 5px;]").get(0).text() +
                        document.select("td[style=width: 76%; border: solid 1px; padding: 5px;]").get(1).text() +
                        document.select("td[style=width: 76%; border: solid 1px; padding: 5px;]").get(2).text();
                FunctionObject functionObject = new FunctionObject(number,code,name,skill);
                dataBase.add(functionObject);
                pb.step();
            }
            pb.stop();


        } catch (IOException ignored) {
            System.out.println("Файл с ссылками не найден.");
            System.exit(0);
        } catch (SQLException throwables) {
            System.out.println("Возникла проблема при добавлении объектов в баззу данных..");
            System.out.println("Прийдется начать все сначала.");
            throwables.printStackTrace();
            System.exit(0);
        }
    }


    private static int countLines() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Main.getPathToFile()));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }


}
