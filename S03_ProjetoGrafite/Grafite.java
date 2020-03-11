package grafite;
import java.util.Scanner;


class Ponta {
    double ponta_grafite;
    public Ponta(double ponta){ //nao eh metodo, eh invocado automaticaticamente na criacao
        
        this.ponta_grafite = ponta;
    }
}


public class Grafite {

    Ponta grafite;
    int tamanho_ponta;
    int qtd_escrever;
    
        public Grafite(int tamanho_ponta){
                this.tamanho_ponta = tamanho_ponta;
                
     }
       void usar(Ponta grafite){
        
        if(this.grafite == null)
			this.grafite = grafite;
		else
			System.out.println("Nenhuma Ponta colocada...");
        
        }   
    
        void escrever(int qtd_escrever){
            
            this.qtd_escrever = qtd_escrever;
            
            
            if(this.grafite == null)
		System.out.println("não tem nenhuma ponta");
            if(this.tamanho_ponta < 2)
		System.out.println("O tamanho da ponta eh insuficiente para escrever algo");
            else if(this.tamanho_ponta > 15)
		System.out.println("Tamanho da ponta eh muito grande para o grafite");
            
            else{
                for(int i = 0; i < qtd_escrever; i++){
            
			System.out.println("Escrevendo...");
                        
                }tamanho_ponta -= this.qtd_escrever;
            }     
        }
        
       
        
        void trocar_ponta() {
		if(this.grafite != null &&  this.tamanho_ponta < 2){
			this.tamanho_ponta = 0;
                        this.grafite = null;
                System.out.println("Ponta Reirada");
                
               
                }else
			System.out.println("Não precisa trocar ");
	}
        
        void colocar_ponta(int colocando_ponta) {
            
            this.tamanho_ponta = colocando_ponta;
            System.out.println("Nova ponta de grafite colocada");
	}
        
        
    public static void main(String[] args) {
        
                Scanner scanner = new Scanner(System.in);
		Grafite grafite  = new Grafite(1);
        
		while(true) {
			String line = scanner.nextLine();
			String ui[] = line.split(" ");
			if(ui[0].equals("terminar")) {
				break;
                                
                        }
                        
                        else if(ui[0].equals("colocar")) { 
				
                                Ponta ponta = grafite.grafite; 
                                grafite = new Grafite(Integer.parseInt(ui[1]));
                                double tipo_ponta = Integer.parseInt(ui[2]);
				grafite.colocar_ponta(grafite.tamanho_ponta);
                                Ponta tipo = new Ponta(tipo_ponta);
				grafite.usar(tipo);
			}
                        else if(ui[0].equals("escrever")) {
				grafite.escrever(Integer.parseInt(ui[1]));
			}else if(ui[0].equals("trocar")) {
				grafite.trocar_ponta();
			}else {
				System.out.println("Comando invalido");
			}
		}
		scanner.close();
	
    }
    
}


