# Sistema de Gestão Acadêmica  
Um sistema abrangente para gerenciar atividades acadêmicas, dicas de estudo e geração de relatórios. Os estudantes podem acompanhar suas atividades extracurriculares, ganhar créditos, compartilhar recursos de estudo e monitorar seu progresso acadêmico.

## Funcionalidades
### Gestão de Atividades
- **Tipos**: Monitoria, Estágio, Extensão, Representação Estudantil  
- **Validação**: Garante nomes válidos de disciplinas, empresas e durações das atividades  
- **Cálculo de Créditos**:
  - Monitoria: 4 créditos/semestre (máximo 16)
  - Estágio: 1 crédito/60 horas (máximo 18)
  - Extensão: 10 créditos/12 meses (máximo 18)
  - Representação: 2 créditos/ano (máximo 2)

### Sistema de Dicas de Estudo
- **Suporte Multimídia**: Adição de vídeos/áudios com rastreamento de duração  
- **Gestão de Referências**: Referências acadêmicas verificadas com avaliações de importância  
- **Sistema de Bônus**: Ganhe pontos por contribuições valiosas
  - Texto: Até 50 pontos por explicações detalhadas  
  - Mídia: 5 pontos/minuto (máximo 50)  
  - Referências Verificadas: 15 pontos cada

### Relatórios
- **Acompanhamento de Progresso**: Monitoramento em tempo real do acúmulo de créditos  
- **Tipos de Relatório**:
  - Relatório Final: Resumo geral de créditos  
  - Por Atividade: Detalhamento por tipo de atividade  
  - Histórico: Salvar/recuperar relatórios anteriores

### Gestão de Usuários
- Autenticação segura  
- Políticas de senha (exatamente 8 caracteres)  
- Sistema de ranking por bônus  
- Comparação de estudantes por nome/ordem alfabética  

## Tecnologias Utilizadas
- **Núcleo**: Java 21+  
- **Testes**: JUnit 5  
- **Validação**: Middleware personalizado para verificações de nulo/vazio  
- **Padrão de Projeto**: Facade para simplificação da API

## Estrutura do Projeto
```
.
├── main/java/
│   ├── atividade/          # Classes e controlador de atividades
│   ├── dica/               # Sistema de dicas de estudo
│   ├── relatorio/          # Geração de relatórios
│   ├── usuario/            # Gestão de usuários
│   ├── middleware/         # Utilitários de validação
│   └── facade/             # Interface unificada do sistema
├── test/java/              # Testes unitários abrangentes
└── README.md
```

## Instalação e Uso
1. **Compilar**:
```bash
mvn compile
```

2. **Executar Testes**:
```bash
mvn test
```

3. **Exemplo de Uso**:
```java
FacadeComplementaACAO sistema = new FacadeComplementaACAO();

// Criar estudante
sistema.criarEstudante("Maria Silva", "123.456.789-09", "senha123", "124110000");

// Adicionar atividade de estágio
String codigo = sistema.criarAtividadeEstagioEmEstudante(
    "123.456.789-09", "senha123", 300, "Google"
);

// Gerar relatório
String relatorio = sistema.gerarRelatorioFinal(
    "123.456.789-09", "senha123"
);
```
## O Que Eu Aprendi
1. **Padrões de Validação**:
   - Verificações de nulo/vazio com exceções personalizadas  
   - Validação de formato de CPF  
   - Requisitos de complexidade de senha  

2. **Estratégias de Testes**:
   - Testes JUnit parametrizados  
   - Testes de casos extremos (strings vazias, valores negativos)  
   - Cobertura de 100% da lógica de validação  

3. **Design de Sistema**:
   - Padrão Facade para acesso simplificado  
   - Tratamento polimórfico de atividades  
   - Estratégias de cálculo de crédito  

4. **Gestão de Dados**:
   - HashMaps para armazenamento de usuários  
   - ArrayLists para rastreamento de atividades  
   - LinkedHashMap para limites de crédito ordenados  

5. **Relatórios**:
   - Geração dinâmica de relatórios  
   - Persistência de dados históricos  
   - Algoritmos de acompanhamento de progresso  

## Contribuidores
- [NotAdson] - Desenvolvedor único
--- 
