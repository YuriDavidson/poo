package twitter;
import java.util.*;

/**
 *
 * @author Yuri Davidson
 */

class Perfil{
    String id;
    Map<String, Perfil> seguidos;
    Map<String, Perfil> seguidores;
    //ArrayList<Perfil> seguidores;
    //ArrayList<Perfil> seguidos;
    
    public Perfil(String id){
        
        this.id = id;
       seguidores = new TreeMap<String, Perfil>();
        seguidos = new TreeMap<String, Perfil>();
        //seguidores = new ArrayList<Perfil>();
        //seguidos = new ArrayList<Perfil>();
    }
     
    public void follow(Perfil other){
       if(seguidos.containsKey(other.id))
            return;
       seguidos.put(other.id, this);
        other.seguidores.put(this.id, this);
;
    }
    
     public void deixar_de_seguir(Perfil other){
         
         if(!seguidos.containsKey(other.id))
            return;
         seguidos.remove(other.id);
         other.seguidores.remove(this.id);
    }
     
    public void twittar(){ 
    }
    
    
    public String toString(){
        String saida = id + "\n";
        saida += "<- [ ";
        for(Perfil user : seguidos.values())
            saida += user.id + " ";
        saida += "]\n-> [ ";
        for(Perfil user : seguidores.values())
            saida += user.id + " ";
        saida += "]";
        return saida;
    }
    
}

class PerfilManeger{

    Map<String, Perfil> perfis;
    
    public PerfilManeger(){
        perfis = new TreeMap<String, Perfil>();
    }
    
    public void addUsuario(String id){
        
        Perfil conta_perfil = perfis.get(id);
        
        if(conta_perfil == null){
            
            perfis.put(id, new Perfil(id));
        }
        
    }
    
    public void seguir(String idOne, String idTwo){
        Perfil one = perfis.get(idOne);
        Perfil two = perfis.get(idTwo);
        
        if(one != null && two != null)
            one.follow(two);
    }
    
    public void unfolow(String idOne, String idTwo){
        Perfil one = perfis.get(idOne);
        Perfil two = perfis.get(idTwo);
         if(one != null && two != null)
            one.deixar_de_seguir(two);
        
    }
    
    public Perfil getUser(String user) {
		return perfis.get(user);
    }
    
     public String toString(){
        String saida = "";
        for(Perfil user : perfis.values())
            saida += user + "\n";
        return saida;
    }
    
}

public class Twitter {

    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scanner = new Scanner(System.in);
        PerfilManeger AppTwitter = new PerfilManeger();
        while(true){
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }
            else if(ui[0].equals("addUser")){
                AppTwitter.addUsuario(ui[1]);
               
            }
            else if(ui[0].equals("seguir")){
                AppTwitter.seguir(ui[1], ui[2]);
            }
            else if(ui[0].equals("unfollow")){
                AppTwitter.unfolow(ui[1], ui[2]);
            }
            else if(ui[0].equals("show")){
                System.out.println(AppTwitter);
            }
            else if(ui[0].contentEquals("twittar")) {
                
				String mensagem = "";
				for(int i = 2; i < ui.length; i++)
					mensagem += ui[i] + " ";
				AppTwitter.getUser(ui[1]);//twittar(message);
            }
            else{
                System.out.println("fail: comand invalid");
            }
            
        }    
    }
    
}
