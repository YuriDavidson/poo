package twitter;
import java.util.*;

/**
 *
 * @author Yuri Davidson
 */

class Tweet{
    
        int id;
	String user;
	String message;
        
	public Tweet(int id, String user, String message) {
		this.id = id;
		this.user = user;
		this.message = message;
	}
}

class Perfil{
    String id;
    Map<String, Perfil> seguidos;
    Map<String, Perfil> seguidores;
    //ArrayList<Perfil> seguidores;
    //ArrayList<Perfil> seguidos;
    ArrayList<Tweet> timeline;
    
    public Perfil(String id){
        
       this.id = id;
       seguidores = new TreeMap<String, Perfil>();
       seguidos = new TreeMap<String, Perfil>();
       //seguidores = new ArrayList<Perfil>();
       //seguidos = new ArrayList<Perfil>();
       timeline = new ArrayList<>();
    }
    
    int nao_lidos = 0;

   
     
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
     
    void twittar(Tweet tweet){
        
        for(Perfil perfil : seguidores.values()){
            perfil.timeline.add(tweet);
            perfil.nao_lidos +=1;
        }
        
        
    }
    
    public String inbox() {
        
		String out = "";
                if(nao_lidos == 0)
                    out = "inbox vazia\n";
                
                
		for(int i = timeline.size() - nao_lidos; i < timeline.size(); i++){
			out += timeline.get(i) + "\n";
                }        
		nao_lidos = 0;
		return out;
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
    int nextId = 0;
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
    
    void twittar(String name, String message){
        Tweet tweet = new Tweet(nextId,name,message);
        nextId += 1;
        getUser(name).twittar(tweet);
    }
    
    public String getInbox(String name) {
		return name + "\n" + perfis.get(name).inbox();
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
        PerfilManeger AppTwitter = new PerfilManeger();
        Scanner scanner = new Scanner(System.in);
        
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
            else if(ui[0].equals("twittar")) {
                 String message = "";
                 
		 for(int i = 2; i < ui.length; i++)
                    message += ui[i] + " ";
		 //AppTwitter.getUser(ui[1]).twittar(message);
                 AppTwitter.twittar(ui[1], message);
                 
            }else if(ui[0].equals("timeline")){
		System.out.println(AppTwitter.getInbox(ui[1]));
            }
            else{
                System.out.println("fail: comand invalid");
            }
            
        }    
    }
    
}
