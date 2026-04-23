/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.dao;

import br.ulbra.model.Equipamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qijef
 */
public class EquipamentoDAOImpl implements EquipamentoDAO{

  
public List<Equipamento> listarTodos() {
    String sql = "SELECT id_equipamento, tag_patrimonio, sala FROM equipamentos ORDER BY tag_patrimonio";
    List<Equipamento> lista = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Equipamento e = new Equipamento();
            e.setId_equipamento(rs.getInt("id_equipamento"));
            e.setTag_patrimonio(rs.getString("tag_patrimonio"));
            e.setSala(rs.getString("sala"));
            lista.add(e);
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao listar equipamentos: " + e.getMessage());
    }
    return lista;
}
    
    @Override
    public void salvar(Equipamento equipamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Equipamento> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipamento buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Equipamento equipamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
