package br.com.prevplan.dto;

import br.com.prevplan.model.Plano;
import java.math.BigDecimal;

public record PlanoResponse(Long id, String nome, String descricao, BigDecimal contribuicaoMensal, boolean ativo) {
    public static PlanoResponse from(Plano plano) {
        return new PlanoResponse(plano.getId(), plano.getNome(), plano.getDescricao(),
                plano.getContribuicaoMensal(), plano.isAtivo());
    }
}
