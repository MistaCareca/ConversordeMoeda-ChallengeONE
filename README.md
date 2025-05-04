# ConversordeMoeda-ChallengeONE

## Descrição
Este projeto é um **Conversor de Moedas** desenvolvido em Java, que utiliza a API `exchangerate-api.com` para obter taxas de câmbio em tempo real e realizar conversões entre moedas selecionadas. O projeto foi implementado como parte de um desafio de programação, dividido em várias fases, culminando na capacidade de filtrar moedas específicas e converter valores informados pelo usuário.

### Funcionalidades
- Suporta conversão entre seis moedas: ARS (Peso Argentino), BOB (Boliviano Boliviano), BRL (Real Brasileiro), CLP (Peso Chileno), COP (Peso Colombiano) e USD (Dólar Americano).
- Solicita ao usuário a moeda de origem, a moeda de destino e o valor a ser convertido.
- Valida entradas do usuário (siglas de moedas e valores).
- Usa a biblioteca Gson para mapear respostas JSON da API.
- Exibe a taxa de câmbio e o resultado da conversão.
- Trata erros de rede, respostas inválidas da API e entradas incorretas.

### Tecnologias Utilizadas
- **Java 11+**: Para a lógica principal e comunicação HTTP.
- **HttpClient**: Biblioteca nativa do Java para requisições HTTP.
- **Gson**: Biblioteca para parsear e mapear respostas JSON.

## Pré-requisitos
- Java Development Kit (JDK) 11 ou superior.
- Acesso à internet para consultar a API.
- Chave da API `exchangerate-api.com` (obtenha em [exchangerate-api.com](https://www.exchangerate-api.com/)).

## Configuração
1. **Clone o Repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd conversor-de-moedas
   ```
   
## Uso
1. Execute o programa.
2. Escolha uma moeda de origem e uma moeda de destino entre as disponíveis: ARS, BOB, BRL, CLP, COP, USD.
3. Informe o valor a ser convertido (ex: 100.50).
4. O programa exibirá a taxa de câmbio e o resultado da conversão.

### Exemplo de Execução
```
Moedas disponíveis: ARS, BOB, BRL, CLP, COP, USD
Informe a sigla da moeda de origem (ex: USD): USD
Informe a sigla da moeda de destino (ex: BRL): BRL

=== Resultados (Mapeamento com Gson) ===
Taxa de câmbio: 1 USD = 5.1234 BRL

Informe o valor a converter (ex: 100.50): 100.50
Resultado da conversão: 100.50 USD = 514.89 BRL
```

## Estrutura do Projeto
```
conversor-de-moedas/
├── src/
│   └── main/
│       └── com/
│           └── conversordemoeda/
│               ├── Cliente_api.java      # Classe principal com a lógica de conversão
│               └── ConversaoResponse.java # Classe para mapear respostas JSON
├── .env                                 # Arquivo com a chave da API (não versionado)
├── pom.xml                              # Arquivo Maven (opcional)
└── README.md                            # Documentação do projeto
```

## Tratamento de Erros
- **Moedas Inválidas**: Verifica se as moedas estão na lista permitida.
- **Valores Inválidos**: Rejeita valores negativos ou não numéricos.
- **Erros da API**: Trata códigos HTTP diferentes de 200 e erros JSON (`error_type`).
- **Erros de Rede**: Captura exceções de rede e exibe mensagens claras.
