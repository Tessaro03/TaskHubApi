package taskhub.infra.excepetion;

public class ValidacaoExcepetion extends RuntimeException{
    
    public ValidacaoExcepetion(String mensagem){
        super(mensagem);
    }
}
