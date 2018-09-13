import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args) {
        int wordCount = 0;
        ArrayList<Word> wordList = new ArrayList<Word>();
        try {
            Scanner scanner = new Scanner(new FileReader("dream.txt"));
            String str = "";
            while(scanner.hasNextLine()) {
                str = scanner.nextLine();
                String[] words = str.split(" ");
                for(String word : words) {

                    if(!(word.equals("-")) && !(word.equals(""))) {
                        wordCount++;
                        if(contains(wordList, word)) {
                            int index = indexOf(wordList,word);
                            wordList.get(index).addOccurrence();
                        } else {
                            wordList.add(new Word(word));

                        }
                    }
                }
            }
        }
        catch(FileNotFoundException fe) {
        }

        ArrayList<Word> sortedList = mergeSort(wordList);

        for(int i = 0; i < 30; i++) {
            System.out.println((i+1) + ": " + sortedList.get(i).getWord() + " | Occurrence: " + sortedList.get(i).getOccurrence());
        }
        System.out.println("Word Count = " + wordCount);
        System.out.println("Unique Word Count = " + sortedList.size());
    }

    private static ArrayList<Word> mergeSort(ArrayList<Word> list){
        if(list.size() < 2) {
            return list;
        }
        int mid = list.size()/2;
        ArrayList<Word> left = new ArrayList<Word>();
        ArrayList<Word> right = new ArrayList<Word>();
        for(int i = 0; i < mid; i++) {
            left.add(list.get(i));
        }
        for(int i = mid; i < list.size(); i++) {
            right.add(list.get(i));
        }
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int indexOf(ArrayList<Word> list, String w) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getWord().equals(w)) {

                return i;            
            }
        }
        return -1;
    }

    public static ArrayList<Word> merge(ArrayList<Word> left, ArrayList<Word> right){
        int i = 0;
        int j = 0;
        ArrayList<Word> list = new ArrayList<Word>();
        for(int e = 0; e < left.size(); e++) {
            list.add(left.get(e));
        }
        
        for(int e = 0; e < right.size(); e++) {
            list.add(right.get(e));
        }
        
        for(int k = 0; k < list.size(); k++) {
            if(i >= left.size()){
                list.set(k, right.get(j++));
            }
            else if(j >= right.size()) {
                list.set(k, left.get(i++));
            }
            else if(left.get(i).getOccurrence() <= right.get(j).getOccurrence()) {
                list.set(k, left.get(i));
                i++;
            }
            else list.set(k, right.get(j++));
        }
        return list;

    } 

    public static boolean contains(ArrayList<Word> list, String w) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getWord().equals(w)) {
                return true;
            }

        }
        return false;
    }
}
