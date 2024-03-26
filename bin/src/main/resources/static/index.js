// Define isConnected como false inicialmente
let isConnected = false;

const socket = new WebSocket('ws://localhost:8080/conect');
const Client = Stomp.over(socket);

const form = document.querySelector('form');
form.addEventListener('submit', attemptLogin);

async function attemptLogin(event) {
    event.preventDefault();

    const data = new FormData(event.target);
    const username = data.get('username');
    const password = data.get('password');

    try {
        console.log("Tentativa de login com usuário:", username);
        console.log("Senha:", password);
      
        const response = await fetch("/user/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });
      
        if (response.ok) {
            const data = await response.json();
            console.log("Resposta do servidor:", data); // Adicione esta linha para verificar o conteúdo da resposta
            const token = data.token;
    
            storeToken(token);
            console.log("Token recebido:", token);
      
            // Redireciona o usuário para a página do chat após o login bem-sucedido
            window.location.href = "/chat/message";
        } else {
            const error = await response.text();
            console.error("Login failed:", error);
            alert("Login failed. Please check your credentials.");
        }
    } catch (error) {
        console.error("An error occurred during login:", error);
        alert("An error occurred during login. Please try again later.");
    }
}

function storeToken(token) {
    localStorage.setItem('Token', token);
}