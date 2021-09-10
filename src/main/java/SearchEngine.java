import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class SearchEngine implements Runnable {
    private static final String SEARCH_TEMPLATE = "https://www.google.ru/search?q=";
    public static volatile List<String> links = Collections.synchronizedList(new ArrayList<>());
    private String searchWord = "";

    public SearchEngine(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void run() {
        searchLinks();
    }

    public void searchLinks(){
        try {
            Document document = Jsoup.connect(SEARCH_TEMPLATE + searchWord).get();
            //Elements elements = document.getElementsByTag("cite");
            Elements elements = document.select("a");

            for (Element element : elements){
                if (checkURL(element.absUrl("href").toCharArray())){
                    String link = element.absUrl("href");
                    synchronized (this){
                        boolean isExist = false;
                        for (String existingLink : links){
                            if (link.equals(existingLink) || link.equals("YouTube")) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            links.add(link);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean checkURL(char[] url){
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;
        char[] chGoogle1 = "https://www.google".toCharArray();
        char[] chGoogle2 = "https://policies.google".toCharArray();
        char[] chGoogle3 = "https://support.google".toCharArray();
        char[] chGoogle4 = "https://www.youtube".toCharArray();
        char[] chGoogle5 = "https://maps.google".toCharArray();

        int currentSize = url.length > chGoogle1.length ? chGoogle1.length : url.length;

        for (int i = 0; i < currentSize; i++){
            if (url[i] == chGoogle1[i]){
                counter1++;
                if (counter1 == currentSize)
                    return false;
            }
            if (url[i] == chGoogle2[i]){
                counter2++;
                if (counter2 == currentSize)
                    return false;
            }
            if (url[i] == chGoogle3[i]){
                counter3++;
                if (counter3 == currentSize)
                    return false;
            }
            if (url[i] == chGoogle4[i]){
                counter4++;
                if (counter4 == currentSize)
                    return false;
            }
            if (url[i] == chGoogle5[i]){
                counter5++;
                if (counter5 == currentSize)
                    return false;
            }
        }
        return true;
    }
}
