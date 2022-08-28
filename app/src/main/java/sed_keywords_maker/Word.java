package sed_keywords_maker;

/**
 * This class is representation of pair word and keyword.
 */
public class Word {
    private String word;
    private String keyWord;
    private String popularityOfKeyword;

    /**
     * This constructor creat new Word with specific params.
     * @param word String represents word in pair word and keyword.
     * @param keyWord String represents keyword in pair word and keywords.
     * @param popularityOfKeyword Popularity of searching this keyword. Value for new keyword is
     *                            set up as zero.
     */
    public Word(String word, String keyWord, String popularityOfKeyword) {
        this.word = word;
        this.keyWord = keyWord;
        this.popularityOfKeyword = popularityOfKeyword;
    }

    /**
     * Getters and Setters
     * @return return fields of this class.
     */
    public String getWord() {
        return word;
    }
    private void setWord(String word) {
        this.word = word;
    }
    public String getKeyWord() {
        return keyWord;
    }
    private void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public String getPopularityOfKeyword() {
        return popularityOfKeyword;
    }
    private void setPopularityOfKeyword(String popularityOfKeyword) {
        this.popularityOfKeyword = popularityOfKeyword;
    }
}
