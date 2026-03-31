const user = window.AppStorage.read(window.AppStorage.keys.user, null);

if (!user) {
    window.location.href = "../auth/login.html?next=../profile/profile.html";
}

if (user) {
    document.getElementById("profileName").textContent = user.name;
    document.getElementById("profileHeadline").textContent = user.role;

    document.getElementById("logoutBtn").addEventListener("click", function () {
        window.AppStorage.remove(window.AppStorage.keys.user);
        window.location.href = "../index.html";
    });
}
