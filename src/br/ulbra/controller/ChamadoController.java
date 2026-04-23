/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.dao.ChamadoDAOImpl;
import br.ulbra.model.Chamado;
import br.ulbra.service.ChamadoService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qijef
 */
public class ChamadoController {

    private ChamadoService service;

    public ChamadoController() {
        this.service = new ChamadoService(new ChamadoDAOImpl());
    }

    // Agora passamos idUsuario e idEquipamento
    public String cadastrar(int idUsuario, int idEquipamento, String problemaRelatado, String diagnosticoTecnico,
            String prioridade, String status, String dataAbertura) {
        try {
            Chamado chamado = new Chamado();
            chamado.setIdUsuario(idUsuario);
            chamado.setIdEquipamento(idEquipamento);
            chamado.setProblemaRelatado(problemaRelatado);
            chamado.setDiagnosticoTecnico(diagnosticoTecnico);
            chamado.setPrioridade(prioridade);
            chamado.setStatus(status);
            chamado.setDataAbertura(dataAbertura);

            service.cadastrar(chamado);
            return "Chamado registrado com sucesso!";

        } catch (Exception e) {
            return "Erro ao cadastrar: " + e.getMessage();
        }
    }

    public List<Chamado> listarPorUsuario(int idUsuario) {
        try {
            return service.listarPorUsuario(idUsuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Chamado> listar() {
        return service.listar();
    }

    public String atualizar(int id, int idUsuario, int idEquipamento, String problemaRelatado,
            String diagnostico, String prioridade, String status, String data_abertura) {
        try {
            Chamado chamado = new Chamado();
            chamado.setId(id);
            chamado.setIdUsuario(idUsuario);
            chamado.setIdEquipamento(idEquipamento);
            chamado.setProblemaRelatado(problemaRelatado);
            chamado.setDiagnosticoTecnico(diagnostico);
            chamado.setPrioridade(prioridade);
            chamado.setStatus(status);
            chamado.setDataAbertura(data_abertura);

            service.atualizar(chamado);
            return "Chamado atualizado com sucesso!";
        } catch (Exception e) {
            return "Erro ao atualizar: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        try {
            service.deletar(id);
            return "Chamado removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao deletar: " + e.getMessage();
        }
    }
}
