/**
 * Created by william on 8/18/15.
 */
public class ParserXml {
    private Token currentToken;
    LexerXml lexer;

    public ParserXml(LexerXml lex){
        this.lexer = lex;
        this.consumeToken();
        System.out.println(currentToken.toString());
    }

    public void consumeToken(){
        this.currentToken = lexer.getXmlToken();
        System.out.println(currentToken.toString());
    }

    public void Parse() throws Exception {
        ListaNodos();
        if(currentToken.type != TokenType.End){
            throw new parserException("Se esperaba Eof");
        }
    }

    private void ListaNodos() throws parserException {
        if(currentToken.type == TokenType.LessThan) {
            Nodo();
            ListaNodos();
        }else{
            //epsilon
        }
    }

    public void Nodo() throws parserException {
        if(currentToken.type == TokenType.LessThan) {
            consumeToken();
            if(currentToken.type != TokenType.Id)
                throw new  parserException("Se esperaba ID");

            consumeToken();

            ListaAtributos();
            NodoP();
        }else{

            throw new parserException("Se esperaba <");
        }
    }

    private void NodoP() throws parserException {
        if(currentToken.type == TokenType.SlashGreaterThan){
            consumeToken();
        }else if(currentToken.type == TokenType.GreaterThan){
            consumeToken();

            ListaNodos();


            if(currentToken.type != TokenType.LessThanSlash)
                throw new parserException("Se esperaba </");

            consumeToken();



            if(currentToken.type != TokenType.Id)
                throw  new parserException("Se esperaba ID");
            consumeToken();
            if(currentToken.type != TokenType.GreaterThan)
                throw  new parserException("Se esperaba >");
            consumeToken();

        }else {
            throw new parserException("Se esperaba / o >");
        }
    }

    private void ListaAtributos() throws parserException {
        if(currentToken.type == TokenType.Id) {
            Atributo();
            ListaAtributos();
        }else{
            //epsilon
        }
    }

    private void Atributo() throws parserException {
        if(currentToken.type != TokenType.Id)
            throw new parserException("Se esperaba Id");

        consumeToken();

        if(currentToken.type != TokenType.Equal)
            throw new parserException("Se esperaba =");

        consumeToken();

        if(currentToken.type != TokenType.Text)
            throw new parserException("Se esperaba Cadena");

        consumeToken();



    }


}
