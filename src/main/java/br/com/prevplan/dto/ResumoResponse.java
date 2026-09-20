package br.com.prevplan.dto;

import java.math.BigDecimal;

public record ResumoResponse(long totalPlanos, long planosAtivos, BigDecimal contribuicaoMedia) {}
