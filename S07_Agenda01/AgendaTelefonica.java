package agendatelefonica;
import java.util.ArrayList;
import java.util.Scanner;



class telefone{
    
    public String id;
    public String numero;
    
     public telefone(String id,String numero){
        
        this.id = id;
        this.numero = numero;
     }
     
     @Override
        public String toString(){
            return id + ":" + numero;
        }
        
       public static boolean validar(String numero){
           
           String validos = "0123456789().-";
           
           for(int i = 0; i < numero.length(); i++){
           char c  = numero.charAt(i);
            if(validos.indexOf(c) == -1)
                return false;
            }
            
                return true;
        }
}

class contato{
   
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<telefone> getFone() {
        return fone;
    }

    public void setFone(ArrayList<telefone> fone) {
        this.fone = fone;
    }
    private ArrayList<telefone> fone;
    
    public contato(String nome){
        this.nome = nome;
        fone = new ArrayList<>();
    }
    public String toString(){
        
        int indice = 0;
        
        String saida = "-" + this.nome+"\n";
        if(fone.size()>0)
            System.out.println("\n");
        for(telefone fone : this.fone){ 
            
         saida += "[" + indice  + ":" + fone + "]\n";
         indice++;
          
       } return saida;
    }   
    
    public void addFone(String id,String numero){
        if(telefone.validar(numero))
            fone.add(new telefone(id, numero));
        else
            System.out.println("fail: fone invalido");
    
    }
    public void rmFone(int indice){
        if(indice < 0 || indice >= fone.size()){
        
        }
        else
            fone.remove(indice);
    }
}


public class AgendaTelefonica {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        contato contato = new contato("");
            while(true){
                String line = scanner.nextLine();
                String ui[] = line.split(" ");
                
                if(ui[0].equals("end")){
                    break;
                }
                
                else if(ui[0].equals("init"))
                   contato.addFone(ui[1], ui[2]);
            
           
                else if(ui[0].equals("rm"))
                    contato.rmFone(Integer.parseInt(ui[1]));
            
    
                else if(ui[0].equals("show"))
                    System.out.println(contato);
                
                else
                    System.out.println("fail: comando invalido");
                        
    }
    
}
}
