package exercicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Calculo.Produto;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Produto> lista = new ArrayList<>();

		System.out.print("Insira o caminho do arquivo: ");
		String localArquivo = sc.nextLine(); // C:\Users\aliss\Dropbox\Programação

		File arquivo = new File(localArquivo);
		String localPasta = arquivo.getParent(); // mostra o caminho da pasta do arquivo.
		boolean sucesso = new File(localPasta + "\\out").mkdir();
		String destinoArquivo = localPasta + "\\out\\summary.csv";

		try (BufferedReader buffeLeitura = new BufferedReader(new FileReader(localArquivo))) {
			String itemCsv = buffeLeitura.readLine();

			while (itemCsv != null) {
				String[] pastas = itemCsv.split(",");
				String nome = pastas[0];
				Double valor = Double.parseDouble(pastas[1]);
				Integer quantidade = Integer.parseInt(pastas[2]);

				lista.add(new Produto(nome, valor, quantidade));

				itemCsv = buffeLeitura.readLine();

			}

			try (BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(destinoArquivo))) {

				for (Produto produtos : lista) {
					bufferEscrita.write(produtos.getNome() + "," + String.format("%.2f", produtos.total()));
					bufferEscrita.newLine();
				}
				System.out.println(destinoArquivo + " Criado");
			} catch (IOException e) {
				System.out.println("Erro ao gravar arquivo:" + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo:" + e.getMessage());
		}

		sc.close();
	}

}
