package agencia_bancaria;
import java.util.ArrayList;
import java.util.Scanner;

class Operacao{
    
    private int indice;
    private String descricao;
    private float valor;
    private float saldo;
    
    public Operacao(int indice, String descricao, float valor, float saldo){
        
        this.indice = indice;
        this.descricao = descricao;
        this.valor = valor;
        this.saldo = saldo;
    }

    public int getIndice() {
        return indice;
    }
    
    public void setIndice(int indice){
        this.indice = indice;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public float getSaldo() {
        return saldo;
    }
    
    
    @Override
    public String toString(){
        return indice + ":  " + descricao + ":  " + valor + ":  " + saldo;
    }
    
}

 class Conta{
     
     private int nextId;
     private float saldo;
     private int numero;
     ArrayList<Operacao> extrato;
     
     public Conta(int numero){
        extrato = new ArrayList();
        this.numero = numero;
        this.saldo = 0;
        this.nextId = 1;
    }

    public int getNumero() {
        return numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
    public void salvarOperacao(String descricao, float valor, float saldo){
        
        extrato.add(new Operacao(this.nextId, descricao, valor,saldo));
        this.nextId += 1;
    }
    
    public void saque(String nome,float valor){
        
        if(this.saldo > 0 && valor <= this.saldo){
            this.saldo -= valor;
            salvarOperacao(nome, -valor, this.saldo);
        }else if(valor > this.saldo)
            System.out.println("fail: saldo eh insuficiente");
        else
            System.out.println("fail: invalid command");
    }
    
    public boolean tarifa(String nome, float valor){
        
        this.saldo -= valor;
        salvarOperacao(nome, -valor, this.saldo);
        return true;
    }
    
    public boolean deposito(String nome,float saldo){
        if(saldo > 0){
            this.saldo += saldo;
            salvarOperacao(nome, saldo, this.saldo);
            return true;
        }else
            System.out.println("fail: invalid command");
        return false;
    }
    
    public void show(){
        
        System.out.println("conta:" + this.getNumero() + " saldo: " + this.getSaldo());
    }
    
    
    @Override
    public String toString(){
        
        int aux = 0;
        String out = "";
        out +=  aux + ":  abertura:   0.0:    0.0\n";        
        for(Operacao operacao: extrato){
            out += operacao + "\n";
             
        }
        aux ++;
        return out;
    }
    
     public void extratoN(int n){
         
        int tamanho = extrato.size()- n;
        for(int i = tamanho; i <= extrato.size()-1;i++){
            if(extrato != null){
                int indexN = extrato.get(i).getIndice();
                String descricaoN = extrato.get(i).getDescricao();
                float valorN = extrato.get(i).getValor();
                float saldoN = extrato.get(i).getSaldo();
                System.out.println(indexN + ":  " + descricaoN + ":   " + valorN + ":  " + saldoN);
            }
        }
     }
     
    public void extornar(String nome, int indice){
        
        int tam = extrato.size()-1;
        int indiceN = indice - 1;
        if(indiceN <= tam){
            if(extrato.get(indiceN).getDescricao().equals("tarifa")){

                float novo_valor = -extrato.get(indiceN).getValor();
                 this.saldo += novo_valor;
                salvarOperacao(nome, novo_valor, this.saldo);
            }
            else
             System.out.println("fail: indice " + indice + " nao e tarifa");
        }else
            System.out.println("fail: indice " + indice + " invalido"); 
    }
}

public class Agencia_Bancaria {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       Conta conta = new Conta(0);
       
       while(true){
           String line = scan.nextLine();
           System.out.println("$ " + line);
           String ui[] = line.split(" ");
           
           if(ui[0].equals("end")){
               break;
               
           }else if(ui[0].equals("init")){
               conta = new Conta(Integer.parseInt(ui[1]));
               
           }else if(ui[0].equals("deposito")){
               conta.deposito(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("extrato")){
               System.out.println(conta);
               
           }else if(ui[0].equals("show")){
               conta.show();
               
           }else if(ui[0].equals("saque")){
               conta.saque(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("tarifa")){
               conta.tarifa(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("extratoN")){
               conta.extratoN(Integer.parseInt(ui[1]));
               
           }else if(ui[0].equals("extornar")){
               for(int i = 1; i < ui.length;i++){
                    conta.extornar(ui[0], Integer.parseInt(ui[i]));
               }
            }else{ 
               System.out.println("fail: comando invalido");
           }
        }
    }
}
