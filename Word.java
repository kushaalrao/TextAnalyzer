
/**
 * Write a description of class Word here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Word
{
    private String word;
    private int occurrence;
    
    public Word(String w) {
        word = w;
        occurrence = 1;
    }
    
    public String getWord() {
        return word;
    }
    
    public int getOccurrence() {
        return occurrence;
    }
    
    public void addOccurrence() {
        occurrence++;
    }
}
