/**
 * Created by william on 8/18/15.
 */
public class main {
    public static void main(String args[]){
        String input = "<root>" +
                "<hijo x = \"1\" y = \"2\">" +
                "<nieto />"+
                "</hijo>"+
                "<hijo x = \"2\"/>"+
                "</root>";


        LexerXml  lex = new LexerXml(input);
               Token  currentToken = lex.getXmlToken();
               try{
               while (currentToken.type != TokenType.End)
               {
                   System.out.println(currentToken.toString());
                   currentToken = lex.getXmlToken();
               }
                   System.out.println(currentToken.toString());

                }catch(Exception e){
                   System.out.println("Lexical Exception Caught");
                   System.out.printf(e.getMessage());
               }


//        ParserXml parser = new ParserXml(lex);
//        try{
//            parser.Parse();
//
//        }catch (parserException e){
//            System.out.println(e.getMessage());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
