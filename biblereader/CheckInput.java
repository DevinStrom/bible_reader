package biblereader;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CheckInput<T> {
    
    private static final String TARGET2 = " ";
    private static final String REPLACEMENT = "";
    private static final String TARGET = ":";
    private static final String HYPHEN = "-";
    private static final int _2 = 2;

    //books- matches first few letters for each book
    private static final String BOOKS_OF_BIBLE = "(1 chr)|(1 cor)|(1 joh)|" +
                                            "(1 kin)|(1 pet)|(1 sam)|(1 the)|(1 tim)|" +
                                            "(2 chr)|(2 cor)|(2 joh)|(2 kin)|(2 pet)|" +
                                            "(2 sam)|(2 the)|(2 tim)|(3 joh)|(act)|(amo)|" +
                                            "(col)|(dan)|(deu)|(ecc)|(eph)|(est)|(exo)|" +
                                            "(exe)|(ezr)|(gal)|(gen)|(hab)|(hag)|(heb)|" +
                                            "(hos)|(isa)|(jam)|(jer)|(job)|(joe)|(joh)|" +
                                            "(jon)|(jos)|(jude)|(judg)|(lam)|(lev)|(luk)|" +
                                            "(mal)|(mar)|(mat)|(mic)|(nah)|(neh)|(num)|" +
                                            "(oba)|(phile)|(phili)|(pro)|(psa)|(rev)|" +
                                            "(rom)|(rut)|(son)|(tit)|(zec)|(zep)";
    
    //chapters- matches numbers: ex "13:" or not(:)numbers ex " 13"
    private static final String CHAPTERS = "([^:]\\d+)|(\\d+:)|([^-]\\d+)";
    //TODO add option for multiple chapters: example input: Matthew "3-4"

    //verses- 
    private static final String VERSES = "(:\\d+-\\d+)|(:\\d+)";

    @SuppressWarnings("unchecked")

    public ArrayList<T> checkInput(String input) {

        boolean bookFound = false;
        
        //check for book
        ArrayList<T> book = new ArrayList<T>();;
        //String booksOfBible = BOOKS_OF_BIBLE;
        Pattern books = Pattern.compile(BOOKS_OF_BIBLE, Pattern.CASE_INSENSITIVE);
        Pattern chapters = Pattern.compile(CHAPTERS);
        Pattern verses = Pattern.compile(VERSES);
        Matcher matcherBooks = books.matcher(input);
        Matcher matcherChapters = chapters.matcher(input);
        Matcher matcherVerses = verses.matcher(input);

        if(matcherBooks.find()) {
            bookFound = true;
            switch(matcherBooks.group().toLowerCase()) {
                case ("1 chr"):
                    book.add((T) "1 CHRONICLES.txt");
                    break;
                case ("1 cor"):
                    book.add((T) "1 CORINTHIANS.txt");
                    break;
                case ("1 joh"):
                    book.add((T) "1 JOHN.txt");
                    break;
                case ("1 kin"):
                    book.add((T) "1 KINGS.txt");
                    break;
                case ("1 pet"):
                    book.add((T) "1 PETER.txt");
                    break;
                case ("1 sam"):
                    book.add((T) "1 SAMUEL.txt");
                    break;
                case ("1 the"):
                    book.add((T) "1 THESSALONIANS.txt");
                    break;
                case ("1 tim"):
                    book.add((T) "1 TIMOTHY.txt");
                    break;   
                case ("2 chr"):
                    book.add((T) "2 CHRONICLES.txt");
                    break;
                case ("2 cor"):
                    book.add((T) "2 CORINTHIANS.txt");
                    break;
                case ("2 joh"):
                    book.add((T) "2 JOHN.txt");
                    break;     
                case ("acts"):
                    book.add((T) "ACTS.txt");
                    break;  
                case ("amo"): 
                    book.add((T) "AMOS.txt");
                    break;
                case ("col"): 
                    book.add((T) "COLOSSIANS.txt");
                    break;
                case ("dan"): 
                    book.add((T) "DANIEL.txt");
                    break;
                case ("deu"): 
                    book.add((T) "DEUTERONOMY.txt");
                    break;
                case ("ecc"): 
                    book.add((T) "ECCLESIASTES.txt");
                    break;
                case ("eph"): 
                    book.add((T) "EPHESIANS.txt");
                    break;
                case ("est"): 
                    book.add((T) "ESTHER.txt");
                    break;
                case ("exo"): 
                    book.add((T) "EXODUS.txt");
                    break;
                case ("eze"): 
                    book.add((T) "EZEKIEL.txt");
                    break;
                case ("ezr"): 
                    book.add((T) "EZRA.txt");
                    break;
                case ("gal"): 
                    book.add((T) "GALATIANS.txt");
                    break;
                case ("gen"): 
                    book.add((T) "GENESIS.txt");
                    break;
                case ("hab"): 
                    book.add((T) "HABAKKUK.txt");
                    break;
                case ("hag"): 
                    book.add((T) "HAGGAI.txt");
                    break;
                case ("heb"): 
                    book.add((T) "HEBREWS.txt");
                    break;
                case ("hos"): 
                    book.add((T) "HOSEA.txt");
                    break;
                case ("isa"): 
                    book.add((T) "ISAIAH.txt");
                    break;
                case ("jam"): 
                    book.add((T) "JAMES.txt");
                    break;
                case ("jer"): 
                    book.add((T) "JEREMIAH.txt");
                    break;
                case ("job"): 
                    book.add((T) "JOB.txt");
                    break;
                case ("joe"): 
                    book.add((T) "JOEL.txt");
                    break;
                case ("joh"): 
                    book.add((T) "JOHN.txt");
                    break;
                case ("jon"): 
                    book.add((T) "JONAH.txt");
                    break;
                case ("jos"): 
                    book.add((T) "JOSHUA.txt");
                    break;
                case ("jude"): 
                    book.add((T) "JUDE.txt");
                    break;
                case ("judg"): 
                    book.add((T) "JUDGES.txt");
                    break;
                case ("lam"): 
                    book.add((T) "LAMENTATIONS.txt");
                    break;
                case ("lev"): 
                    book.add((T) "LEVITICUS.txt");
                    break;
                case ("luk"): 
                    book.add((T) "LUKE.txt");
                    break;
                case ("mal"): 
                    book.add((T) "MALACHI.txt");
                    break;
                case ("mar"): 
                    book.add((T) "MARK.txt");
                    break;
                case ("mat"): 
                    book.add((T) "MATTHEW.txt");
                    break;
                case ("mic"): 
                    book.add((T) "MICAH.txt");
                    break;
                case ("nah"): 
                    book.add((T) "NAHUM.txt");
                    break;
                case ("neh"): 
                    book.add((T) "NEHEMIAH.txt");
                    break;
                case ("num"): 
                    book.add((T) "NUMBERS.txt");
                    break;
                case ("oba"): 
                    book.add((T) "OBADIAH.txt");
                    break;
                case ("phile"): 
                    book.add((T) "PHILEMON.txt");
                    break;
                case ("phili"): 
                    book.add((T) "PHILIPPIANS.txt");
                    break;
                case ("pro"): 
                    book.add((T) "PROVERBS.txt");
                    break;
                case ("psa"): 
                    book.add((T) "PSALMS.txt");
                    break;
                case ("rev"): 
                    book.add((T) "REVELATION.txt");
                    break;
                case ("rom"): 
                    book.add((T) "ROMANS.txt");
                    break;
                case ("rut"): 
                    book.add((T) "RUTH.txt");
                    break;
                case ("son"): 
                    book.add((T) "SONG OF SOLOMAN.txt");
                    break;
                case ("tit"): 
                    book.add((T) "TITUS.txt");
                    break;
                case ("zec"): 
                    book.add((T) "ZECHARIAH.txt");
                    break;
                case ("zep"): 
                    book.add((T) "ZEPHANIAH.txt");
                    break;
                default:
                    book.add((T) "unable to match");
            }
        }
        else {
            book.add((T) "unable to match");
        }

        if(matcherChapters.find()) {
            T matcherChapterMinusColon = 
                (T) matcherChapters.group().replace(TARGET, REPLACEMENT).replace(TARGET2, REPLACEMENT);
            book.add(matcherChapterMinusColon);
        }

        if(matcherVerses.find()) {
            String [] versesSplit = 
                matcherVerses.group().replace(TARGET, REPLACEMENT).split(HYPHEN, _2);
            for(String str : versesSplit) {
                book.add((T) str);
            }
        }

        if(bookFound) {
            return book;
        }
        else {
            return null;
        }
    }
}
