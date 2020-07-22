package parquinho;
import java.util.Scanner;
import java.util.ArrayList;


public class Parquinho {

   
    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        Kid crianca = new Kid();
        PulaPula toy = new PulaPula();
        
        
        
        ArrayList<Kid> kidwait = new ArrayList();
        ArrayList<Kid> kidplay = new ArrayList();
        
        while(true){
        
        String comando = ler.nextLine();
        System.out.println("$" + comando);
        String a[] = comando.split(" ");
        String opc = a [0];
        
                        if(opc.equals("fechar")){
                            
                            System.out.println("Seu Saldo Final foi: " + toy.getSaldo() + "reais");
				break;
                                
			}
                        if(opc.equals("show")){
				
                               
                                    
                                   for(int i = kidwait.size() - 1; i >= 0; --i)
					System.out.print(kidwait.get(i) + " ");

                                    
                                    System.out.print("=>");
                                    System.out.print("[ ");
                                    
                                    for (int j = 0; j < kidplay.size() ; j++) {
                                    System.out.println(kidplay.get(j) + " ");
                                    }
                                      System.out.print("]\n");
    }
				
			
                        else if(opc.equals("chegou")){
			
                              
                                   crianca.setnome(a[2]); 
                                   crianca.setidade((Integer.parseInt(a[1])));
                                 
                                   kidwait.add(crianca);
       
                       }
                        
                        else if(opc.equals("entrou")){
                            
                           
                            
                            int tamanho = kidplay.size();
                            
                            if(toy.getCapacidade() <= tamanho){
                                
                                System.out.println("Pula-Pula já chegou em capacidade maxima, tire ao menos uma kid para colocar outra");
                            
                            }
                            else{
                            kidplay.add(kidwait.get(0));
                            kidwait.remove(kidwait.get(0));
                            
                            
                            toy.setSaldo(toy.getSaldo() + 2);
                            
                            }
                           }
                        
                        else if(opc.equals("saiu")){
                           
                           int ins = kidwait.size();
                           
                           kidwait.add(ins, kidplay.get(0));
                           kidplay.remove(kidplay.get(0));
                           
                        }
                        
                        
                         else if(opc.equals("papai")){
                             
                             String Nome_Kid;
                             
                             for(int i = 0; i < kidwait.size(); i++){
                             
                                 if(kidwait.get(i).equals(a[1])){
                                     
                                     kidwait.remove(i);
                                 
                                 }
                                 else{
                                     System.out.println("A crianca nao esta na fila");
                                 }
                                 
                             }
                             
                             for(int j = 0; j < kidplay.size(); j++){
                                 
                                 if(kidplay.get(j).equals(a[1])){
                                     
                                     kidplay.remove(j);
                                 
                                 }else{
                                     
                                     System.out.println("A crianca nao esta no pula-pula");
                                    
                                 }
                             
                             }
                             
                         
                         }
                        
                         else{
                             System.out.println("opc invalida! , tente novamente.");
                         }
        
        }
        
        
    }
    
}
