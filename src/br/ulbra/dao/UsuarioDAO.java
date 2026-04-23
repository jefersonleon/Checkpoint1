/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.dao;

import br.ulbra.model.Chamado;
import br.ulbra.model.Usuario;
import java.util.List;

/**
 *
 * @author qijef
 */
public interface UsuarioDAO {

    void salvar(Usuario usuario);

    List<Usuario> listar();

    Usuario buscarPorId(int id);

    void atualizar(Usuario usuario);

    void deletar(int id);

}
