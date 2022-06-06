import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

class CommonLetters{
    public static final int THREADS = 6;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Dictionary<Set<Character>, Integer> words = new Hashtable<Set<Character>, Integer>(100000);
        String fileName = "englishWords.txt";
        try (FileInputStream is = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(is, 1024*1024)) {
            int b;
            Set<Character> word = new HashSet<Character>();
            while ((b = bis.read()) != -1) {
                if( (char)b == '\r' ) {
                    if(words.get(word) == null) {
                        words.put(word, 1);
                    } else {
                        words.put(word, words.get(word)+1);
                    }
                    word = new HashSet<Character>();
                } else {
                    word.add((char)b);
                }
            }
            bis.close();
        } catch(IOException e) {
            e.getStackTrace();
        }
        char letters[] = new char[7];
        char maxLetters[] = new char[7];
        int maxCount = 0;
        long previousTime = startTime;
        long currentTime;
        int previousNumbers[] = new int[5];
        for(int i = 0; i<20; i++) {
            for( int j = i+1; j<21; j++) {
                }
            }
            currentTime = System.currentTimeMillis();
            System.out.println("Progress: " + (char)(i+97) + "\nTime: " + (currentTime - previousTime)/1000.0);
            previousTime = currentTime;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Letters: " + Arrays.toString(maxLetters));
        System.out.println("Words: " + maxCount);
        System.out.println("Time: " + (endTime-startTime)/1000);
    }
    

        return count;
    }

    public class findMaxLetters implements Runnable {
        int[] previousNumbers;
        int i;
        int j;
        Integer maxCount;
        public findMaxLetters(Integer maxCount, int i, int j) {

        }
        public void run() {
            previousNumbers[0] = numWords(words, new char[]{(char)(i+97),(char)(j+97)});
                for( int k = j+1; k<22; k++) {
                    previousNumbers[1] = numWords(words, new char[]{(char)(i+97),(char)(j+97)}, (char)(k+97), previousNumbers[0]);
                    for( int l = k+1; l<23; l++) {
                        previousNumbers[2] = numWords(words, new char[]{(char)(i+97),(char)(j+97),(char)(k+97)}, (char)(l+97), previousNumbers[1]);
                        for( int m = l+1; m<24; m++) {
                            previousNumbers[3] = numWords(words, new char[]{(char)(i+97),(char)(j+97),(char)(k+97),(char)(l+97)}, (char)(m+97), previousNumbers[2]);
                            for( int n = m+1; n<25; n++) {
                                previousNumbers[4] = numWords(words, new char[]{(char)(i+97),(char)(j+97),(char)(k+97),(char)(l+97),(char)(m+97)}, (char)(n+97), previousNumbers[3]);
                                for( int o = n+1; o<26; o++) {
                                    letters = new char[]{(char)(i+97),(char)(j+97),(char)(k+97),(char)(l+97),(char)(m+97),(char)(n+97)};
                                    Integer numWords = numWords(words, letters, (char)(o+97), previousNumbers[4]);
                                    if(maxCount<numWords) {
                                        maxLetters = letters;
                                        maxCount = numWords;
                                    }
                                }
                            }
                        }
                    }
        }

        private static int numWords(Dictionary words, char letters[], char newLetter, int oldNumber) {
            int count = 0;
            Set<Character> temp;
            int length = letters.length;
            for(int i = 1; i<=(1<<length); i++) {
                temp = new HashSet<Character>(Arrays.asList('\n', newLetter));
                for(int j = 0; j<length; j++) {
                    if( (i&(0b1<<j)) == 0b1<<j) {
                        temp.add(letters[j]);
                    }
                }
                if( words.get(temp) != null) {
                    count = count + (int)words.get(temp);
                }
            }
            return count + oldNumber;
        }
        private static int numWords(Dictionary words, char letters[]) {
            int count = 0;
            Set<Character> temp;
            int length = letters.length;
            for(int i = 1; i<=(1<<length); i++) {
                temp = new HashSet<Character>(Arrays.asList('\n'));
                for(int j = 0; j<length; j++) {
                    if( (i&(0b1<<j)) == 0b1<<j) {
                        temp.add(letters[j]);
                    }
                }
                if( words.get(temp) != null) {
                    count = count + (int)words.get(temp);
                }
            }
    }
}