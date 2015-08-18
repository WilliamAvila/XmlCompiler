import java.util.Hashtable;

/**
 * Created by william on 8/18/15.
 */
public class Token {
    public TokenType type;
    String lexeme;

    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public String toString(){
        return "Type:" + type.toString() + "\nlexeme: " + lexeme;
    }
}
