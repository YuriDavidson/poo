public class Carro_Corrida {
     
    String nome_carro = "Willians";
    int velocidade_maxima = 290;
    int tanque_combustivel = 100;
    int voltas_pneus = 25; 
    int total_voltas = 0;
    
    
    int voltas_pista = 62;
    int consumo_combusivel = 4;
    
    
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
        
        System.out.println("Scuderia: "+carro.nome_carro);
        
        for(carro.total_voltas = 0; carro.total_voltas < carro.voltas_pista; carro.total_voltas ++){
        System.out.println("Total De Voltas:" + carro.total_voltas);
        carro.correndo_pista();
        carro.gasto_pneus();
        carro.estado_pneus();
        carro.trocar_pneus();
        carro.combustivel();
        carro.abastecer();
        
        }
        
        if(carro.total_voltas == carro.voltas_pista){
        
            System.out.println("A Corrida Acabou");
            
        }
    }
    
}
