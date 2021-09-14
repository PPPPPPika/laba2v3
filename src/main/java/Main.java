import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //Search links
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
        }

        //write files
        CollectorInformation collectorInformation = new CollectorInformation();
        FilesCreator filesCreator = new FilesCreator();

        for (String url : SearchEngine.getLinks()){
            if (!filesCreator.checkNumbersAllFiles()){
                collectorInformation.extractInformation(url);
                for (LengthInformation lengthInformation : collectorInformation.getProcessedTexts().keySet()){
                    if (!collectorInformation.getProcessedTexts().get(lengthInformation).equals("")
                            && !collectorInformation.getProcessedTexts().get(lengthInformation).equals(" ")
                            && collectorInformation.getProcessedTexts().get(lengthInformation) != null){
                        if (!filesCreator.checkNumbersFiles(lengthInformation)){
                            filesCreator.createFile(url, collectorInformation.getProcessedTexts().get(lengthInformation), lengthInformation);
                        }
                    }
                }
            }
            else break;
        }
    }
}

//исправить количество слов в файле