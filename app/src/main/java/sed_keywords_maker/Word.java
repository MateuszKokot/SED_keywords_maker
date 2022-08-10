package sed_keywords_maker;

public class Word {
    private String word;
    private String keyWord;



    private String popularityOfKeyword;

    public Word(String word, String keyWord, String popularityOfKeyword) {
        this.word = word;
        this.keyWord = keyWord;
        this.popularityOfKeyword = popularityOfKeyword;
    }

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
