import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CollectorInformation {
    public static Map<LengthInformation, String> mapProcessedTexts = new HashMap<>();

    private static Map<LengthInformation, StringBuilder> mapInformation = new HashMap<>(Map.of(LengthInformation.SHORT, new StringBuilder(),
                                                                                               LengthInformation.MIDDLE, new StringBuilder(),
                                                                                               LengthInformation.LONG, new StringBuilder()));

    CollectorInformation(){}

    public void extractInformation(String url){
        StringBuilder information = new StringBuilder();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("p");

            int counterWords = 0;
            for (Element element : elements){
                counterWords += element.text().split(" ").length;
                information.append(element.text() + " ");
            }

            if (counterWords < 200)
                mapInformation.replace(LengthInformation.SHORT, mapInformation.get(LengthInformation.SHORT), information);
            if (counterWords > 200 && counterWords < 500){
                StringBuilder stringBuilder = transformationInformation(information, LengthInformation.SHORT);
                if (stringBuilder != null)
                    mapInformation.replace(LengthInformation.SHORT, mapInformation.get(LengthInformation.SHORT), stringBuilder);
            }
            if (counterWords > 500 && counterWords < 800)
                mapInformation.replace(LengthInformation.MIDDLE, mapInformation.get(LengthInformation.MIDDLE), information);
            if (counterWords > 800 && counterWords < 1000){
                StringBuilder stringBuilder1 = transformationInformation(information, LengthInformation.MIDDLE);
                if (stringBuilder1 != null){
                    mapInformation.replace(LengthInformation.MIDDLE, mapInformation.get(LengthInformation.MIDDLE), stringBuilder1);
                }
            }
            if (counterWords > 1000 && counterWords < 1500){
                mapInformation.replace(LengthInformation.LONG, mapInformation.get(LengthInformation.LONG), information);
            }
            if (counterWords > 1500){
                StringBuilder stringBuilder2 = transformationInformation(information, LengthInformation.LONG);
                if (stringBuilder2 != null){
                    mapInformation.replace(LengthInformation.LONG, mapInformation.get(LengthInformation.LONG), stringBuilder2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<LengthInformation, String> getInformation() {
        Map<LengthInformation, String> map = new HashMap<>();
        for (Map.Entry<LengthInformation, StringBuilder> entry : mapInformation.entrySet()){
            if (!entry.getValue().toString().equals("") && !entry.getValue().toString().equals(" ") && entry.getValue().toString() != null)
                map.put(entry.getKey(), entry.getValue().toString());
        }
        //cleanInformation();
        return map;
    }

    private StringBuilder transformationInformation(StringBuilder stringBuilder, LengthInformation lengthInformation){
        char[] array = stringBuilder.toString().toCharArray();
        int arraySize = 0;
        int counter = 0;

        switch (lengthInformation){
            case SHORT  -> arraySize = 200;
            case MIDDLE -> arraySize = 800;
            case LONG   -> arraySize = 1000;
        }

        char[] newArray = new char[stringBuilder.toString().toCharArray().length];
        //char[] newArr = new char[];
        for (int i = 0; i < array.length; i++){
            newArray[i] = array[i];
            if (array[i] == ' '){
                counter++;
                if (counter == arraySize)
                    return new StringBuilder(String.valueOf(newArray));
            }
        }
        return null;
    }

    public void cleanInformation(){
        for (LengthInformation lengthInformation : LengthInformation.values()){
            mapInformation.get(lengthInformation).delete(0, mapInformation.get(lengthInformation).length());
        }
    }
}
