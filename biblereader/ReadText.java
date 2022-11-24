package biblereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadText {

    private static final int _1 = 1;
    private static final int _2 = 2;
    private static final int _3 = 3;
    private static final int _4 = 4;
    
    String book;
    int chapter;
    int startingVerse;
    int endingVerse;
    String finalText = "";

    public ReadText(){}

    public ReadText(String book) {
        this.book = book;
    }

    public ReadText(String book, int chapter) {
        this.book = book;
        this.chapter = chapter;
    }

    public ReadText(String book, int chapter, int startingVerse) {
        this.book = book;
        this.chapter = chapter;
        this.startingVerse = startingVerse;
    }

    public ReadText(String book, int chapter, int startingVerse, int endingVerse) {
        this.book = book;
        this.chapter = chapter;
        this.startingVerse = startingVerse;
        this.endingVerse = endingVerse;
    }

    public String getBook() {
        return book;
    }

    public int getChapter() {
        return chapter;
    }

    public int getStartingVerse() {
        return startingVerse;
    }

    public int getEndingVerse() {
        return endingVerse;
    }

    public String getText() {
        if(book != null && chapter != 0 && startingVerse != 0 && endingVerse != 0) {
            int [] verses = new int[endingVerse-startingVerse+1];
            int count = 0;
            for(int index = startingVerse; index <= endingVerse; index++) {
                verses[count] = index;
                count++;
            }
            finalText = checkBook(book, chapter, startingVerse, endingVerse, _4, verses);
        }
        else if(book != null && chapter != 0 && startingVerse != 0 && endingVerse == 0) {
            finalText = checkBook(book, chapter, startingVerse, endingVerse, _3, new int[0]);
        }
        else if(book != null && chapter != 0 && startingVerse == 0 && endingVerse == 0) {
            finalText = checkBook(book, chapter, startingVerse, endingVerse, _2, new int[0]);
        }
        else if(book != null && chapter == 0 && startingVerse == 0 && endingVerse == 0) {
            finalText = checkBook(book, chapter, startingVerse, endingVerse, _1, new int[0]);
        }
        return finalText; 
    }

    private String checkBook(String book, int chapter, int startingVerse, int endingVerse, int numberActualArgs, int [] totalVerses) {
        StringBuilder bookString = new StringBuilder();
        File file = new File("books_of_bible/" + book);
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            if(numberActualArgs == 1) {
                while((line = br.readLine()) != null){
                    bookString.append(line + "\n");  
                }
            }
            else if(numberActualArgs == 2) {
                while((line = br.readLine()) != null){
                    if(line.startsWith(chapter + ":")) {
                        bookString.append(line + "\n");  
                    }
                }
            }
            else if(numberActualArgs == 3) {
                while((line = br.readLine()) != null){
                    if(line.startsWith(chapter + ":" + startingVerse)) {
                        bookString.append(line + "\n");  
                    }
                }
            }
            else if(numberActualArgs == 4) {
                while((line = br.readLine()) != null){
                    for(int index : totalVerses) {
                        if(line.startsWith(chapter + ":" + index)) {
                            bookString.append(line + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookString.toString();
    }
}
