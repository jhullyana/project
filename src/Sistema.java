import java.io.*;
import Console;

public class Sistema {

    private static void exibirMenu() {

        System.out.println("\nEvents System");
        System.out.println("1)Cadastrar evento");
        System.out.println("Buscar evento");
        System.out.println("3)Listar eventos");
        System.out.println("4)Atualizar dados");
        System.out.println("5)Excluir evento");
        System.out.println("0)Sair");
    }

    private static void cadastrarEvento() {
        System.out.println("Novo Evento: ");
        String novo = Console.lerString();
        String data = Console.lerString();
        String local = Console.lerString();
        String descricao = Console.lerString();
        String participantes = Console.lerString();

        Evento evento = new Evento(novo, data, local, descricao, participantes);
        
        GerenciadorEventos.salvarEvento(evento);

        try {

            EventoPersistencia.salvarNoArquivo();
            System.out.println("\nEvento foi salvo com sucesso!");

            
        } catch (Exception exception) {

            System.out.println(exception.getMessage());
        }
    }

    private static void buscarEvento(int modo) {

         /*
         * modo:
         * 3 = apagar
         * 2 = editar
         * 1 ou default = exibir
         */

        try {
            
            GerenciadorEventos.verificarListaVazia();

            String Evento = Console.lerString("Informe o nome do evento: ")
        } catch (Exception exception) {
        }


    }







}
