/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.dao;

import br.ulbra.model.Chamado;
import java.util.List;

/**
 *
 * @author qijef
 */
public interface ChamadoDAO {
     void salvar(Chamado usuario);
    List<Chamado> listar();
    Chamado buscarPorId(int id);
    void atualizar(Chamado usuario);
    void deletar(int id);
    List<Chamado> listarPorUsuario(int idUsuario);

}
