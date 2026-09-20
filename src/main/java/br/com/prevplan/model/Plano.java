package br.com.prevplan.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planos")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 240)
    private String descricao;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal contribuicaoMensal;

    @Column(nullable = false)
    private boolean ativo;

    protected Plano() {}

    public Plano(String nome, String descricao, BigDecimal contribuicaoMensal, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.contribuicaoMensal = contribuicaoMensal;
        this.ativo = ativo;
    }

    public void atualizar(String nome, String descricao, BigDecimal contribuicaoMensal, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.contribuicaoMensal = contribuicaoMensal;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getContribuicaoMensal() { return contribuicaoMensal; }
    public boolean isAtivo() { return ativo; }
}
