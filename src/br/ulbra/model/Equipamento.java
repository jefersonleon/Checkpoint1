/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

/**
 *
 * `id_equipamento` INT NOT NULL AUTO_INCREMENT,
  `tag_patrimonio` VARCHAR(50) NOT NULL UNIQUE,
  `sala` VARCHAR(50),

 */
public class Equipamento {
    private int id_equipamento;
    private String tag_patrimonio;
    private String sala;

    public Equipamento() {
    }

    public Equipamento(int id_equipamento, String tag_patrimonio, String sala) {
        this.id_equipamento = id_equipamento;
        this.tag_patrimonio = tag_patrimonio;
        this.sala = sala;
    }

    public int getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(int id_equipamento) {
        this.id_equipamento = id_equipamento;
    }

    public String getTag_patrimonio() {
        return tag_patrimonio;
    }

    public void setTag_patrimonio(String tag_patrimonio) {
        this.tag_patrimonio = tag_patrimonio;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return tag_patrimonio;
    }
    
}
