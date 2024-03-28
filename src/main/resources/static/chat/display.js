let isConnected = false;
let messageInputValue = ''; 

const socket = new WebSocket('ws://localhost:8080/conect');
const Client = Stomp.over(socket);

connect();
function sendMessage(e) {
    e.preventDefault();
    const messageInput = document.getElementById("messageInput").value.trim();
    if (!messageInput) { 
        return;
    }
    const user = sessionStorage.getItem('username');
    if (Client && Client.connected) { 
        Client.send("/app/chat/message", {}, JSON.stringify({ msg: messageInput, user: user }));
        document.getElementById("messageInput").value = ''; 
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
            displayMessage(chatMessage); 
        });
    });
}

function displayMessage(message) {
    const chatMessages = document.getElementById("chatMessages");
    const messageContainer = document.createElement("div");
   
    messageContainer.textContent = message.user + ": " + message.msg; 
    chatMessages.appendChild(messageContainer);
}
function disconnectUser() {
    sessionStorage.removeItem('username'); // Remover o nome de usuário da sessão
    window.location.href = "http://localhost:8080"; // Redirecionar para a página inicial
}
