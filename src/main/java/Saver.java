import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

/*
    Класс предоставляет метод для записи ссылок на функции в файл, путь к которому указан в Main.class
 */
public class Saver {

    public static void saveInFile(Elements links) throws IOException {
        FileWriter writer = new FileWriter(Main.getPathToFile(), true);
        for (Element link : links) {
            writer.append("https://profstandart.rosmintrud.ru").append(link.attr("href"));
            writer.append("\n");
            writer.flush();
        }
    }
}
