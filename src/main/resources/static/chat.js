function sendMessage(e) {
    e.preventDefault();
    const messageInput = document.getElementById("messageInput").value;

    displayMessage(messageInput,sessionStorage.getItem('user'))
   
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
   
    messageContainer.textContent = user + ": " + message;
    chatMessages.appendChild(messageContainer);
}
