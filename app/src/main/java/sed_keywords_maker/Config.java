package sed_keywords_maker;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is tool for load and save config from file.
 */
public class Config {
    /**
     * Field od Config class
     */
    private static String host;
    private static String port;
    private static String database;
    private static String userName;
    private static String password;
    private static String tableName;
    private static boolean configLoaded = false;
    public static final String CONFIG_FILE_PATH = "app/src/main/resources/config.json";

    /***
     * It is inner class of Config class. It is used for convert static fields
     * of Config class to variables witch can save to file.
     */
    private class AdapterConfig {
        private String host;
        private String port;
        private String database;
        private String userName;
        private String password;
        private String tableName;

        /**
         * Constructor witch set up fields of Config class.
         */
        AdapterConfig(){
            this.host = Config.getHost();
            this.port = Config.getPort();
            this.database = Config.getDatabase();
            this.userName = Config.getUserName();
            this.password = Config.getPassword();
            this.tableName = Config.getTableName();
        }
    }


    /**
     * This method load configuration from File
     */
     public static void loadConfigFromFile(){
        Gson gson = new Gson();
        try {

            Reader reader = Files.newBufferedReader(Paths.get(CONFIG_FILE_PATH));
            AdapterConfig adapterConfig = gson.fromJson(reader,AdapterConfig.class);
            reader.close();

            setHost(adapterConfig.host);
            setPort(adapterConfig.port);
            setDatabase(adapterConfig.database);
            setUserName(adapterConfig.userName);
            setPassword(adapterConfig.password);
            setTableName(adapterConfig.tableName);
            setConfigLoaded(true);
        }catch(IOException e){
            e.printStackTrace();
            setConfigLoaded(false);
        }
    }

    /**
     * This method save configuration to File.
     */
    public static void saveConfigToFile(){
        Config config = new Config();
        config.saveConfigToFileAdapter();
    }

    /**
     * This method print in console config variables loaded from file
     */
    public static void showConfig(){
        System.out.println("[ Config ]");
        System.out.println(">Host: " + getHost());
        System.out.println(">Port: " + getPort());
        System.out.println(">Database: " + getDatabase());
        System.out.println(">UserName: " + getUserName());
        System.out.println(">Password: " + getPassword());
    }

    /***
     * This method is adapter between static fields in Config class and config file.
     * It is used for saved config to file in saveConfigToFile() method.
     */
    private void saveConfigToFileAdapter(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            AdapterConfig adapterConfig = new AdapterConfig();
            String json = gson.toJson(adapterConfig);
            FileWriter fileWriter = new FileWriter(CONFIG_FILE_PATH);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Getters and Setters
     * @return Fields of this class
     */
    public static String getHost() {
        return host;
    }

    private static void setHost(String host) {
        Config.host = host;
    }

    public static String getPort() {
        return port;
    }

    private static void setPort(String port) {
        Config.port = port;
    }

    public static String getDatabase() {
        return database;
    }

    private static void setDatabase(String database) {
        Config.database = database;
    }

    public static String getUserName() {
        return userName;
    }

    private static void setUserName(String userName) {
        Config.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    private static void setPassword(String password) {
        Config.password = password;
    }

    public static boolean isConfigLoaded() {
        return configLoaded;
    }

    private static void setConfigLoaded(boolean configLoaded) {
        Config.configLoaded = configLoaded;
    }

    public static String getTableName() {
        return tableName;
    }
    private static void setTableName(String tableName) {
        Config.tableName = tableName;
    }
}
