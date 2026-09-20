package br.com.prevplan.service;

import br.com.prevplan.dto.*;
import br.com.prevplan.exception.RecursoNaoEncontradoException;
import br.com.prevplan.model.Plano;
import br.com.prevplan.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.util.List;

@Service
public class PlanoService {
    private final PlanoRepository repository;

    public PlanoService(PlanoRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<PlanoResponse> listar() {
        return repository.findAll().stream().map(PlanoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public PlanoResponse buscar(Long id) { return PlanoResponse.from(buscarEntidade(id)); }

    @Transactional
    public PlanoResponse criar(PlanoRequest request) {
        Plano plano = new Plano(request.nome(), request.descricao(), request.contribuicaoMensal(), request.ativo());
        return PlanoResponse.from(repository.save(plano));
    }

    @Transactional
    public PlanoResponse atualizar(Long id, PlanoRequest request) {
        Plano plano = buscarEntidade(id);
        plano.atualizar(request.nome(), request.descricao(), request.contribuicaoMensal(), request.ativo());
        return PlanoResponse.from(plano);
    }

    @Transactional
    public void excluir(Long id) { repository.delete(buscarEntidade(id)); }

    @Transactional(readOnly = true)
    public ResumoResponse resumo() {
        List<Plano> planos = repository.findAll();
        BigDecimal media = planos.stream().map(Plano::getContribuicaoMensal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!planos.isEmpty()) {
            media = media.divide(BigDecimal.valueOf(planos.size()), 2, RoundingMode.HALF_UP);
        }
        return new ResumoResponse(planos.size(), repository.countByAtivoTrue(), media);
    }

    private Plano buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado: " + id));
    }
}
