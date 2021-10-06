import java.io.Serializable;

/**
 * Creates a test Object to be sent from the client to the server
 * @version 10-6-21
 */
public class TestObject implements Serializable {
    private String word;

    public TestObject(String inWord) {
        word = inWord;
    }

    public String getWord() {
        return this.word;
    }
}
