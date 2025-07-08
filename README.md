# Literatura

Este repositório contém um projeto de pesquisa em API de livros desenvolvido como requisito para conclusão do plano de estudos ONE | TECH FOUNDATION - Especialização Back-End.

## Funcionalidades
O projeto apresenta um menu com as opções de busca:

1- Buscar livro pelo título: Busca informações do livro solicitado na API https://gutendex.com/. Se é a primeira consulta sobre o livro este é armazenado no banco de dados da aplicação.
2- Listar livros registrados: Lista as informações de todos os livros armazenados no banco de dados da aplicação.
3- Lista autores registrados: Lista as informações de todos os autores armazenados no banco de dados da aplicação.
4- Listar autores vivos em um determinado ano: Lista as informações de todos os autores armazenados no banco de dados da aplicação que estavam vivos no ano informado.
5- Listar livros em um determinado idioma: Lista as informações de todos os livros armazenados no banco de dados da aplicação escritos no idioma informado

## Base de dados
O projeto usa como base de dados para a busca de novos livros a API API https://gutendex.com/
As demais consultas são realizadas utilizando a base de dados local.

## Tratamento dos dados
Os dados recebidos da API são tratados com o uso da biblioteca jackson
