package sed_keywords_maker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This class is tool for load data from file.
 */
public class AllWordsContainer {

    public static final String SOURCE_FILE_NAME = "odm.txt"; // Data file name
    private static File file;
    private static FileInputStream fileInputStream;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static boolean fileLoaded = false;
    private static boolean hasNextLine = true;
    private static String lineRead;

    /**
     * This method load file to field of this class, prepare stream and bufferedReader
     */
    public static void loadFile(){
        try{
            ClassLoader loader = AllWordsContainer.class.getClassLoader();
            file = new File(loader.getResource(SOURCE_FILE_NAME).getFile());
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            setFileLoaded(true);
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method check that the file has next line to read and if yes it save this line to field
     * of this class LINEREAD and return boolean;
     * @return Return true if file has next line to read and false if it has not.
     */
    public static boolean hasLineToRead(){
        try {
            lineRead = bufferedReader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        if (lineRead == null) hasNextLine = false;
        return hasNextLine;
    }

    /**
     * This method split read line and convert it to table of Strings.
     * @return Table of Strings contain words and keywords. First item is word and rest are keywords.
     */
    public static ArrayList<Word> getNextKeyWord(){

        String[] splitedLine = lineRead.split(",\\s+");
        ArrayList<Word> words = new ArrayList<>();
        for (String s : splitedLine) {
            Word word = new Word(replaceBadCharacterInString(s), replaceBadCharacterInString(splitedLine[0]), "0");
            words.add(word);
        }
        return  words;
    }

    /**
     * This method replace characters witch makes problems in SQL request.
     * @param str String to clean from bad characters.
     * @return Cleaned string without bad characters.
     */
    private static String replaceBadCharacterInString (String str){
        String cleanedString ="";

        for (char c : str.toCharArray()) {
            if (c!=39) {
                cleanedString += c;
            } else {
                cleanedString += " ";
            }
        }

//        for (char c : str.toCharArray()) {
//            if ( (c>64 && c<91) /*UTF8 *A-Z*/ || (c>96 && c<123) /*a-z*/ || (c>259 && c<264) /*Ą,ą,Ć,ć*/|| (c>279 && c<282) /*Ę,ę */ || (c>320 && c<325) /*Ł,ł,Ń,ń*/|| (c==211) /*Ó*/|| (c==243) /*ó*/|| (c>345 && c<348) /*Ś,ś*/ || (c>376 && c<381) /*Ź,ź,Ż,ż*/) {
//                cleanedString += c;
//            } else {
//                cleanedString += " ";
//                System.out.println(c);
//            }
//        }
        return cleanedString;
    }

    /**
     * This method release resource
     */
    public static void releaseResource (){

        if (bufferedReader != null){
            try {
                bufferedReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if (inputStreamReader != null){
            try {
                inputStreamReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if (fileInputStream != null){
            try {
                fileInputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean isFileLoaded() {
        return fileLoaded;
    }

    private static void setFileLoaded(boolean fileLoaded) {
        AllWordsContainer.fileLoaded = fileLoaded;
    }

}
