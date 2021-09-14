import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CollectorInformation {
    private static Map<LengthInformation, String> mapProcessedTexts = new HashMap<>();
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
            classifierInformation(counterWords, information);
            processingInformation();
            cleanInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void classifierInformation(int counterWords, StringBuilder information){
        if (counterWords <= 200)
            mapInformation.replace(LengthInformation.SHORT, mapInformation.get(LengthInformation.SHORT), information);
        if (counterWords > 200 && counterWords < 500){
            StringBuilder stringBuilder = transformationInformation(information, LengthInformation.SHORT);
            if (stringBuilder != null)
                mapInformation.replace(LengthInformation.SHORT, mapInformation.get(LengthInformation.SHORT), stringBuilder);
        }

        if (counterWords >= 500 && counterWords <= 800)
            mapInformation.replace(LengthInformation.MIDDLE, mapInformation.get(LengthInformation.MIDDLE), information);
        if (counterWords > 800 && counterWords < 1000){
            StringBuilder stringBuilder1 = transformationInformation(information, LengthInformation.MIDDLE);
            if (stringBuilder1 != null)
                mapInformation.replace(LengthInformation.MIDDLE, mapInformation.get(LengthInformation.MIDDLE), stringBuilder1);
        }

        if (counterWords >= 1000 && counterWords <= 1500)
            mapInformation.replace(LengthInformation.LONG, mapInformation.get(LengthInformation.LONG), information);
        if (counterWords > 1500){
            StringBuilder stringBuilder2 = transformationInformation(information, LengthInformation.LONG);
            if (stringBuilder2 != null)
                mapInformation.replace(LengthInformation.LONG, mapInformation.get(LengthInformation.LONG), stringBuilder2);
        }
    }

    private void processingInformation() {
        for (Map.Entry<LengthInformation, StringBuilder> entry : mapInformation.entrySet()){
            if (!entry.getValue().toString().equals(" ") && !entry.getValue().isEmpty() && entry.getValue() != null){
                mapProcessedTexts.put(entry.getKey(), new String(entry.getValue()));
            }
        }
    }

    private StringBuilder transformationInformation(StringBuilder stringBuilder, LengthInformation lengthInformation){
        char[] oldInformation = stringBuilder.toString().toCharArray();
        int border = 0;

        switch (lengthInformation){
            case SHORT  -> border = 200;
            case MIDDLE -> border = 800;
            case LONG   -> border = 1000;
        }

        int counterSpace = 0;
        char[] newInformation = new char[stringBuilder.toString().toCharArray().length];
        for (int i = 0; i < oldInformation.length; i++){
            newInformation[i] = oldInformation[i];
            if (oldInformation[i] == ' '){
                counterSpace++;
                if (counterSpace == border)
                    return new StringBuilder(String.valueOf(newInformation));
            }
        }
        return null;
    }

    public Map<LengthInformation, StringBuilder> getInformation() {
        return mapInformation;
    }

    public Map<LengthInformation, String> getProcessedTexts() {
        return mapProcessedTexts;
    }

    public void cleanInformation(){
        for (LengthInformation lengthInformation : LengthInformation.values()){
            mapInformation.get(lengthInformation).delete(0, mapInformation.get(lengthInformation).length());
        }
    }
}
