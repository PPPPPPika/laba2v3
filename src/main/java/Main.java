import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (KeyWords keyWord : KeyWords.values())
            executorService.execute(new SearchEngine(keyWord.getValue()));
        executorService.shutdown();


        //test
        try {
            Thread.sleep(5000);
            System.out.println(executorService.isTerminated());
            for (String s : SearchEngine.links){
                System.out.println(s);
            }
            System.out.println(SearchEngine.links.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
