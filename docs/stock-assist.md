# Assistente de Estoque

Esse documento contém um geral sobre o sistema da aplicação.

## Objetivo

O sistema irá ler um arquivo CSV gera por outro sistema que contém os dados de estoque, aplicar regras de negócio para identificar os itens que precisam ser repostos, integrar com uma API externa de um setor de compras, utilizando um token de autenticação para garantir a segurança da comunicação.

Ela irá persistir os dados no MongoDB para acompanhamento e auditoria.

## Regras de Negócio:

- Deveremos consumir o arquivo diário no formato CSV.
  - Ele terá os seguintes campos:
  - item_id (uuid)
  - item_name
  - quantity
  - reorder_threshold
  - supplier_name
  - supplier_email
  - last_stock_update_time 
- Quando um item estiver abaixo da quantidade mínima em estoque, deveremos solicitar a recompra com a quantidade mínima + 20% de margem de segurança.

- Para interagir com a API do Setor de Compras, deveremos:
  - Autenticar na API
  - Realizar a chamada utilizando o token da etapa anterior
- Deveremos manter armazenado todos os itens que tiveram uma solicitação de recompra, possuindo as seguintes informações:
  - Identificação do Item
  - Nome do Item
  - Quantidade em estoque
  - Quantidade mínima de itens no estoque
  - Nome do Fornecedor
  - Email do Fornecedor
  - Última atualização do item no estoque
  - Quantidade de recompra solicitada
  - Indicativo se a recompra foi enviada com sucesso ou falha
  - Data e hora do envio da solicitação de recompra.
