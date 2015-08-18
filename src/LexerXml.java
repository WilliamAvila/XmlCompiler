/**
 * Created by william on 8/18/15.
 */
public class LexerXml {
    public Token currenToken;
    public String input;
    public int position =0;
    public LexerXml(String input){
        this.input = input;
    }

    public char getCurrentSymbol() {
        if(position<input.length()){
            return input.charAt(position);
        }
        return '\u0000';
    }

    public char getNextSymbol() {
        position++;
        return getCurrentSymbol();
    }

    public Token getXmlToken(){
        int state =0;
        char symbol = getCurrentSymbol();
        String lexeme = "";
        while (true){
            switch (state) {
                case 0:
                    if(symbol=='\u0000')
                        return new Token(TokenType.End, lexeme);
                    else if(symbol == '\"'){
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        state = 2;
                    }
                    else if( Character.isLetter(symbol)){
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        state = 1;
                    }
                    else if(symbol == '='){
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        return  new Token(TokenType.Equal,lexeme);
                    }else if( symbol == '<'){
                        lexeme += symbol;
                        state = 4;

                    }
                    else if( symbol == '>'){
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        return  new Token(TokenType.GreaterThan,lexeme);
                    }
                    else if( symbol == '/'){
                        lexeme += symbol;
                        state = 5;
                    }
                    else if(Character.isWhitespace(symbol)){
                        symbol = getNextSymbol();
                    }
                break;

                case 1:
                    if(Character.isLetter(symbol)) {
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        state = 1;
                    }else{
                        return new Token(TokenType.Id, lexeme);
                    }

                    break;

                case 2:

                    if(symbol != '\"'){
                        lexeme+=symbol;
                        symbol = getNextSymbol();
                        state = 2;
                    }else if(symbol == '\"'){
                        lexeme += symbol;
                        symbol = getNextSymbol();
                        state = 3;
                    }
                    break;

                case 3:
                    return  new Token(TokenType.Text,lexeme);


                case 4:
                    String lessThan =  lexeme;
                    symbol = getNextSymbol();
                    if(symbol == '/') {
                        lexeme+= symbol;
                        symbol = getNextSymbol();
                        return new Token(TokenType.LessThanSlash, lexeme);
                    }else{
                        return new Token(TokenType.LessThan, lexeme);

                    }



                case 5:
                    symbol = getNextSymbol();
                    lexeme += symbol;
                    if(symbol == '>') {
                        return new Token(TokenType.SlashGreaterThan, lexeme);
                    }


            }
        }
    }



}
