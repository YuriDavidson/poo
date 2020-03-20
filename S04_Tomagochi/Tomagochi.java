import java.util.ArrayList;
import java.util.Scanner;

class Pet{
    
    int energyMax = 20, hungryMax = 15, cleanMax = 15;
    private int energy, hungry, clean;
    int diamonds;
    int age;
    boolean alive;
    
    public Pet(int energy, int hungry, int clean){
    
        this.setEnergy(energy);
        this.setHungry(hungry);
        this.setClean(clean);
    
    }
    
    public Integer getEnergy(){
        return this.energy;
    }
    
    public void setEnergy(int energy){
        this.energy = energy;
    }
    
    public Integer getHungry(){
        return this.hungry;
    }
    
    public void setHungry(int hungry){
        this.hungry = hungry;
    }
    
    public Integer getclean(){
        return this.clean;
    }
    
    public void setClean(int clean){
        this.clean = clean;
    }
}

public class Tomagochi {
    
    
    Pet pet;
    
    void init(Pet pet){
    
        if(this.pet == null){
        
            this.pet = pet;
        }else{
            System.out.println("Seu Pet já existe");
        }
        
    }
    
    void show(){
    
    if(this.pet == null){
        System.out.println("fail : Nenhum tomagochi vivo");
    } 
    else if(this.pet.getEnergy() <= 0 || this.pet.getHungry() <=0 || this.pet.getclean() <= 0){
    
        this.pet.alive = false;
        System.out.println("fail : Tomagochi morreu");
    }
    else{
        System.out.println("E:" + this.pet.getEnergy() + "/" + this.pet.energyMax + ", "
             + "S:" + this.pet.getHungry() + "/" + this.pet.hungryMax + ", "
             + "L:" + this.pet.getclean() + "/" + this.pet.cleanMax + ", "
             + "D:" + this.pet.diamonds + ", "
             + "I:"  + this.pet.age);
    }
   }
    

    void play(){
        
        if(this.pet == null){
            System.out.println("fail: Pet não foi criado");
        }
        else if(this.pet.getEnergy() <= 0){
            this.pet.alive = false;
            System.out.println("fail: Tomagochi morreu de fraqueza");
        }
        else if(this.pet.getHungry() <= 0){
            this.pet.alive = false;
            System.out.println("fail: Tomagochi morreu de fome");
        }
        else if(this.pet.getclean() <= 0){
            this.pet.alive = false;
            System.out.println("fail: Tomagochi morreu de seujeira");
        }
        else{
            this.pet.setEnergy(this.pet.getEnergy() - 2);
            this.pet.setHungry(this.pet.getHungry()- 1);
            this.pet.setClean(this.pet.getclean() - 3);
            
            this.pet.diamonds += 1;
            this.pet.age += 1;
            
        
        }
    }
    
    void eat(){
    
        if(this.pet == null){
            System.out.println("fail: Pet não foi criado");
        }
        else if(this.pet.getEnergy() <= 0 || this.pet.getHungry() <=0 || this.pet.getclean() <= 0){
            
            this.pet.alive = false;
            System.out.println("fail : Tomagochi morreu");
        
        }
        else{
        
            this.pet.setEnergy(this.pet.getEnergy() - 1);
            if(this.pet.getHungry() + 4 > this.pet.hungryMax){
                
                this.pet.setHungry(this.pet.hungryMax);
            }else{
                this.pet.setHungry(this.pet.getHungry() + 4);
            }
            this.pet.setClean(this.pet.getclean() - 2);
            
            this.pet.diamonds += 0;
            this.pet.age += 1;
        }
    
    }
   
    void clean(){
    
        if(this.pet == null){
            System.out.println("fail: Pet não foi criado");
        }
        else if(this.pet.getEnergy() <= 0 || this.pet.getHungry() <=0 || this.pet.getclean() <= 0){
            
            this.pet.alive = false;
            System.out.println("fail : Tomagochi morreu");
        
        }
        else{
        
                int valor = this.pet.cleanMax - this.pet.getclean();
                this.pet.setEnergy(this.pet.getEnergy()- 3);
                this.pet.setHungry(this.pet.getHungry()- 1);
                this.pet.setClean(this.pet.getclean() + valor);
                this.pet.age += 2;
            
        }
    
    }
    
    void sleep(){
    
        int energia = this.pet.energyMax - this.pet.getEnergy();
        
        if(this.pet == null){
            System.out.println("fail: Pet não foi criado");
        }
        else if(this.pet.getEnergy() <= 0 || this.pet.getHungry() <=0 || this.pet.getclean() <= 0){
            
            this.pet.alive = false;
            System.out.println("fail : Tomagochi morreu");
        
        }
        
        else{
            if(energia <= 5){
                System.out.println("fail: Tomagochi não está com sono");
            }
            else if(energia > 5){
                int valor = this.pet.energyMax - this.pet.getEnergy();
                this.pet.setEnergy(this.pet.getEnergy()+valor);
                this.pet.age += valor;
            }
            
        }
    
    }
    
    public static void main(String[] args) {
        
     Scanner scanner = new Scanner(System.in);
     Tomagochi jogo  = new Tomagochi();
     
     while(true){
        
            String linha = scanner.nextLine();
            System.out.println("$" + linha);
            String ui[] = linha.split(" ");
            String cmd = ui[0];
            
                        if(ui[0].equals("end")){
				break;
			}else if( ui[0].equals("show")){
                            jogo.show();
                        }else if(ui[0].equals("init")){
                            
                            Pet pet = new Pet(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]),Integer.parseInt(ui[3]));
                            jogo.init(pet);
                        
                        }else if(linha.equals("play")){
                            jogo.play();
                        }
                        else if(linha.equals("eat")){
                            jogo.eat();
                        }else if(linha.equals("clean")){
                            jogo.clean();
                        }else if(linha.equals("sleep")){
                            jogo.sleep();
                        }else{
                            System.out.println("fail: operacao invalida");
                        }
                        
                            

    }
    }  
    
}
