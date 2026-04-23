/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.dao;

import br.ulbra.model.Chamado;
import br.ulbra.model.Equipamento;
import java.util.List;

/**
 *
 * @author qijef
 */
public interface EquipamentoDAO {
    void salvar(Equipamento equipamento);
    List<Equipamento> listar();
    Equipamento buscarPorId(int id);
    void atualizar(Equipamento equipamento);
    void deletar(int id);


}
