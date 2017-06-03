package br.edu.impacta.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class AgendaApp {
	
	private static Scanner entrada = new Scanner(System.in);
	//private static List<Contato> contatos = new ArrayList<>();
	private static iContatoDao dao = new ContatoDao();
	
	public static void main(String[] args) {
		boolean sair = false;
		while(!sair){
			int opcao = apresentarMenuPrincipal();
			switch (opcao) {
				case 1: inserirContato(); break;
				case 2: buscarContato(); break;
				case 3: sair = true; break;
				default: out.println("Erro: opção invalida");
			}
		}
		out.println("\n Fim do programa");
	}
	
	private static void buscarContato() {
		out.println("\n BUSCA DE CONTATOS");
		String nome = lerNome();
		
			//List<Contato> resultado = new ArrayList<>();
			//for (Contato c : contatos) {
			//	if(nome.equals(c.getNome())){
			//		resultado.add(c);
			//	}
			//}
		
			List<Contato> resultado = dao.buscar(nome);
			
			if(resultado.size() == 0){
				out.println("Não ha nenhum resultado com esse nome");
			}else{
				out.println("\n Resultado da busca: ");
				for (Contato c : resultado) {
					out.println(c);
				}
			}
	}
	
	private static void inserirContato() {
		out.println("\n INSERÇÃO DE NOVO CONTATO");
		String nome = lerNome();
		String telefone = lerTelefone();
		Contato c = new Contato(nome, telefone);
		
		//if(contatos.contains(c)){
		if(dao.existe(c)){
			out.println("Este contato ja foi cadastrado");
		}else{
			//contatos.add(c);
			dao.inserir(c);
			out.println("Contato inserido");
		}
	}
	
	private static String lerTelefone() {
		boolean valido = false;
		String telefone = "";
		while(!valido){
			out.println("Telefone: ");
			telefone = entrada.nextLine();
			if(telefone.length() == 0 || telefone.length() > 25){
				out.println("Erro: telefone de tamanho invalido");
			}else{
				valido = true;
			}
		}
		return telefone;
	}
	private static String lerNome() {
		boolean valido = false;
		String nome = "";
		while(!valido){
			out.println("Nome: ");
			nome = entrada.nextLine();
			if(nome.length() == 0 || nome.length() > 200){
				out.println("Erro: nome de tamanho invalido");
			}else{
				valido = true;
			}
		}
		return nome;
	}
	private static int apresentarMenuPrincipal() {
		boolean inteiro = false;
		int opcao  = 0;
		while(!inteiro){
			out.println("\n Agenda Telefonica");
			out.println("(1) Inserir");
			out.println("(2) buscar");
			out.println("(3) Sari");
			out.println("Escolha uma opcao");
			String s = entrada.nextLine();
			try {
				opcao = Integer.parseInt(s);
				inteiro = true;
			} catch (Exception e) {
				out.println("Erro: opção deve ser um valor numerico");
			}
		}
		return opcao;
	}
}
