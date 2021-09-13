import java.util.HashMap;
import java.util.Map;

public class TestMain {
    private static final String SEARCH_TEMPLATE = "https://www.google.ru/search?q=";

    public static void main(String[] args) {
        /* try {*/
        /*     Document document = Jsoup.connect(SEARCH_TEMPLATE + "экономика").get();*/
        /*     Elements elements = document.select("a");*/

        /*     AnalyzeURL analyzeURL = new AnalyzeURL();*/
        /*     for (Element element : elements){*/
                /*if (analyzeURL.checkURL(element.absUrl("href").toCharArray())){
                    //System.out.println(element.absUrl("href"));
                }*/
        /*     }*/
        /* } catch (IOException e) {*/
        /*     e.printStackTrace();*/
        /* }*/

        /*String s = "jgh frewrg brgfb. wbrtgbr wrtbrt, wbrtbrwtb wbrtbwb, bsadfbrt bvqaeth cvxzcv werfwer";

        int i = s.split(" ").length;

        System.out.println(i);*/


        //StringBuilder stringBuilder = new StringBuilder();

        /*System.out.println(CollectorInformation.mapInformation.get(LengthInformation.SHORT).isEmpty());
        System.out.println(CollectorInformation.mapInformation.get(LengthInformation.MIDDLE).isEmpty());
        System.out.println(CollectorInformation.mapInformation.get(LengthInformation.LONG).isEmpty());*/
        ;



        /*Map<LengthInformation, String> map = new HashMap<>();
        for (Map.Entry<LengthInformation, StringBuilder> entry : CollectorInformation.mapInformation.entrySet()){
            if (!entry.getValue().toString().equals("") && !entry.getValue().toString().equals(" ") && entry.getValue().toString() != null){
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }

        System.out.println(map);*/

        //Map<LengthInformation, String> map = new HashMap<>(Map.of(LengthInformation.SHORT, "qwerwfger")); //почистить

        //Map.Entry<LengthInformation, String> transitionMap = (Map.Entry<LengthInformation, String>) map.entrySet();




        //System.out.println(transitionMap);


    }
}
