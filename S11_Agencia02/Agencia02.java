package agencia02;
import java.util.*;
/**
 *
 * @author Yuri Davidson
 */
class Conta{
    
    int id;
    String id_cliente;
    String tipo_conta;
    public float saldo_conta;
   
    public Conta(int id ,String id_cliente){
        
        this.id = id;
        this.id_cliente = id_cliente;
        this.tipo_conta = "";
        this.saldo_conta = 0;
    }
    
    public float getSaldo(){
        return this.saldo_conta;
    }
    
//    public void setSaldo( float aux_saldo){
//         this.saldo_conta = aux_saldo;
//    }
    public void depositar(float valor){
        
        if(this.tipo_conta.equals("CC"))
            this.saldo_conta += valor;
        
        else
            this.saldo_conta += valor;
    }
    
      public void sacar(float valor){ 
          
        if(this.tipo_conta.equals("CC"))
            this.saldo_conta -= valor;
            
        else if(this.tipo_conta.equals("CP")){
            
            if(this.saldo_conta - valor >= 0)
                this.saldo_conta -= valor;
            
            else
               System.out.println("fail: saldo insuficiente");
        }
    }
 //    public void tranferir(int indice01, int indice02, float valor){
//    }
     public void transferir(Conta other, float valor){
         
        other.saldo_conta += valor;
    }  
            
    public void AtualizacaoMensal(){
        
        if(this.tipo_conta.equals("CC"))
            this.saldo_conta -= 20;
        
        else{
            float aux_saldo= (this.saldo_conta * 1) / 100;
            this.saldo_conta += aux_saldo;
        }
    }
    
    @Override
    public String toString(){
        return this.id + ":" + this.id_cliente + ":" + this.saldo_conta + "00:" + this.tipo_conta;
    }
}

class Cliente {
    
    private String id_cliente;
    private ArrayList<Conta> conta_banco;
    
    public Cliente(String id_cliente) {
        this.id_cliente = id_cliente;
        conta_banco = new ArrayList<>();
    }
 }

class ContaPoupanca extends Conta{
    
    float lucro_poupanca = 0;
    
    public ContaPoupanca(int id, String idCliente) {
        
        super(id, idCliente);
        this.tipo_conta = "CP";
    }
    
    public void AtualizacaoMensal(){
        lucro_poupanca = (this.getSaldo()*1) / 100;
        this.saldo_conta += lucro_poupanca;
    }
}

class ContaCorrente extends Conta{
    
    float Tarifa_Mensal = 20;
    
    public ContaCorrente(int id, String id_cliente) {
        
        super(id, id_cliente);
        this.tipo_conta = "CC";
    }
    
    public void AtualizacaoMensal(){
        
         this.saldo_conta -= Tarifa_Mensal;
    }
}

public class Agencia02 {

    TreeMap<Integer,Conta> conta_banco;
    TreeMap<String,Cliente> cliente_banco;
    int next_id;
    
    public Agencia02(){
        
        conta_banco = new TreeMap<>();
        cliente_banco = new TreeMap<>();
        this.next_id = 0;
    }
    
    public void AddCliente(String id){
        
        if(cliente_banco.get(id) == null){
            
            Conta poupanca = new ContaPoupanca(this.next_id +=1, id);
            Conta corrente = new ContaCorrente(this.next_id +=1, id);
            Cliente cliente = new Cliente(id);
            
            cliente_banco.put(id, cliente);
            conta_banco.put(poupanca.id, poupanca);
            conta_banco.put(corrente.id, corrente);
        }
    }
    
    public void Depositar(int indice, float valor){
        
        if(conta_banco.containsKey(indice)){
            Conta conta = conta_banco.get(indice);
            conta.depositar(valor);
        }
        else
           System.out.println("fail: indice invalido");
    }
    
    public void Sacar(int indice, float valor){
        
        if(conta_banco.containsKey(indice)){
            
            Conta conta = conta_banco.get(indice);
            conta.sacar(valor);
        }
        else
           System.out.println("fail: indice invalido");
    }
    
     public void Tranferencia(int indice1, int indice2, float valor){
         
        if(conta_banco.containsKey(indice1) && conta_banco.containsKey(indice2)){
            Conta conta01 = conta_banco.get(indice1);
            Conta conta02 = conta_banco.get(indice2);
            
            conta01.sacar(valor);
            conta02.transferir(conta02 , valor);
        }
        else{
            System.out.println("fail: conta nao encontrada");
        }
    }
    
    public void update(){
        
        for(Conta aux_conta : conta_banco.values()){
            
            if(aux_conta.tipo_conta.equals("CC")){
                ContaCorrente corrente = (ContaCorrente) conta_banco.get(aux_conta.id);
                corrente.AtualizacaoMensal();
            }
            else if(aux_conta.tipo_conta.equals("CP")){
                ContaPoupanca poupanca = (ContaPoupanca) conta_banco.get(aux_conta.id);
                poupanca.AtualizacaoMensal();
            }
        }
    }
    
    @Override
    public String toString(){
        
        String saida = "";
        
        for(Conta aux_conta : conta_banco.values())
            saida += aux_conta + "\n";
        
        return saida;
    }
  
    public static void main(String[] args) {
        // TODO code application logic here
        Agencia02 agencia02 = new Agencia02();
        Scanner scan = new Scanner(System.in);
        
        while(true){
            
            String line = scan.nextLine();
            System.out.println("$ " + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("addCli")){
                agencia02.AddCliente(ui[1]);
            }else if(ui[0].equals("show")){
                System.out.println(agencia02);
            }else if(ui[0].equals("deposito")){
                agencia02.Depositar(Integer.parseInt(ui[1]) ,Float.parseFloat(ui[2]));
            }else if(ui[0].equals("saque")){
                agencia02.Sacar(Integer.parseInt(ui[1]) ,Float.parseFloat(ui[2]));
            }else if(ui[0].equals("transf")){
                agencia02.Tranferencia(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]) ,Float.parseFloat(ui[3]));
            }else if(ui[0].equals("update")){
                agencia02.update();
            }else{
                System.out.println("fail: comando invalido");
            }
        }
    }
}