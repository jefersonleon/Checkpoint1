package br.ulbra.utils;

import br.ulbra.model.Usuario;

public class Sessao {
    private static Usuario usuarioLogado;

    public static void setUsuario(Usuario u) {
        usuarioLogado = u;
    }

    public static Usuario getUsuario() {
        return usuarioLogado;
    }
}