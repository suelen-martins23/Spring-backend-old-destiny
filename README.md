<h1>🎮 Serviço Middleware para Jogo de MMORPG 🌎</h2>
Este projeto consiste em um serviço intermediário entre o site e o servidor de um jogo de MMORPG. Ele fornece uma API para gerenciar contas de jogadores e se comunica com o servidor do jogo para autenticar os jogadores e permitir o acesso aos recursos do jogo.

🧰 Funcionalidades do Serviço
O serviço é dividido em três componentes principais, cada um com suas próprias funcionalidades:

<h2>OnlyRead</h2>
Este componente é responsável por ler informações das contas dos jogadores. Ele oferece a possibilidade de consultar informações como nome de usuário, email, personagens criados, entre outras.

<h2>OnlyWrite</h2>
Este componente é responsável por gravar informações nas contas dos jogadores. Ele oferece a possibilidade de criar uma nova conta, alterar informações como nome de usuário e email, e excluir contas.

<h2>GoogleRecaptcha</h2>
Este componente é responsável pela validação do Google reCAPTCHA. Ele verifica se o usuário é humano e protege contra bots e ataques maliciosos.

💻 Exemplo de Uso
Abaixo está um exemplo de como criar uma nova conta usando a API do serviço:

<PRE>
POST /api/v1/register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 7a8a29d3-7b22-96e1-cb7f-d06391a49278

{
    "username": "jogador1",
    "email": "jogador1@email.com",
    "password": "senha123"
}
    
</PRE>

<h2>🚀 Tecnologias Utilizadas</h2>
🟡 Spring Boot
🟡 Java
🟡 Google reCAPTCHA
🟡 Gson

<h2>📖 Documentação</h2>
Para acessar a documentação da API, basta executar o projeto e acessar o link http://localhost:8080/swagger-ui.html. A documentação foi criada com o Spring Fox, que utiliza o Swagger para criar uma interface interativa com a API.
