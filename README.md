# PrevPlan

Sistema de gestão de planos previdenciários com painel web e API REST.
Permite editar contribuições, ativar ou desativar planos e excluir registros.
O painel mostra a quantidade de planos, os ativos e a contribuição média de todos os planos cadastrados.

## Executar localmente

Requisitos: JDK 21 ou superior compatível com o Spring Boot utilizado e Maven 3.9+.

Na pasta que contém o `pom.xml`:

```sh
mvn spring-boot:run
```

Abra <http://localhost:8080>. Para encerrar, use `Ctrl+C` no terminal.

O banco H2 roda em memória e recebe três planos de exemplo ao iniciar.
As alterações são perdidas quando a aplicação é encerrada.

## Organização

| Local | Responsabilidade |
| --- | --- |
| `src/main/java/br/com/prevplan/controller` | Endpoints HTTP |
| `src/main/java/br/com/prevplan/service` | Operações e transações |
| `src/main/java/br/com/prevplan/repository` | Acesso aos dados com JPA |
| `src/main/java/br/com/prevplan/model` | Entidade persistida |
| `src/main/java/br/com/prevplan/dto` | Entrada, validação e resposta da API |
| `src/main/java/br/com/prevplan/exception` | Tratamento de erros |
| `src/main/resources/static` | Interface em HTML, CSS e JavaScript |
| `src/test/java` | Teste unitário do serviço |
| `docs/oracle.sql` | Exemplo separado de SQL e PL/SQL para Oracle 12c+ |

## API

| Método | Endereço | Operação |
| --- | --- | --- |
| GET | `/api/planos` | Listar |
| GET | `/api/planos/{id}` | Buscar por ID |
| POST | `/api/planos` | Criar |
| PUT | `/api/planos/{id}` | Atualizar |
| DELETE | `/api/planos/{id}` | Excluir |
| GET | `/api/planos/resumo` | Consultar indicadores |

Exemplo de corpo para criação ou atualização:

```json
{
  "nome": "Plano Futuro",
  "descricao": "Plano para formação de reserva de longo prazo",
  "contribuicaoMensal": 300.00,
  "ativo": true
}
```

Os campos de nome e descrição são obrigatórios. A contribuição deve ser positiva,
com até duas casas decimais. Valores monetários são representados por `BigDecimal` no backend.
Entradas inválidas retornam HTTP 400; IDs inexistentes retornam HTTP 404.

## Teste e build

```sh
mvn test
mvn package
java -jar target/prevplan-lab-1.0.0.jar
```

O teste incluído cobre a criação de um plano no serviço, com o repositório simulado
por Mockito. Não representa cobertura completa da aplicação.

Há também um Dockerfile para build em múltiplos estágios:

```sh
docker build -t prevplan-lab .
docker run --rm -p 127.0.0.1:8080:8080 prevplan-lab
```

## Configuração atual

- A API não possui autenticação nem controle de acesso.
- A aplicação usa H2, não Oracle. O script Oracle é independente e não é executado na inicialização.
- A listagem não tem paginação.
- Testes de integração e persistência permanente ainda não foram implementados.

Stack: Java, Spring Boot 4.1.1, Spring Data JPA, Bean Validation, H2 e JavaScript.
