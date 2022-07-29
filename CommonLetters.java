import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

class CommonLetters{
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Dictionary<Integer, Integer> words = new Hashtable<Integer, Integer>(100000);
        String fileName = "englishWords.txt";
        int smallestWord = 10000;
        try (FileInputStream is = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(is, 1024*1024)) {
            int b;
            int word = 0;
            while ((b = bis.read()) != -1) {
                if( (char)b == '\r' ) {
                    if(word < smallestWord) smallestWord = word;
                    if(words.get(word) == null) {
                        words.put(word, 1);
                    } else {
                        words.put(word, words.get(word)+1);
                    }
                    word = 0;
                } else if ((char)b != '\n') {
                    word |= (1<<(b-97));
                }
            }
            bis.close();
        } catch(IOException e) {
            e.getStackTrace();
        }
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime-startTime + " milliseonds to read input");
        int letters[] = new int[7];
        int maxLetters[] = new int[7];
        int maxCount = 0;
        long previousTime = startTime;
        int previousNumbers[] = new int[5];
        for(int i = 0; i<20; i++) {
            for( int j = i+1; j<21; j++) {
                previousNumbers[0] = numWords(words, new int[]{i, j});
                for( int k = j+1; k<22; k++) {
                    previousNumbers[1] = numWords(words, new int[]{i,j}, k, previousNumbers[0]);
                    for( int l = k+1; l<23; l++) {
                        previousNumbers[2] = numWords(words, new int[]{i,j,k}, l, previousNumbers[1]);
                        for( int m = l+1; m<24; m++) {
                            previousNumbers[3] = numWords(words, new int[]{i,j,k,l}, m, previousNumbers[2]);
                            for( int n = m+1; n<25; n++) {
                                previousNumbers[4] = numWords(words, new int[]{i,j,k,l,m}, n, previousNumbers[3]);
                                for( int o = n+1; o<26; o++) {
                                    letters = new int[]{i,j,k,l,m,n};
                                    int numWords = numWords(words, letters, o, previousNumbers[4]);
                                    if(maxCount<numWords) {
                                        maxLetters = letters;
                                        maxCount = numWords;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Letters: " + Arrays.toString(toLetters(maxLetters)));
        System.out.println("Words: " + maxCount);
        System.out.println("Time: " + (endTime-startTime));
    }

    private static char[] toLetters(int[] numbers) {
        char letters[] = new char[numbers.length];
        for (int i = 0; i<numbers.length; i++) {
            letters[i] = (char)(numbers[i]+97);
        }
        return letters;
    }

    private static int numWords(Dictionary words, int letters[], int newLetter, int oldNumber) {
        int count = 0;
        int temp;
        int length = letters.length;
        Object num;
        for(int i = 1; i<=(1<<length); i++) {
            temp = (1<<newLetter);
            for(int j = 0; j<length; j++) {
                if( (i&(0b1<<j)) == 0b1<<j) {
                    temp |= (1<<letters[j]);
                }
            }
            num = words.get(temp);
            if( num != null) {
                count = count + (int)num;
            }
        }
        return count + oldNumber;
    }
    private static int numWords(Dictionary words, int letters[]) {
        int count = 0;
        int temp;
        int length = letters.length;
        Object num;
        for(int i = 1; i<=(1<<length); i++) {
            temp = 0;
            for(int j = 0; j<length; j++) {
                if( (i&(0b1<<j)) == 0b1<<j) {
                    temp |= (1<<letters[j]);
                }
            }
            num = words.get(temp);
            if( num != null) {
                count = count + (int)num;
            }
        }

        return count;
    }
}