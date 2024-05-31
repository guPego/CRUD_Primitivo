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

    while (opcao != 9) {

      // Limpeza do console | Limpeza do buffer de saida
      menu.limparTela();

      // Menu CRUD
      System.out.println("Menu:");
      System.out.println("Criar [1]");
      System.out.println("Escrever [2]");
      System.out.println("Atualizar [3]");
      System.out.println("Deletar [4]");
      System.out.println("Listar [5]\n");
      System.out.println("Sair [9]\n");

      System.out.print("Digite a opção que deseja: ");
      opcao = scanner.nextInt();

      scanner.nextLine();
      menu.limparTela();

      switch (opcao) {
        // Caso queira Criar[1]
        case 1:
          menu.Criar(scanner);

          System.out.println("\nMenu Criar:");
          opcao = menu.menuStartOrEnd(scanner);
          break;

        // Caso queira Escrever[2] uma nova palavra
        case 2:
          menu.Escrever(scanner);

          System.out.println("\nMenu Escrever:");
          opcao = menu.menuStartOrEnd(scanner);
          break;

        // Caso queira Atualizar[3] uma palavra existente
        case 3:
          menu.Atualizar(scanner);

          System.out.println("\nMenu Atualizar:");
          opcao = menu.menuStartOrEnd(scanner);
          break;

        // Caso queira Atualizar[3] uma palavra existente
        case 4:
          menu.Deletar(scanner);
    
          System.out.println("\nMenu Deletar:");
          opcao = menu.menuStartOrEnd(scanner);
          break;
        
        // Caso queira Listar[5] as palavras de um arquivo
        case 5:
          menu.Listar(scanner);

          System.out.println("\nMenu Listar:");
          opcao = menu.menuStartOrEnd(scanner);
          break;

      }
    }
  }

  // Metodo Criar() para criar um novo arquivo.
  public void Criar(Scanner scanner) {
    try {
      System.out.print("Digite o nome do arquivo que deseja criar: ");
      String nomeArquivo = scanner.nextLine();

      File arquivo = new File(nomeArquivo);

      if (arquivo.createNewFile()) {
        System.out.println("\nArquivo criado com sucesso: " + nomeArquivo);
      } else {
        System.out.println("\nArquivo já existe.");
      }

    } catch (IOException e) {
      System.out.println("Erro ao criar arquivo: " + e.getMessage());
    }
  }

  // Metodo Escrever() para escrever uma palavra em um arquivo.
  public void Escrever(Scanner scanner) throws Exception {

    System.out.print("Qual arquivo que deseja escrever a palavra: ");
    String url = scanner.nextLine();
    File file = new File(url);

    if(!file.exists()) {
      System.out.println("Arquivo nao encontrado.");
    } else {
      try {
        FileWriter writer = new FileWriter(file, true);

        System.out.print("Qual palavra deseja escrever: ");
        String palavra = scanner.nextLine();

        writer.write(palavra + "\n");
        writer.close();

        limparTela();
        System.out.printf("Palavra '%s' escrita com sucesso!\n", palavra);

      } catch (IOException e) {
        System.out.println("Erro ao criar arquivo: " + e.getMessage());
      }
    }
  }

  // Metodo Atualizar() para atualizar uma palavra dentro de um arquivo.
  public void Atualizar(Scanner scanner) throws Exception {

    System.out.print("Qual arquivo que deseja escrever a palavra: ");
    String url = scanner.nextLine();
    Path path = Paths.get(url);

    if (!Files.exists(path)) {
      System.out.println("Arquivo nao encontrado.");
    } else {
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

      limparTela();
      System.out.printf("Palavra '%s' atualizada para '%s' com sucesso!\n", palavraAntiga, palavraNova);
    }
  }

  // Metodo Deletar() para deletar uma palavra dentro de um arquivo.
  public void Deletar(Scanner scanner) throws Exception {
    
    System.out.print("Qual arquivo deseja modificar: ");
    String url = scanner.nextLine();
    Path path = Paths.get(url);

    if (!Files.exists(path)) {
      System.out.println("Arquivo nao encontrado.");
    } else {
      System.out.print("Qual palavra deseja remover: ");
      String palavraDeletar = scanner.nextLine();

      List<String> linhas = Files.readAllLines(path);
      
      limparTela();
      if(linhas.contains(palavraDeletar)) {
        linhas.removeIf(linha -> linha.equals(palavraDeletar));
        System.out.printf("Palavra '%s' removida com sucesso!\n", palavraDeletar);      
      } else {
        System.out.printf("Palavra '%s' nao encontrada para remocao.\n", palavraDeletar);
      }

      Files.write(path, linhas);
    }
  }

  // Metodo Listar() para listar as palavras dentro de um arquivo.
  public void Listar(Scanner scanner) throws Exception {

    System.out.print("Qual arquivo deseja listar: ");
    String url = scanner.nextLine();
    Path path = Paths.get(url);

    if(!Files.exists(path)) {
      System.out.println("Arquivo nao encontrado.");
    } else {

      limparTela();
      System.out.printf("Lista '%s': \n", url);
      List<String> linhas = Files.readAllLines(path);
      for(int i = 0; i < linhas.size(); i++) {
        String linha = linhas.get(i);
        System.out.printf("\t%s\n" ,linha);
      }
    }
  }

  // Metodo limparTela() para limpar tanto o console quanto o buffer de saida.
  public void limparTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

  // Metodo menuStartOrEnd() para exibir o Menu 'Voltar ao começo [0]' ou 'Sair [9]'
  public int menuStartOrEnd(Scanner scanner) {
    System.out.println("Voltar ao começo [0]");
    System.out.println("Sair [9]");
    System.out.print("\nDigite a opção que deseja: ");
    return scanner.nextInt();
  }

}