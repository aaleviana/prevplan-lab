package br.com.prevplan.controller;

import br.com.prevplan.dto.*;
import br.com.prevplan.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {
    private final PlanoService service;

    public PlanoController(PlanoService service) { this.service = service; }

    @GetMapping
    public List<PlanoResponse> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public PlanoResponse buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<PlanoResponse> criar(@Valid @RequestBody PlanoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public PlanoResponse atualizar(@PathVariable Long id, @Valid @RequestBody PlanoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }

    @GetMapping("/resumo")
    public ResumoResponse resumo() { return service.resumo(); }
}
