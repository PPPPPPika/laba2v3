import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class SearchEngine implements Runnable {
    private String searchWord = "";
    private static final String SEARCH_TEMPLATE = "https://www.google.ru/search?q=";
    private static volatile List<String> links = Collections.synchronizedList(new ArrayList<>());
    private final Set<char[]> notValidLinks = new HashSet<>(Arrays.asList("https://www.google".toCharArray(),
                                                                          "https://policies.google".toCharArray(),
                                                                          "https://support.google".toCharArray(),
                                                                          "https://www.youtube".toCharArray(),
                                                                          "https://maps.google".toCharArray()));

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
            Elements elements = document.select("a");

            for (Element element : elements){
                if (checkURL(element.absUrl("href").toCharArray()) && !element.absUrl("href").equals("")){
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

    public static List<String> getLinks() {
        return links;
    }

    private boolean checkURL(char[] url){ //сомнительная необходимость в синхронизации
        int[] arrayCounters = {0, 0, 0, 0, 0};
        int indexNotValidLink = 0;

        for (char[] notValidLink : notValidLinks){
            char[] subCurrentURL = new char[notValidLink.length];
            if (url.length > notValidLink.length){
                System.arraycopy(url, 0, subCurrentURL, 0, notValidLink.length);
            }
            for (int i = 0; i < notValidLink.length; i++){
                if (subCurrentURL[i] == notValidLink[i]){
                    arrayCounters[indexNotValidLink]++;
                    if (arrayCounters[indexNotValidLink] == notValidLink.length)
                        return false;
                }
            }
            indexNotValidLink++;
        }
        return true;
    }
}
