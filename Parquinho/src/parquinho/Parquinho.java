package parquinho;
import java.util.Scanner;
import java.util.ArrayList;


public class Parquinho {

   
    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        Kid crianca = new Kid();
        PulaPula toy = new PulaPula();
        
        //crianca.nome = ler.nextLine();
        
        ArrayList<String> kidwait = new ArrayList();
        ArrayList<String> kidplay = new ArrayList();
        
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
				
                                    //crianca.status();
                                    
                                   for(int i = kidwait.size() - 1; i >= 0; --i)
					System.out.print(kidwait.get(i) + " ");

                                    
                                    System.out.print("=>");
                                    System.out.print("[ ");
                                    
                                    for (int j = 0; j < kidplay.size() ; j++) {
                                    System.out.printf(kidplay.get(j) + " ");
                                    }
                                      System.out.print("]\n");
    }
				
			
                        else if(opc.equals("chegou")){
			
                                //vetor.add(Integer.parseInt(a[i]));
                                //vetor.add(crianca.setnome(a[1]));
                                   crianca.setnome(a[1]); 
                                   crianca.setidade((Integer.parseInt(a[2])));
                                   
                                   kidwait.add(crianca.getnome() + crianca.getidade());
                                    
                                    
			}
                        
                        else if(opc.equals("entrou")){
                            
                            //pulapula.add(crianca.getnome() + crianca.getidade());
                            
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
        
        }
        
        
    }
    
}
