import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {

    Menu menu = new Menu();

    int opcao = 0;
    Scanner scanner = new Scanner(System.in);

    do {

      System.out.print("\033[H\033[2J");
      System.out.flush();

      // Menu CRUD
      System.out.println("Menu:");  
      System.out.println("Criar [1]"); 
      System.out.println("Escrever [2]"); 
      System.out.println("Atualizar [3]"); 
      System.out.println("Deletar [4]");
      System.out.println("Sair [5]\n");
      
      System.out.print("Digite a opção que deseja: ");
      opcao = scanner.nextInt();

      // Verificacao Criar[1]
      if (opcao == 1) {        
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("Digite o nome do arquivo que deseja criar: ");
        String nomeArquivo = scanner.nextLine();
        menu.Criar(nomeArquivo);

        System.out.println("\nMenu Criar:");
        System.out.println("Voltar ao começo [0]");
        System.out.println("Sair [5]");
        System.out.print("\nDigite a opção que deseja: ");
        opcao = scanner.nextInt();
      }

      // Verificacao Criar[2]
      if (opcao == 2) {        
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        menu.Escrever();

        System.out.println("\nMenu Criar:");
        System.out.println("Voltar ao começo [0]");
        System.out.println("Sair [5]");
        System.out.print("\nDigite a opção que deseja: ");
        opcao = scanner.nextInt();
      }

      // Verificacao Criar[3]
      if (opcao == 3) {        
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        menu.Atualizar();

        System.out.println("\nMenu Criar:");
        System.out.println("Voltar ao começo [0]");
        System.out.println("Sair [5]");
        System.out.print("\nDigite a opção que deseja: ");
        opcao = scanner.nextInt();
      }

      // Verificacao Criar[4]
      if (opcao == 4) {
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        menu.Deletar();

        System.out.println("\nMenu Criar:");
        System.out.println("Voltar ao começo [0]");
        System.out.println("Sair [5]");
        System.out.print("\nDigite a opção que deseja: ");
        opcao = scanner.nextInt();

      }
      
      // scanner.close();
    } while (opcao < 1 || opcao > 5);
  }

  // Metodo Criar() para criar um novo arquivo.
  public void Criar(String nomeArquivo) {
    try {
      File arquivo = new File(nomeArquivo);
      
      if(arquivo.createNewFile()) {
        System.out.println("\nArquivo criado com sucesso: " + nomeArquivo);
      } else {
        System.out.println("\nArquivo já existe.");
      }

    } catch (IOException e) {
      System.out.println("Erro ao criar arquivo: " + e.getMessage());
    }
  }

  // Metodo Escrever() para escrever uma palavra em um arquivo.
  public void Escrever() throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Qual arquivo que deseja escrever a palavra: ");
    String url = scanner.nextLine();
    File file = new File(url);

    if(file.exists()) {
      try {
        FileWriter writer = new FileWriter(file, true);
    
        System.out.print("Qual palavra deseja escrever: ");
        String palavra = scanner.nextLine();

        writer.write(palavra + "\n");
        writer.close();
        System.out.println("Palavra escrita com sucesso!");

      } catch (IOException e) {
        System.out.println("Erro ao criar arquivo: " + e.getMessage());
      }
    } else {
      System.out.println("Arquivo nao encontrado.");
    }
  }

  // Metodo Atualizar() para atualizar uma palavra dentro de um arquivo.
  public void Atualizar() throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Qual arquivo que deseja escrever a palavra: ");
    String url = scanner.nextLine();
    Path path = Paths.get(url);

    if (Files.exists(path)) {
      System.out.print("Qual palavra deseja alterar: ");
      String palavraAntiga = scanner.nextLine();

      System.out.print("Palavra nova: ");
      String palavraNova = scanner.nextLine();

      List<String> linhas = Files.readAllLines(path);
      for (int i = 0; i < linhas.size(); i++) {
        String linha = linhas.get(i);
        if (linha.contains(palavraAntiga)) {
          linha = linha.replace(palavraAntiga, palavraNova);
          linhas.set(i, linha);
        }
      }

      Files.write(path, linhas);
      System.out.println("Palavra atualizada com sucesso!");
    }else {
      System.out.println("Arquivo nao encontrado.");
    }
  }

  // Metodo Deletar() para deletar uma palavra dentro de um arquivo.
  public void Deletar() throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Qual arquivo deseja modificar: ");
    String url = scanner.nextLine();
    Path path = Paths.get(url);

    if (Files.exists(path)) {
      System.out.print("Qual palavra deseja remover: ");
      String palavraDeletar = scanner.nextLine();

      List<String> linhas = Files.readAllLines(path);
      for (int i = 0; i < linhas.size(); i++) {
        String linha = linhas.get(i);
        if (linha.contains(palavraDeletar)) {
          linha = linha.replace(palavraDeletar, "");
          linhas.set(i, linha);
        }
      }

      Files.write(path, linhas);
      System.out.println("Palavra removida com sucesso!");
    } else {
      System.out.println("Arquivo nao encontrado.");
    }

  }
 
}