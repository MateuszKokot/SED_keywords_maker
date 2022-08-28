package sed_keywords_maker;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is tool for convert data from file to database records.
 */
public class Kernel {
    /**
     * This method convert data from file to database record if conditions are true.
     * @param isConnection Put in here DBConnection.isDBConnected(). This method return TRUE if
     *                     connection witch database is done.
     * @param isFileLoaded Put in AllWordsContainer.isFileLoaded(). This method return TRUE if
     *                     file is loaded.
     */
    public static void doYourJob (boolean isConnection, boolean isFileLoaded){

        int ordinalNumberOfWord = 1;
        boolean hasLineToRead;
        String updateString = "INSERT INTO " + Config.getTableName() + " VALUES (NULL, ?, ?, ?); ";

        if (isConnection && isFileLoaded){
            try {
                do {
                    hasLineToRead = AllWordsContainer.hasLineToRead();

                    if (hasLineToRead){
                        for (Word w : AllWordsContainer.getNextKeyWord()) {

                            PreparedStatement updateSales = DBConnection.connection.prepareStatement(updateString);
                            updateSales.setString(1,w.getWord());
                            updateSales.setString(2,w.getKeyWord());
                            updateSales.setString(3,"0");
                            updateSales.executeUpdate();
                            System.out.println(ordinalNumberOfWord + " - " + w.getWord() + " - " + w.getKeyWord());
                            ordinalNumberOfWord++;
                        }
                    }
                } while (hasLineToRead);
            } catch (SQLException e)   {
                e.printStackTrace();
                System.exit(0);
            }
        }

        DBConnection.releaseResource();
        AllWordsContainer.releaseResource();

    }

}
