import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnalyzeURL {
    private synchronized boolean checkURL(char[] url){ //сомнительная необходимость в синхронизации
        int[] arrayCounters = {0, 0, 0, 0, 0};
        Set<char[]> notValidLinks = new HashSet<>(Arrays.asList("https://www.google".toCharArray(),
                "https://policies.google".toCharArray(),
                "https://support.google".toCharArray(),
                "https://www.youtube".toCharArray(),
                "https://maps.google".toCharArray()));
        char[] chGoogle1 = "https://www.google".toCharArray();
        char[] chGoogle2 = "https://policies.google".toCharArray();
        char[] chGoogle3 = "https://support.google".toCharArray();
        char[] chGoogle4 = "https://www.youtube".toCharArray();
        char[] chGoogle5 = "https://maps.google".toCharArray();


        for (char[] notValidLink : notValidLinks){
            if (url.length > notValidLink.length){

            }



        }






        int currentSize = url.length > chGoogle1.length ? chGoogle1.length : url.length;

        for (int i = 0; i < currentSize; i++){
            if (url[i] == chGoogle1[i]){
                arrayCounters[0]++;
                if (arrayCounters[0] == currentSize)
                    return false;
            }
            if (url[i] == chGoogle2[i]){
                arrayCounters[1]++;
                if (arrayCounters[1] == currentSize)
                    return false;
            }
            if (url[i] == chGoogle3[i]){
                arrayCounters[2]++;
                if (arrayCounters[2] == currentSize)
                    return false;
            }
            if (url[i] == chGoogle4[i]){
                arrayCounters[3]++;
                if (arrayCounters[3] == currentSize)
                    return false;
            }
            if (url[i] == chGoogle5[i]){
                arrayCounters[4]++;
                if (arrayCounters[4] == currentSize)
                    return false;
            }
        }
        return true;
    }
}
