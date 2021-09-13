import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
            Map<LengthInformation, String> map; //почистить

            collectorInformation.extractInformation(url);
            map = collectorInformation.getInformation();

            boolean replicate = false;
            for (String processedText : CollectorInformation.mapProcessedTexts.values()){
                for (String currentText : map.values()){
                    if (processedText.equals(currentText)){
                        replicate = true;
                        break;
                    }
                }
            }
            if (!replicate){
                CollectorInformation.mapProcessedTexts.putAll(map);
            }
            collectorInformation.cleanInformation();


            /*for (String string : CollectorInformation.mapProcessedTexts.values()){
                if (!string.equals(collectorInformation.getInformation().)){
                    map = collectorInformation.getInformation();
                }
            }
            //for (Map.Entry<LengthInformation, String> entry : map.entrySet()){
            System.out.println(map);
            collectorInformation.cleanInformation();*/


                /*if (entry.getKey().equals(LengthInformation.SHORT)){
                    if (FilesCreator.getCounterFiles(LengthInformation.SHORT) < 101){
                        filesCreator.createFile(entry.getValue(), url, LengthInformation.SHORT);
                        collectorInformation.cleanInformation();
                    }
                }
                if (entry.getKey().equals(LengthInformation.MIDDLE)){
                    if (FilesCreator.getCounterFiles(LengthInformation.MIDDLE) < 101){
                        filesCreator.createFile(entry.getValue(), url, LengthInformation.MIDDLE);
                        collectorInformation.cleanInformation();
                    }
                }
                if (entry.getKey().equals(LengthInformation.LONG)){
                    if (FilesCreator.getCounterFiles(LengthInformation.LONG) < 101){
                        filesCreator.createFile(entry.getValue(), url, LengthInformation.LONG);
                        collectorInformation.cleanInformation();
                    }
                }*/
            //}
            //map.clear();
        }
        System.out.println(CollectorInformation.mapProcessedTexts);
        /*for (String url : SearchEngine.getLinks()){
            else if (FilesCreator.getCounterFiles(LengthInformation.MIDDLE) < 101){
                collectorInformation.extractInformation(url, LengthInformation.MIDDLE);
            }
            else if (FilesCreator.getCounterFiles(LengthInformation.LONG) < 101){

            }
        }*/



        /*String url1 = "https://webcache.googleusercontent.com/search?q=cache:OpHtncpqFDAJ:https://fincult.info/article/obligatsii-chto-eto-takoe-i-kak-na-nikh-zarabotat/+&cd=1&hl=ru&ct=clnk&gl=ru";
        String url2 = "https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D0%BB%D0%B8%D0%B3%D0%B0%D1%86%D0%B8%D1%8F";
        String url3 = "https://webcache.googleusercontent.com/search?q=cache:3iPx7VjLlDMJ:https://ru.wikipedia.org/wiki/%25D0%259E%25D0%25B1%25D0%25BB%25D0%25B8%25D0%25B3%25D0%25B0%25D1%2586%25D0%25B8%25D1%258F+&cd=2&hl=ru&ct=clnk&gl=ru";

        CollectorInformation collectorInformation = new CollectorInformation();
        collectorInformation.extractInformation(url1);
        System.out.println(collectorInformation.getInformation());
        System.out.println(collectorInformation.getInformation().split(" ").length);
        collectorInformation.extractInformation(url2);
        System.out.println(collectorInformation.getInformation());
        System.out.println(collectorInformation.getInformation().split(" ").length);
        collectorInformation.extractInformation(url3);
        System.out.println(collectorInformation.getInformation());
        System.out.println(collectorInformation.getInformation().split(" ").length);*/





        /*CollectorInformation collectorInformation = new CollectorInformation(url1);
        collectorInformation.extractInformation();*/


        /*FilesCreator filesCreator = new FilesCreator("ertyui ghjk vbnm uiuo; rfgves avsdf");
        filesCreator.createFile();*/

















        /*for (int i = 0; i < KeyWords.values().length; i++){
            executorService.execute(new SearchEngine(KeyWords.values()));
        }*/

        /*for (KeyWords string : KeyWords.values()){
            System.out.println(string.getValue());
        }*/

        //System.out.println(KeyWords.values().length);

        /*SearchEngine searchEngine = new SearchEngine();
        searchEngine.searchLinks();

        for (String str : SearchEngine.links){
            System.out.println(str);
        }*/

        //System.out.println(KeyWords.KEY_WORD1.getValue());

    }
}
