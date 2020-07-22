package parquinho;

public class Kid {
    
    
    private int idade;
    private String nome;
    
    
    
    public String getnome(){
        return nome;
    }
    public int getidade(){
        return idade;
    }
    
    public void setnome (String nome){
     
        this.nome = nome;
    }
    public void setidade (int idade){
     
        this.idade = idade;
    }
    
    public String toString(){
    
        return nome + ":" + idade;
    
    }
    
}
