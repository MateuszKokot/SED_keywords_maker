package sed_keywords_maker;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Kernel {
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
