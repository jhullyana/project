import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaGerenciamentoEventos {

    private List<Evento> eventos;
    private static final String ARQUIVO_EVENTOS = "eventos.txt";

    public SistemaGerenciamentoEventos() {
        this.eventos = new ArrayList<>();
    }

    public void iniciar() {
        carregarEventos();
        // Exemplos de utilização do sistema
        listarEventos();
        adicionarNovoEvento();
        listarEventos();
        salvarEventos();
    }

    private void carregarEventos() {
        eventos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_EVENTOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                LocalDate data = LocalDate.parse(partes[2]);
                String descricao = partes[3];
                Evento evento = new Evento(id, nome, data, descricao);
                // Carregar participantes do evento
                String[] participantes = partes[4].split(",");
                for (String participanteStr : participantes) {
                    String[] dadosParticipante = participanteStr.split(":");
                    int idParticipante = Integer.parseInt(dadosParticipante[0]);
                    String nomeParticipante = dadosParticipante[1];
                    String emailParticipante = dadosParticipante[2];
                    String telefoneParticipante = dadosParticipante[3];
                    Participante participante = new Participante(idParticipante, nomeParticipante, emailParticipante, telefoneParticipante);
                    evento.adicionarParticipante(participante);
                }
                eventos.add(evento);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }

    private void salvarEventos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_EVENTOS))) {
            for (Evento evento : eventos) {
                writer.write(evento.getId() + ";" +
                        evento.getNome() + ";" +
                        evento.getData() + ";" +
                        evento.getDescricao() + ";");
                // Salvar participantes do evento
                StringBuilder participantesStr = new StringBuilder();
                for (Participante participante : evento.getParticipantes()) {
                    participantesStr.append(participante.getId()).append(":")
                            .append(participante.getNome()).append(":")
                            .append(participante.getEmail()).append(":")
                            .append(participante.getTelefone()).append(",");
                }
                if (participantesStr.length() > 0) {
                    participantesStr.deleteCharAt(participantesStr.length() - 1); // Remove a última vírgula
                }
                writer.write(participantesStr.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    private void listarEventos() {
        System.out.println("Eventos cadastrados:");
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
        System.out.println("Total de eventos: " + eventos.size());
        System.out.println();
    }

    private void adicionarNovoEvento() {
        // Exemplo: Criar um novo evento
        Evento evento = new Evento(eventos.size() + 1, "Conferência de Tecnologia", LocalDate.of(2024, 7, 1), "Evento sobre as últimas tendências em tecnologia");
        evento.adicionarParticipante(new Participante(1, "João Silva", "joao.silva@example.com", "(11) 99999-9999"));
        eventos.add(evento);
    }

    public static void main(String[] args) {
        SistemaGerenciamentoEventos sistema = new SistemaGerenciamentoEventos();
        sistema.iniciar();
    }
}
