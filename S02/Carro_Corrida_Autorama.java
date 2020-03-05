
import javax.swing.JOptionPane;

public class Carro_Corrida {
     
    String nome_carro = "Willians";
    int velocidade_maxima = 290;
    int tanque_combustivel = 100;
    int voltas_pneus = 25; 
    int total_voltas = 0;
    
    
    int voltas_pista = 62;
    int consumo_combusivel = 5;
    
    
    boolean combustivel(){
            
        if(this.tanque_combustivel + 1 <= this.consumo_combusivel){
            
            System.out.println("Combustivel está no fim !!!");
                
            return true;
        }
        System.out.println("combustivel ok");
        return false;
    }
    
    boolean estado_pneus(){
    
      if(this.voltas_pneus <= 1 ){
      
          System.out.println("Precisa trocar os pneus na proxima volta");
          
          return true;
      }  
        System.out.println("pneus ok");
            return false;
    
    }
    
    void correndo_pista(){
    
        this.tanque_combustivel -= this.consumo_combusivel;
       
    }
    
    void trocar_pneus(){
        
        if(this.voltas_pneus <= 1 ){
            
          System.out.println("Novos pneus colocados...");
          this.voltas_pneus = 25;
          
      }  
    }
    
    boolean abastecer (){
        
      if  (this.tanque_combustivel + 1 <= this.consumo_combusivel){
        
        this.tanque_combustivel =+ 50;
        
        return true;
        
      }
      return false;
     }
    
    void gasto_pneus(){
    
         this.voltas_pneus --;
         
         
        }
    
    public static void main(String[] args) {
        
        Carro_Corrida carro = new Carro_Corrida();
        Carro_Corrida corrida = carro;
        
        boolean troquei_pneus ;
        boolean tanque;
        
        System.out.println("Scuderia: "+carro.nome_carro);
        
        int Iniciar_Corrida = JOptionPane.showConfirmDialog
        (null, "A Corrida irá começar, ligue o motor AGORA!", "A corrida vai começar", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if(Iniciar_Corrida == JOptionPane.YES_OPTION){
        
        while(carro.total_voltas <= carro.voltas_pista){
            
        System.out.println("Total De Voltas:" + carro.total_voltas);
        carro.correndo_pista();
        carro.combustivel();
        carro.gasto_pneus();
        
        troquei_pneus = carro.estado_pneus() ;
        tanque = carro.combustivel();
        
        if(troquei_pneus == true){
        
         int troque_peneus = JOptionPane.showConfirmDialog
        (null, "Você quer trocar os pneus?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if(troque_peneus == JOptionPane.NO_OPTION) {
            System.out.println("Seus Pneus estouraram !!!\n"
                    + "Fim da corrida");
            
            break;
        }
            
        }
     
        carro.trocar_pneus();
        
        if(tanque == true){
        
         int troque_peneus = JOptionPane.showConfirmDialog
        (null, "Você quer abastecer o carro?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if(troque_peneus == JOptionPane.NO_OPTION) {
            
            System.out.println("Acabou a gasolina !!! \n"
                    + "Fim da corrida pra você");
            
            break;
        }
            
        }
        
        carro.abastecer();
        
        if(carro.total_voltas == carro.voltas_pista){
        
            System.out.println("A Corrida Acabou \n"
                    + "Parabéns !!!");
            
        }
           
        carro.total_voltas ++;
        
    }
}
        else{
        
             System.out.println("A Corrida Começou,e você nem saiu do lugar *-*");    
            
        }
    }
}
