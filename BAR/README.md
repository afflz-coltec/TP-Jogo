# TP - JavaBAR

### Inspiração

O jogo javaBAR tenta trazer de volta o espírito dos jogos de Orkut, mas trazendo
um jogo de estrategia que vem pra unir conceitos de economia e as práticas de
informática.

### Funcionamento

O jogo se baseia na vida de um gerente de bar, considerando o preço de venda das
bebidas, comportamento de clientes, popularidade com certos grupos e outros
fatores pra chegar a um jogo de estrategia que trabalha conceitos de mercado e
fatores comportamentais pra chegar a resultados que mais agradam.

### Funcionalidades

Inicialmente o jogo vem a trazer uma interface gráfica simples, somente um painel
do proprietário para alterações de preço e afins. Para representação dos clientes
serão gerados clientes ao longo do tempo de acordo com grupos predefinidos e tais
grupos reagem ao bar criando um status de popularidade relativa que promove uma
maior frequencia de clientes desse determinadado grupo. Com uma thread por mesa,
gerando dados de consumo aleatórios dentro dos padrões de determindado grupo.

### Tópicos utilizados

Para o protótipo mais básicos, os principais tópicos a serem utilizados são:
- Threads
- Java2D
- Slick2D
Caso todos os objetivos do projeto inicial sejam concluidos em tempo hábil, a
extenção do projeto inclui:
- Sockets (Multiplayer)
- Banco de dados (Uma geração aleatória e evolutiva de tipos de clientes
  requereria um Banco de dados para armazenar as informações).
