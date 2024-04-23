/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_aula_02_cliente;


import financeiro.contaCorrente;
import financeiro.contaCorrenteHelper;
import org.omg.CORBA.ORB;

public class SD_Aula_02_Cliente {

    public static void main(String[] args) {
        try {
			// Criando o Objeto ORB
			ORB meuOrb = ORB.init(args,null);
			// Registrando-se no servidor ORB
			org.omg.CORBA.Object objRef = meuOrb.string_to_object("corbaname::localhost:9876#contaCorrente");
			contaCorrente contaCorrenteRef = contaCorrenteHelper.narrow(objRef);
				
			System.out.println(contaCorrenteRef.toString());
				
			// Realizando o RPC - Chamada de Procedimento Remoto
			if(Integer.parseInt(args[0]) > 0) {
				if(contaCorrenteRef.credita(Integer.parseInt(args[0])) == false) {
					System.out.println("Opera��o de Cr�dito mal sucedida");
				}
			} else {
				if(Integer.parseInt(args[0]) < 0) {
					if(contaCorrenteRef.debita(Integer.parseInt(args[0])) == false) {
						System.out.println("Opera��o de D�bito mal sucedida");
					}                    
				} else {
					System.out.println("Informe uma opera��o v�lida (> 0 ou < 0)");
				}   
			}
			System.out.println(contaCorrenteRef.getSituacao());
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
    }
}
