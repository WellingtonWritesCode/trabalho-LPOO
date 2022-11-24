/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pesquisa;

import Av1C2.CadConta;
import Av1C2.Conta;
import Av1C2.ContaEspecial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jadso
 */
public class Arquivo {

	public static CadConta leContas(String arquivo) throws FileNotFoundException {
		CadConta contas = new CadConta();
		Scanner entrada = new Scanner(new File(arquivo));
		while(entrada.hasNext()) {
			contas.insere(parseLine(entrada.nextLine()));
		}
		entrada.close();
		return contas;
	}
	
	   private static Conta parseLine(String line) {
		Conta novaConta;
		String[] valores = line.split(";");
		String numero = valores[0];
		String nome = valores[1];
		String cpf = valores[2];
		double valor_em_conta = Double.parseDouble(valores[3]);
		if(valores.length == 5) {
			double limite = Double.parseDouble(valores[4]);
			novaConta = new ContaEspecial(numero, nome, cpf, limite);
		} else {
			novaConta = new Conta(numero, nome, cpf);
		}
		novaConta.deposito(valor_em_conta);
		return novaConta;
	}
           
           public static String formataPesquisa(String nome, List<Conta> pesquisa) {
		String out = "NOME " + nome + ":";
		if (pesquisa == null) {
			out += "\nNAO HA NENHUMA CONTA COM O NOME " + nome;
		} else {
			for (Conta c : pesquisa) {
				out += "\nConta " + c.getNumero() + "\tSaldo R$" + c.saldo();
			}
		}
		return out;
	}

}
