/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.dao;

import br.ulbra.model.Chamado;
import br.ulbra.model.Equipamento;
import br.ulbra.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATE TABLE chamado_tecnico ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
 * solicitante VARCHAR(100) NOT NULL, sala VARCHAR(50), equipamento_tag
 * VARCHAR(50), problema_relatado TEXT NOT NULL, diagnostico_tecnico TEXT,
 * prioridade VARCHAR(20) DEFAULT 'MÉDIA', status VARCHAR(20) DEFAULT
 * 'PENDENTE', data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP );
 */
public class ChamadoDAOImpl implements ChamadoDAO {

    @Override
    public void salvar(Chamado chamado) {
        // SQL atualizado com as novas colunas de ID e removendo as de texto redundantes
        String sql = "INSERT INTO chamado_tecnico (id_usuario, id_equipamento, "
                + "problema_relatado, diagnostico_tecnico, "
                + "prioridade, status, data_abertura) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, chamado.getIdUsuario());
            stmt.setInt(2, chamado.getIdEquipamento());
            stmt.setString(3, chamado.getProblemaRelatado());
            stmt.setString(4, chamado.getDiagnosticoTecnico());
            stmt.setString(5, chamado.getPrioridade());
            stmt.setString(6, chamado.getStatus());
            stmt.setString(7, chamado.getDataAbertura());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

@Override
public List<Chamado> listar() {
    // Adicionamos e.sala na busca
    String sql = "SELECT c.*, u.nome, e.tag_patrimonio, e.sala " +
                 "FROM chamado_tecnico c " +
                 "INNER JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                 "INNER JOIN equipamentos e ON c.id_equipamento = e.id_equipamento";
                 
    List<Chamado> lista = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Chamado c = new Chamado();
            c.setId(rs.getInt("id"));
            c.setIdUsuario(rs.getInt("id_usuario"));
            c.setIdEquipamento(rs.getInt("id_equipamento"));
            c.setProblemaRelatado(rs.getString("problema_relatado"));
            c.setDiagnosticoTecnico(rs.getString("diagnostico_tecnico"));
            c.setPrioridade(rs.getString("prioridade"));
            c.setStatus(rs.getString("status"));
            c.setDataAbertura(rs.getString("data_abertura"));

            // Usuário
            Usuario u = new Usuario();
            u.setNome(rs.getString("nome"));
            c.setUsuario(u);

            // Equipamento (Agora com a Sala)
            Equipamento equi = new Equipamento();
            equi.setTag_patrimonio(rs.getString("tag_patrimonio"));
            equi.setSala(rs.getString("sala")); // <--- Capturando a sala do banco
            c.setEquipamento(equi);

            lista.add(c);
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao listar chamados: " + e.getMessage());
    }
    return lista;
}

    @Override
    public Chamado buscarPorId(int id) {
        String sql = "SELECT * FROM chamado_tecnico WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Chamado(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_equipamento"),
                        rs.getString("problema_relatado"),
                        rs.getString("diagnostico_tecnico"),
                        rs.getString("prioridade"),
                        rs.getString("status"),
                        rs.getString("data_abertura")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void atualizar(Chamado chamado) {
        String sql = "UPDATE chamado_tecnico SET id_usuario=?, id_equipamento=?, "
                + "problema_relatado=?, diagnostico_tecnico=?,"
                + " prioridade=?, status=?, data_abertura=? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, chamado.getIdUsuario());
            stmt.setInt(2, chamado.getIdEquipamento());
            stmt.setString(3, chamado.getProblemaRelatado());
            stmt.setString(4, chamado.getDiagnosticoTecnico());
            stmt.setString(5, chamado.getPrioridade());
            stmt.setString(6, chamado.getStatus());
            stmt.setString(7, chamado.getDataAbertura());
            stmt.setInt(8, chamado.getId());
            
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM chamado_tecnico WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    // No ChamadoDAOImpl.java
public List<Chamado> listarPorUsuario(int idUsuario) {
    String sql = "SELECT c.*, u.nome, e.tag_patrimonio, e.sala " +
                 "FROM chamado_tecnico c " +
                 "INNER JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                 "INNER JOIN equipamentos e ON c.id_equipamento = e.id_equipamento " +
                 "WHERE c.id_usuario = ?"; // Filtro aqui!
                 
    List<Chamado> lista = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Chamado c = new Chamado();
            c.setId(rs.getInt("id"));
            c.setProblemaRelatado(rs.getString("problema_relatado"));
            c.setDiagnosticoTecnico(rs.getString("diagnostico_tecnico"));
            c.setPrioridade(rs.getString("prioridade"));
            c.setStatus(rs.getString("status"));
            c.setDataAbertura(rs.getString("data_abertura"));

            Usuario u = new Usuario();
            u.setNome(rs.getString("nome"));
            c.setUsuario(u);

            Equipamento equi = new Equipamento();
            equi.setTag_patrimonio(rs.getString("tag_patrimonio"));
            equi.setSala(rs.getString("sala"));
            c.setEquipamento(equi);

            lista.add(c);
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao listar chamados do usuário: " + e.getMessage());
    }
    return lista;
}
    
}