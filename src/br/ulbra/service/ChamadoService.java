/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.service;

import br.ulbra.dao.ChamadoDAO;
import br.ulbra.model.Chamado;
import java.util.List;

/**
 *
 * @author qijef
 */
public class ChamadoService {

    private ChamadoDAO dao;

    public ChamadoService(ChamadoDAO dao) {
        this.dao = dao;
    }

    public void cadastrar(Chamado chamado) {
        /* Validações de Relacionamento
        if (chamado.getIdUsuario() <= 0) {
            throw new RuntimeException("ID de Usuário inválido!");
        }
        if (chamado.getIdEquipamento() <= 0) {
            throw new RuntimeException("ID de Equipamento inválido!");
        }
        
        // Regra de Negócio: Todo chamado novo começa como 'ABERTO'
        if (chamado.getStatus() == null || chamado.getStatus().isEmpty()) {
            chamado.setStatus("ABERTO");
        }
        
        // Regra de Negócio: Se não houver prioridade, define como 'MÉDIA'
        if (chamado.getPrioridade() == null) {
            chamado.setPrioridade("MÉDIA");
        }
         */
        dao.salvar(chamado);
    }

    public List<Chamado> listar() {
        return dao.listar();
    }

    public void atualizar(Chamado chamado) {
        // Regra: Não permitir alterar a data de abertura original
        Chamado existente = dao.buscarPorId(chamado.getId());
        if (existente == null) {
            throw new RuntimeException("Chamado não encontrado!");
        }

        chamado.setDataAbertura(existente.getDataAbertura());
        dao.atualizar(chamado);
    }

    public void deletar(int id) {
        // Regra de Negócio: Não permite deletar chamados 'EM ANDAMENTO'
        Chamado c = dao.buscarPorId(id);
        if (c != null && "EM ANDAMENTO".equalsIgnoreCase(c.getStatus())) {
            throw new RuntimeException("Não é possível deletar um chamado em atendimento!");
        }
        dao.deletar(id);
    }

    public Chamado buscar(int id) {
        return dao.buscarPorId(id);
    }

    public List<Chamado> listarPorUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            throw new RuntimeException("ID de Usuário inválido para listagem!");
        }
        return dao.listarPorUsuario(idUsuario);
    }
}
