package junkfood;
import java.util.ArrayList;
import java.util.Scanner;


class Product{

    private int index;
    private String name;
    private int unity;
    private float price;
    
    public Product(int index, String name,int unity , float price){
        
        this.index = index;
        this.name = name;
        this.unity = unity;
        this.price = price;
     }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnity() {
        return unity;
    }

    public void setUnity(int unity) {
        this.unity = unity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString(){
        return name + ":" + unity + ":" + price + "R$";
    }

}

class Machine{

    private int spirals;
    private int depth;
    private float machine_balance;
    
    private ArrayList <Product> products_machine;

        public Machine(int soirals , int depth){
            
            products_machine = new ArrayList();
            this.spirals = soirals;
            this.depth = depth;
            
            for(int i = 0; i < this.spirals; i++){
                
                products_machine.add(null);
            
            }
            
        }

    public int getSpirals() {
        return spirals;
    }

    public void setSpirals(int spirals) {
        this.spirals = spirals;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public float getMachine_balance() {
        return machine_balance;
    }

    public void setMachine_balance(float machine_balance) {
        this.machine_balance = machine_balance;
    }

    public ArrayList<Product> getProducts_machine() {
        return products_machine;
    }

    public void setProducts_machine(ArrayList<Product> products_machine) {
        this.products_machine = products_machine;
    }

        
     public void money(float money){
     
         setMachine_balance(getMachine_balance() + money);
     }
     
    public boolean set(int index , String name, int capacity, float price){
    
        if(index < 0 || index >= getSpirals()){
            System.out.println("fail: indice inexistente");
            return false;
        }
        if(products_machine.get(index) != null){
            System.out.println("fail: limite excedido");
            return false;
        }
        else{
        products_machine.set(index,new Product(index, name, capacity, price));
        return true;
        }
    }
    
    public void clear(int index){
    
        if(products_machine.get(index)!= null){
            products_machine.set(index, null);
        }
        else if(index > getSpirals()-1){
            System.out.println("Indice inexistente");
        }
        else{
            System.out.println("Não existe produto nessa posição");
        }
    }
    public void buy(int index){
        
        int size = getSpirals()-1;
        
        for(Product p: products_machine){
            if(p != null){
                if(p.getIndex() == index){
                    
                    if(this.machine_balance > p.getPrice() &&  p.getUnity() > 0){
                        System.out.println("Voce Comprou " + p.getName());
                        setMachine_balance(this.getMachine_balance() - p.getPrice());
                        p.setUnity(p.getUnity()-1);
                        
                        break;
                    }
                    else if(this.machine_balance > 0 && p.getUnity() == 0){
                        
                        System.out.println("fail: Espiral Vazia...");
                        
                        break;
                    }
                    else if(this.machine_balance < p.getPrice() && (this.machine_balance > 0 || this.machine_balance == 0) && p.getUnity() > 0){
                        
                        System.out.println("fail: Saldo Insuficiente");
                        
                        break;
                    }
                    
                }
                else if(index > size){
                    
                        System.out.println("fail: Indice Inexistente");
                        
                        break;
               }
            }
          }
      }
    
     public void troco(){
         
        System.out.println("Seu Troco: " + getMachine_balance()+ " R$");
        setMachine_balance(0);
    }
    
    public String toString(){
        
        int indice = 0;
        
        String out = "Saldo: " +getMachine_balance() + "0\n";
        
        for(Product products_machine : products_machine){
            
            if(products_machine == null && indice< getSpirals()){
            
                out += indice + " [ empty : 0 U : 0.00 RS ]\n";
            }else if(products_machine != null && indice < getSpirals()){
                    
                out += indice + " [ " +products_machine+ "]\n";
            }
            indice++;
        }
        return out;
    } 
}


public class Junkfood {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Machine maquine = new Machine(0,0);
        
        while(true){
            
            String line = scan.nextLine();
            System.out.println("$" + line);
            String input[] = line.split(" ");
            
            if(input[0].equals("end")){
                break;
            }else if(input[0].equals("show")){
                System.out.println(maquine);
                
            }else if(input[0].equals("init")){
                maquine = new Machine(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                
            }else if(input[0].equals("set")){
                maquine.set(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), Float.parseFloat(input[4]));
                
            }else if(input[0].equals("limpar")){
                maquine.clear(Integer.parseInt(input[1]));
                
            }else if(input[0].equals("dinheiro")){
                maquine.money(Float.parseFloat(input[1]));
                
            }else if(input[0].equals("comprar")){
                maquine.buy(Integer.parseInt(input[1]));
            }else if(input[0].equals("troco")){
                maquine.troco();
            }else{
                System.out.println("Fail: comando inválido, tente novamente");
            }
        }
    }
 }
