
# Projeto de Chat em Tempo Real com WebSocket e Spring Security

Este projeto é uma aplicação de chat em tempo real desenvolvida em Java com o framework Spring. Ele utiliza WebSocket para permitir a comunicação em tempo real entre os usuários e é protegido pelo Spring Security para garantir a autenticação e autorização adequadas.

## Tecnologias Utilizadas
* Java
* Spring Boot
* Spring Security
* WebSocket
* PostgreSQL
* H2 Database (para testes)
* JavaScript (para funções do lado do cliente)
* HTML e CSS (para a interface básica do chat)

## Funcionalidades
Autenticação e Autorização Seguras: Utiliza o Spring Security para autenticar os usuários e garantir que apenas usuários autenticados tenham acesso ao chat.
Comunicação em Tempo Real: Implementa WebSocket para permitir a comunicação em tempo real entre os usuários do chat.
Persistência de Dados: Utiliza o banco de dados PostgreSQL para armazenar informações sobre os usuários, salas de chat e mensagens.
Testes com H2 Database: Utiliza o banco de dados H2 para testes, garantindo que os testes possam ser executados de forma isolada e rápida.
Estrutura do Projeto.

1. Executando a Aplicação: Execute a classe principal ChatWebsocketApplication para iniciar a aplicação Spring Boot.
2. Acessando o Chat: Abra um navegador e acesse a URL http://localhost:8080 para acessar o Login. Você será redirecionado para http://localhost:8080/chat/message onde poderá começar a usar o chat em tempo real.
