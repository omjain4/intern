const USERS_KEY = window.AppStorage.keys.users;

const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const authMessage = document.getElementById("authMessage");

function getUsers() {
    return window.AppStorage.read(USERS_KEY, []);
}

function saveUsers(users) {
   window.AppStorage.write(USERS_KEY, users);
}

function setCurrentUser(user) {
    window.AppStorage.write(window.AppStorage.keys.user, user);
}

function showMessage(message, isError) {
    if (!authMessage) {
        return;
    }
    authMessage.textContent = message;
    authMessage.style.color = isError ? "#c62828" : "#1f7a1f";
}

function getNextPath() {
    const params = new URLSearchParams(window.location.search);
    return params.get("next") || "../home/home.html";
}


if (loginForm) {
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const email = document.getElementById("loginEmail").value.trim().toLowerCase();
        const password = document.getElementById("loginPassword").value;

        const users = getUsers();
        const user = users.find(function (item) {
            return item.email === email && item.password === password;
        });

        if (!user) {
            showMessage("Invalid email or password.", true);
            return;
        }

        setCurrentUser({
            name: user.name,
            role: user.role,
            email: user.email,
        });

        showMessage("Sign in successful. Redirecting...", false);
        window.location.href = getNextPath();
    });
}

if (registerForm) {
    registerForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const name = document.getElementById("registerName").value.trim();
        const role = document.getElementById("registerRole").value.trim();
        const email = document.getElementById("registerEmail").value.trim().toLowerCase();
        const password = document.getElementById("registerPassword").value;

        const users = getUsers();
        const emailExists = users.some(function (item) {
            return item.email === email;
        });

        if (emailExists) {
            showMessage("Email already exists. Please sign in.", true);
            return;
        }

        users.push({ name, role, email, password });
        saveUsers(users);
        setCurrentUser({ name, role, email });

        showMessage("Registration successful. Redirecting...", false);
        window.location.href = getNextPath();
    });
}
