import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {

    private int id;
    private String nome;
    private LocalDate data;
    private String descricao;
    private List<Participante> participantes;

    public Evento(int id, String nome, LocalDate data, String descricao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    // Getters e Setters omitidos para brevidade

    public void adicionarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", participantes=" + participantes +
                '}';
    }
}

