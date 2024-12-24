package dictionary.dictionarypro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This is a public DictionaryManagement class represents the management of the dictionary.
 */

public class DictionaryManagement {

    /**
     * This is a constructor.
     */

    public DictionaryManagement() {
        insertFromFile();
    }

    /**
     * This is a method to get the Dictionary from File.
     */

    public void insertFromFile() {
        try {
            File file = new File("dictionaries.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String newWordTarget = sc.next();
                String newWordType = sc.next();
                String newPronunciation = sc.next();
                String newWordExplain = sc.next() + sc.nextLine();
                Dictionary.addWord(newWordTarget, newWordType, newPronunciation, newWordExplain);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist!");
        }
    }

    /**
     * This is the method to add a word into the Dictionary.
     * @param wordTarget is the wordTarget of the word
     * @param wordType is the type of the word
     * @param pronunciation is the pronunciation of the word
     * @param wordExplain is the explanation of the word
     */

    public void addWord(String wordTarget, String wordType, String pronunciation
            , String wordExplain) {
        boolean b = false;
        int n = Dictionary.numberOfWords();
        Dictionary.addWord("", "", "", "");
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (Dictionary.at(mid).getWordTarget().compareTo(wordTarget) > 0) {
                if (mid == 0 || Dictionary.at(mid - 1).getWordTarget().compareTo(wordTarget)
                        <= 0) {
                    for (int j = n; j >= mid + 1; j--) {
                        Word w = Dictionary.at(j - 1);
                        Dictionary.at(j).setWord(w.getWordTarget(), w.getWordType()
                                , w.getPronunciation(), w.getWordExplain());
                    }
                    Dictionary.at(mid).setWord(wordTarget, wordType, pronunciation, wordExplain);
                    b = true;
                    break;
                } else {
                    r = mid - 1;
                }
            } else {
                l = mid + 1;
            }
        }
//        for (int i = 0; i < n; i++) {
//            if (Dictionary.at(i).getWordTarget().compareTo(wordTarget) > 0) {
//                for (int j = n; j >= i + 1; j--) {
//                    Word w = Dictionary.at(j - 1);
//                    Dictionary.at(j).setWord(w.getWordTarget(), w.getWordType()
//                        , w.getPronunciation(), w.getWordExplain());
//                }
//                Dictionary.at(i).setWord(wordTarget, wordType, pronunciation, wordExplain);
//                b = true;
//                break;
//            }
//        }
        if (!b) Dictionary.at(n).setWord(wordTarget, wordType, pronunciation, wordExplain);
    }

    /**
     * This is a method to remove a word from Dictionary.
     * @param wordTarget is the word you want to delete
     */

    public void removeWord(String wordTarget) {
        if (Dictionary.contains(wordTarget) != -1) {
            Dictionary.removeWord(Dictionary.contains(wordTarget));
        }
    }

    /**
     * This is a method to edit a word.
     * @param oldWordTarget is the word you want to change.
     * @param wordTarget is the word display of the new word you want to change
     * @param wordType is the type of the new word you want to change
     * @param pronunciation is the pronunciation of the word you edit
     * @param wordExplain is the explanation of the new word you want to change
     */

    public void editWord(String oldWordTarget, String wordTarget, String wordType
            , String pronunciation, String wordExplain) {
        if (Dictionary.contains(oldWordTarget) != -1) {
            Dictionary.removeWord(Dictionary.contains(oldWordTarget));
            addWord(wordTarget, wordType, pronunciation, wordExplain);
        }
    }

    /**
     * This is a method to export Dictionary to file "dictionaries.txt".
     */

    public void dictionaryExportToFile() {
        try {
            Formatter f = new Formatter("dictionaries.txt");
            for (int i = 0; i < Dictionary.numberOfWords(); i++) {
                f.format("%s\t%s\t%s\t%s", Dictionary.at(i).getWordTarget(), Dictionary.at(i)
                        .getWordType(), Dictionary.at(i).getPronunciation(), Dictionary.at(i)
                        .getWordExplain() + "\r\n");
            }
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error!");
        }
    }
}
