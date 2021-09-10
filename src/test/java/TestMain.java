import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class TestMain {
    private static final String SEARCH_TEMPLATE = "https://www.google.ru/search?q=";

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect(SEARCH_TEMPLATE + "экономика").get();
            Elements elements = document.select("a");

            AnalyzeURL analyzeURL = new AnalyzeURL();
            for (Element element : elements){
                /*if (analyzeURL.checkURL(element.absUrl("href").toCharArray())){
                    //System.out.println(element.absUrl("href"));
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
