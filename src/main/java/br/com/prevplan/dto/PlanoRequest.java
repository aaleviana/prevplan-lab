package br.com.prevplan.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlanoRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 240, message = "A descrição deve ter no máximo 240 caracteres")
        String descricao,

        @NotNull(message = "A contribuição mensal é obrigatória")
        @DecimalMin(value = "0.01", message = "A contribuição deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "Use no máximo duas casas decimais")
        BigDecimal contribuicaoMensal,

        boolean ativo
) {}
