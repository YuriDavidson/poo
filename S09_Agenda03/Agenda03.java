/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda03;

/**
 *
 * @author Yuri Davidson
 */
import java.util.*;

class Fone{
    private String id;
    private String number;
    
    public Fone(String id, String number){
        this.id = id;
        this.number = number;
    }
    
    public String toString(){
        return id + ":" + number;
    }
    
    public boolean validar(String number){
        String validos = "0123456789().-";
        for(int i = 0; i < number.length();i++){
            char c = number.charAt(i);
            if(validos.indexOf(c) == -1){
                return false;
            }
        }
        return true;
    }
}

class Contato{
    
    private String name;
    private ArrayList<Fone> phone;
    public boolean favoritado;
    
    public Contato(String name){
        this.name = name;
        phone = new ArrayList<>();
        favoritado = false;
    }
    
    public boolean addFone(String id, String number){
        Fone fone = new Fone(id,number);
        if(fone.validar(number)){
            phone.add(new Fone(id,number));
            return true;
        }
        else{
            System.out.println("fail: fone invalido!");
        }
        return false;
    }
    
    public void rmFone(int indice){
        for(int i = 0; i < phone.size(); i++){
            Fone fone = phone.get(i);
            if(i == indice){
                phone.remove(phone.get(i));
            }
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setFavoritado(boolean favorito){
        this.favoritado = favorito;
    }
    
    public boolean isFavoritado(){
        return this.favoritado;
    }
    
    public int comparaContato(Contato um, Contato dois){
        return um.name.compareTo(dois.name);
    }
    
    @Override
    public String toString(){
        
       String out =  "";
       
        if(isFavoritado())
            out += "@ " + this.name;
        
        else
            out += "- " + this.name;
        
        if(phone.size() > 0)
            out += " ";
        for(int index = 0; index < phone.size(); index++){
            Fone fone = phone.get(index);
            out += "[" + index + ":"+ fone + "]";
        }
        return out;
    }
    
}

public class Agenda03 {
    ArrayList<Contato> contato;
    ArrayList<Contato> favoritos;
    
    public Agenda03(){
        contato = new ArrayList<>();
        favoritos = new ArrayList<>();
    }
    
    Contato getContato(String name){
        for(Contato cont: contato){
            if(cont.getName().equals(name)){
                return cont;
            }
        }
        return null;
    }
    public boolean initContato(String nome){
        
        Contato cont = getContato(nome);
        if(cont != null) return false;
        this.contato.add(new Contato(nome));
        return true;
    }
    
    public void addContato(String name, String id, String number){
        Contato contact = getContato(name);
        
        if(contact == null){
            contact = new Contato(name);
            contact.addFone(id, number);
        }
        else if(contact != null){
            contact.addFone(id, number);    
        }
        
        Collections.sort(contato,(Contato one, Contato two) -> one.getName().compareTo(two.getName()));
    }
   
    public void search(String search){
        
        ArrayList<Contato> aux_search = new ArrayList<>();
        for(Contato contact : contato){
            if(contact.toString().indexOf(search) != -1){
                aux_search.add(contact);
            }    
        }
        for(int aux = 0; aux < aux_search.size(); aux++)
            System.out.println(aux_search.get(aux));
    }
    
     public boolean rmContato(String name){
        
        Contato contact = getContato(name);
        if(contact == null){
            return false;
        }
        desfavoritar_contato(name);
        contato.remove(contact);
        return true;
    }
    
    public void rmFoneindice(String name, int index){
        Contato contato = getContato(name);
        
        if(contato != null)
            contato.rmFone(index);
    }
    
    public ArrayList<Contato> getContatos(){
        return contato;
    }
    
    public void show(){
        for(int i = 0; i < contato.size(); i++){
            System.out.println(contato.get(i));
        }
    }
    
     public void favoritar_contato (String id){
         
        Contato contact = getContato(id);
        
        if(contact == null){
            return;   
        }
        if(contact.isFavoritado()){
            return;
        }    
        contact.setFavoritado(true);
        favoritos.add(contact);
    }
    
    
    public boolean desfavoritar_contato  (String name){
         
        Contato contact = getContato(name);
        if(contact == null){
            return false;
        }
        favoritos.remove(contact);
        return true;

    }
    
    public void favoritados(){
        
        for(int i = 0; i < favoritos.size(); i++){
            System.out.println(favoritos.get(i));
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Agenda03 agenda = new Agenda03();
        
        while(true){
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("add")){ 
                agenda.initContato(ui[1]);
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(":");
                    agenda.addContato(ui[1], partes[0], partes[1]);
                }
            }else if(ui[0].equals("agenda")){ 
                agenda.show();
            }else if(ui[0].equals("rmContato")){ 
                agenda.rmContato(ui[1]);
            }else if(ui[0].equals("search")){ 
                agenda.search(ui[1]);
            }else if(ui[0].equals("rmFone")){ 
                agenda.rmFoneindice(ui[1], Integer.parseInt(ui[2]));
            }else if(ui[0].equals("show")){
                agenda.show();
            }else if(ui[0].equals("fav")){ 
                agenda.favoritar_contato(ui[1]);
            }else if(ui[0].equals("favorited")){ 
                agenda.favoritados();
            }else if(ui[0].equals("unfav")){ 
                agenda.desfavoritar_contato(ui[1]);
            }
            else
              System.out.println("fail: comando inválido!");
        }
    }
}
