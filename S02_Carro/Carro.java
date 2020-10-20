/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carro;
import java.util.Scanner;

/**
 *
 * @author Yuri Davidson
 */

class Tanque_Carro{

	private float gasolina;
	private float gasMax;
	
	
	
	public Tanque_Carro(float gasMax) {
            
		this.gasolina = 0;
		this.gasMax = gasMax;
	}
	
	public void abastecer_carro(float gasolina) {
            
		if(this.gasolina + gasolina > gasMax) {
			this.gasolina = gasMax;
		}
		else this.gasolina += gasolina;
                }
	
        public boolean gastarGas(float km) {
		
		if(this.gasolina < (km/10)) {
			System.out.println("Fail: gasolina insulficiente");
			return false;
		}
		else {	
			this.gasolina -= (km/10);
			return true;
		}
	}
       
        public float getGasMax() {
		return gasMax;
	}

	public void setGasMax(float gasMax) {
		this.gasMax = gasMax;
	}
        
        public float getGasolina(){
            return gasolina;
        }
	
}

class Atributos_Carro {
	
	private int pessoas;
	private float quilometragem;
	private Tanque_Carro tanque;
	
	public Atributos_Carro(int pessoas) {

		this.pessoas = 0;
		this.quilometragem = 0;
		this.setTanque_Carro(new Tanque_Carro(10));
	}
		
	public int getPessoas() {
		return pessoas;
	}

	public float getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}

	public void embarcar() {
		if(this.pessoas <= 1) {
			this.pessoas ++;
			System.out.println("Pessoa embarcada com sucesso");
		}
		else {
				System.out.println("Fail: Limite de pessoas atingido");
		}
	}
	
	public void desembarcar() {
		if(this.pessoas > 0) {
			this.pessoas --;
			System.out.println("Pessoa desembarcada com sucesso");
		}
	}
		
	public void andar(float quilometragem, Tanque_Carro tanque) {
		boolean n = true;
		if(this.pessoas <= 0) {
			System.out.println("Fail: nao ha ninguem no carro");
		}
		else {
                    n = tanque.gastarGas(quilometragem);
		}
		if(n == true) {
			this.quilometragem += quilometragem;
		}
	}

	public Tanque_Carro getTanque() {
		return tanque;
	}
        
        public void setTanque_Carro(Tanque_Carro tanque) {
		this.tanque = tanque;
	}
}


public class Carro {
    
	// TODO code application logic here
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Tanque_Carro tanque = null;
		Atributos_Carro carro = new Atributos_Carro(0);
		
		
		while (true) { 
			String line = scan.nextLine();
			System.out.println("$" + line);
			if (line.equals("end"))
				break;
			String ui[] = line.split(" ");
			
			if (ui[0].equals("init")) {
				int value = Integer.parseInt(ui[1]); 
				tanque = new Tanque_Carro(value);
			}else if (ui[0].equals("show")) {
				System.out.println("pass: " + carro.getPessoas() + " gas: " + tanque.getGasolina() +"/"+ tanque.getGasMax() + " km: " + carro.getQuilometragem());
			}else if (ui[0].equals("in")) {
				carro.embarcar();
			}else if (ui[0].equals("out")) {
				carro.desembarcar();
			}else if (ui[0].equals("fuel")) {
				tanque.abastecer_carro(Float.parseFloat(ui[1]));
			}else if (ui[0].equals("drive")) {
				carro.andar(Float.parseFloat(ui[1]),tanque);
			}
		}
	}
}
