package br.edu.impacta.arq4;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaudacaoServer {
	public static void main (String[] args) throws IOException {
		ServerSocket ss= new ServerSocket(58888);
	while(true){
		System.out.println("servidor esperando conexão...");
		Socket soc = ss.accept();
		System.out.println("conectado!");
		InputStream is = soc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		OutputStream os= soc.getOutputStream();
		PrintWriter pw= new PrintWriter(os);
		String nome = br.readLine();
		Date data = new Date();
		SimpleDateFormat fmtData = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat fmtHora = new SimpleDateFormat("hh:mm:ss");
		String msg = "Ola" + nome + "!";
		msg +="O relogio deste servidor indica que hoje é " + fmtData.format(data);
		msg += "e o horario e "+ fmtHora.format(data);
		pw.println(msg);
		pw.flush();
		pw.close();
		br.close();
		soc.close();
		
	}
	
	}
	
	
}
