package br.com.prevplan.service;

import br.com.prevplan.dto.PlanoRequest;
import br.com.prevplan.repository.PlanoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PlanoServiceTest {
    @Mock PlanoRepository repository;
    @InjectMocks PlanoService service;

    @Test
    void deveCriarPlanoValido() {
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        PlanoRequest request = new PlanoRequest("Plano Teste", "Descrição de teste", new BigDecimal("199.90"), true);
        var resposta = service.criar(request);
        assertEquals("Plano Teste", resposta.nome());
        assertEquals(new BigDecimal("199.90"), resposta.contribuicaoMensal());
        verify(repository).save(any());
    }
}
