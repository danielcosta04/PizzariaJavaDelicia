Projeto Prático: Sistema de Entregas de uma Pizzaria usando Coleções Genéricas em Java
(Atividade baseada nos conteúdos de List, ArrayList, Stack, Queue, PriorityQueue, Set e Map.)
Contextualização 
Você foi contratado para desenvolver uma parte do sistema interno de uma pizzaria chamada Pizzaria Java
Delícia.
O estabelecimento trabalha com pedidos online, fila de entregas, controle de clientes, histórico e ranking
de sabores.
Seu objetivo é criar módulos do sistema utilizando coleções genéricas da linguagem Java para organizar e
processar as informações da pizzaria.
O proprietário quer um sistema simples, porém eficiente, capaz de registrar pedidos, organizar entregas,
evitar repetição de cadastros e identificar os sabores mais vendidos.

Descrição Geral do Projeto
Você deverá implementar, passo a passo, os componentes do sistema usando:
• ArrayList → para armazenar pedidos abertos
• LinkedList / Queue → para gerenciar fila de entregas
• Stack → para armazenar histórico de navegação ou pedidos cancelados
• PriorityQueue → para pedidos prioritários (ex.: clientes VIP ou pedidos expressos)
• HashSet → para cadastro de sabores sem duplicação
• HashMap → para associar clientes aos seus pedidos ou registrar vendas por sabor
O sistema final deve permitir manipular dados dessas coleções e simular o funcionamento real de uma
pizzaria.

Tarefas a serem implementadas
Abaixo estão os requisitos do projeto. Cada item deve ser implementado com coleções genéricas adequadas.

1. Cadastro de Sabores (Set<String>)
Crie um conjunto contendo todos os sabores disponíveis:
• Deve usar HashSet<String>.
• O sistema não deve permitir sabores duplicados.
• Permita adicionar, remover, listar e verificar se um sabor existe.

Exemplo de uso esperado:
sabores.add("Calabresa");
sabores.add("Mussarela");
sabores.add("Calabresa"); // deve ser ignorado

2. Registro de Pedidos (ArrayList<Pedido>)
Cada pedido deve ser representado por uma classe Pedido, contendo:
• número do pedido
• nome do cliente
• sabor
• tamanho (P, M, G)
• valor
Armazene os pedidos abertos em um ArrayList<Pedido>.
Funcionalidades obrigatórias:
• adicionar pedido
• listar pedidos
• buscar pedido pelo número (pesquisa linear)
• ordenar pedidos por valor ou nome do cliente (Collections.sort + Comparator)

3. Fila de Entregas (Queue<Pedido>)
Implemente a fila de entregas utilizando:
LinkedList<Pedido> ou sua classe Queue<T> estudada em aula.
Funcionalidades:
• adicionar pedido à fila
• remover pedido (entregar)
• consultar o próximo sem remover (peek)
• verificar se a fila está vazia

4. Pedidos Prioritários (PriorityQueue<Pedido>)
Alguns pedidos (clientes VIP ou entregas expressas) devem ter prioridade.
Crie uma PriorityQueue onde:
• Pedidos com menor tempo de preparo estimado têm prioridade
• Use um Comparator para definir a ordem

5. Histórico de Pedidos Cancelados (Stack<Pedido>)
Use uma Stack<Pedido> para armazenar pedidos cancelados:
• cada cancelamento chama push
• recuperar último pedido cancelado com pop
• visualizar sem remover com peek

6. Registro de Vendas por Sabor (Map<String, Integer>)
Use um HashMap<String, Integer> para contar quantas pizzas de cada sabor foram vendidas.
Exemplo:
vendas.put("Calabresa", vendas.getOrDefault("Calabresa", 0) + 1);
Permita:
• exibição do ranking de sabores mais vendidos
• consulta do total vendido para um sabor específico
• listagem de todos os sabores registrados

Extensões opcionais
Se quiser tornar o sistema mais robusto, implemente também:
• salvar e carregar dados em arquivos .txt
• menu de opções usando Scanner
• tempo estimado de entrega com Random
• uso de TreeSet para ordenar sabores automaticamente

Entrega
O aluno deve entregar:
Código fonte organizado em pacotes:
• model (classes Pedido, Cliente etc.)
• service (classe de operações com coleções)
• app (classe Main)
