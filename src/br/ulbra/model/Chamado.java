package br.ulbra.model;

import java.sql.Timestamp;

/*
chamado_tecnico (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
solicitante VARCHAR(100) NOT NULL,
sala VARCHAR(50),
equipamento_tag VARCHAR(50),
problema_relatado TEXT NOT NULL,
diagnostico_tecnico TEXT,
prioridade VARCHAR(20) DEFAULT &#39;MÉDIA&#39;,
status VARCHAR(20) DEFAULT &#39;PENDENTE&#39;,
data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 */
public class Chamado {

    private int id;

    // Substituindo as Strings pelos IDs e Objetos [Relacionamento]
    private int idUsuario;
    private Usuario usuario; // Opcional: útil para exibir o nome do solicitante
    private int idEquipamento;
    private Equipamento equipamento; // Opcional: útil para exibir a sala e a tag
    private String problemaRelatado;
    private String diagnosticoTecnico;
    private String prioridade;
    private String status;
    private String dataAbertura;

    public Chamado() {
    }

    // Construtor atualizado para a nova estrutura do banco
    public Chamado(int id, int idUsuario, int idEquipamento, String problemaRelatado,
            String diagnosticoTecnico, String prioridade, String status, String dataAbertura) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEquipamento = idEquipamento;
        this.problemaRelatado = problemaRelatado;
        this.diagnosticoTecnico = diagnosticoTecnico;
        this.prioridade = prioridade;
        this.status = status;
        this.dataAbertura = dataAbertura;
    }

    // Getters e Setters Atualizados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getProblemaRelatado() {
        return problemaRelatado;
    }

    public void setProblemaRelatado(String problemaRelatado) {
        this.problemaRelatado = problemaRelatado;
    }

    public String getDiagnosticoTecnico() {
        return diagnosticoTecnico;
    }

    public void setDiagnosticoTecnico(String diagnosticoTecnico) {
        this.diagnosticoTecnico = diagnosticoTecnico;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
