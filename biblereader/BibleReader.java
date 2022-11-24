package biblereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class BibleReader {

    private static final int _0 = 0;
    private static final int _1 = 1;
    private static final int _2 = 2;
    private static final int _3 = 3;

    public static <T> void main(String [] args) {
        //get user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String textToRead = reader.readLine();
            CheckInput check = new CheckInput();
            ArrayList<T> bibleTextToRead = check.checkInput(textToRead);
            int size = check.checkInput(textToRead).size();
            
            if(size == 4) {
                ReadText text = new ReadText(bibleTextToRead.get(_0).toString(),
                Integer.parseInt((String)bibleTextToRead.get(_1)), Integer.parseInt((String) bibleTextToRead.get(_2)),
                    Integer.parseInt((String) bibleTextToRead.get(_3)));
                System.out.println(text.getText());
            }
            else if(size == 3) {
                ReadText text = new ReadText(bibleTextToRead.get(_0).toString(),
                    Integer.parseInt((String)bibleTextToRead.get(_1)), Integer.parseInt((String)bibleTextToRead.get(_2)));
                System.out.println(text.getText());
            }
            else if(size == 2) {
                ReadText text = new ReadText(bibleTextToRead.get(_0).toString(),
                    Integer.parseInt((String)bibleTextToRead.get(_1)));
                System.out.println(text.getText());
            }
            else if(size == 1) {
                ReadText text = new ReadText(bibleTextToRead.get(_0).toString());
                System.out.println(text.getText());
            }

        //ReadText text = new ReadText(bibleTextToRead.get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}