package br.ufrn.SmartRecibos.dto;

import br.ufrn.SmartRecibos.model.Papel;

public record FuncionarioRequest(
        String nome,
        String email,
        String telefone,
        Papel papel) {
}