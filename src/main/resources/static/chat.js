async function createChatRoom() {
    try {
        const response = await fetch("/chat/message");
        
        if (response.ok) {
            const htmlContent = await response.text();
            document.body.innerHTML = htmlContent;
            if (isConnected) { // Se isConnected for true, chama a função connect()
                connect();
            }
        } else {
            console.error("Failed to load chat room:", response.statusText);
            alert("Failed to load chat room. Please try again later.");
        }
    } catch (error) {
        console.error("Error loading chat room:", error);
        alert("An error occurred while loading the chat room. Please try again later.");
    }
}

function sendMessage(e) {
    e.preventDefault();
    const messageInput = document.getElementById("messageInput").value;

    const message = {
        user: sessionStorage.getItem("user"),
        msg: messageInput
    };

    Client.send("/app/chatMessage", {}, JSON.stringify(message));

    document.getElementById("messageInput").value = "";
}

function connect(){
    Client.connect({}, function (frame) {
        console.log('Conectado: ' + frame);
        isConnected = true; // Define isConnected como true quando a conexão é estabelecida
        Client.subscribe('/canal', function (message) {
            const chatMessage = JSON.parse(message.body);
            displayMessage(chatMessage.msg, chatMessage.user);
        });
    });
}

function displayMessage(message, user) {
    const chatMessages = document.getElementById("chatMessages");
    const messageContainer = document.createElement("div");
    const messageText = document.createElement("div");
    const userText = document.createElement("div");

    messageContainer.classList.add("message-container");
    messageText.textContent = message;
    userText.textContent = user;

    messageContainer.appendChild(userText);
    messageContainer.appendChild(messageText);
    chatMessages.appendChild(messageContainer);
}

document.addEventListener("DOMContentLoaded", function() {
    if (sessionStorage.getItem("user")) { // Verifica se o usuário está autenticado
        createChatRoom(); // Chama createChatRoom() apenas se o usuário estiver autenticado
    }
});