let isConnected = false;
let messageInputValue = ''; // Variável para armazenar a mensagem digitada

const socket = new WebSocket('ws://localhost:8080/conect');
const Client = Stomp.over(socket);

function sendMessage(e) {
    e.preventDefault();
    const messageInput = document.getElementById("messageInput").value.trim();
    if (!messageInput) { 
        return;
    }
    const user = sessionStorage.getItem('username');
    if (Client && Client.connected) { // Verifica se o cliente e a conexão estão ativos
        Client.send("/app/chat/message", {}, JSON.stringify({ message: messageInput, user: user }));
        document.getElementById("messageInput").value = "";
    } else {
        console.error("A conexão WebSocket não está aberta.");
    }
}

function connect() {
    Client.connect({}, function (frame) {
        console.log('Conectado: ' + frame);
        isConnected = true; 
        Client.subscribe('/chat', function (message) {
            const chatMessage = JSON.parse(message.body);
            displayMessage(chatMessage); // Passa a mensagem completa para a função de exibição
        });
    });
}

function displayMessage(message) {
    const chatMessages = document.getElementById("chatMessages");
    const messageContainer = document.createElement("div");
   
    messageContainer.textContent = message.user + ": " + message.message;
    chatMessages.appendChild(messageContainer);
}
connect();


