import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        /*//Search links
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (KeyWords keyWord : KeyWords.values())
            executorService.execute(new SearchEngine(keyWord.getValue()));
        executorService.shutdown();
        //test
        try {
            Thread.sleep(5000);
            System.out.println(executorService.isTerminated());
            for (String s : SearchEngine.getLinks()){
                System.out.println(s);
            }
            System.out.println(SearchEngine.getLinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //write files
        CollectorInformation collectorInformation = new CollectorInformation();
        FilesCreator filesCreator = new FilesCreator();

        String s1 = "https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D0%BA%D0%B0#%D0%A1%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D1%8B_%D1%8D%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D0%BA%D0%B8";
        String s2 = "https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D0%BA%D0%B0#%D0%AD%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%D1%80%D0%BE%D1%81%D1%82";
        String s3 = "https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D0%BA%D0%B0#%D0%98%D1%81%D1%82%D0%BE%D1%80%D0%B8%D1%8F_%D1%8D%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B8%D0%BA%D0%B8";

        /*for (String url : SearchEngine.getLinks()){

        }*/ //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        collectorInformation.extractInformation(s1);
        for (LengthInformation lengthInformation : collectorInformation.getProcessedTexts().keySet()){
            if (!collectorInformation.getProcessedTexts().get(lengthInformation).equals("")
                    && !collectorInformation.getProcessedTexts().get(lengthInformation).equals(" ")
                    && collectorInformation.getProcessedTexts().get(lengthInformation) != null){
                filesCreator.createFile(s1, collectorInformation.getProcessedTexts().get(lengthInformation), lengthInformation);
            }
        }



        //System.out.println(collectorInformation.getInformation());
        //System.out.println(collectorInformation.getProcessedTexts());


    }
}
