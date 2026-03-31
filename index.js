const POSTS_KEY = window.AppStorage.keys.posts;

const profileName = document.getElementById("profileName");
const profileTitle = document.getElementById("profileTitle");
const authActions = document.getElementById("authActions");
const postInput = document.getElementById("postInput");
const postBtn = document.getElementById("postBtn");
const postStatus = document.getElementById("postStatus");
const userPostsContainer = document.getElementById("userPostsContainer");

function getCurrentUser() {
    return window.AppStorage.read(window.AppStorage.keys.user, null);
}

function getStoredPosts() {
    return window.AppStorage.read(POSTS_KEY, []);
}

function savePosts(posts) {
    window.AppStorage.write(POSTS_KEY, posts);
}

function setStatus(message, isError) {
    postStatus.textContent = message;
    postStatus.style.color = isError ? "#c62828" : "#1f7a1f";
}

function updateProfileCard(user) {
    if (user) {
        profileName.textContent = user.name;
        profileTitle.textContent = user.role;
        authActions.style.display = "none";
    } else {
        profileName.textContent = "Guest User";
        profileTitle.textContent = "Please sign in";
        authActions.style.display = "flex";
    }
}

function updatePostComposer(user) {
    if (user) {
        postInput.disabled = false;
        postBtn.disabled = false;
        postInput.placeholder = "What do you want to talk about?";
        setStatus("", false);
    } else {
        postInput.disabled = true;
        postBtn.disabled = true;
        postInput.placeholder = "Sign in to create a post.";
        setStatus("Please sign in first to post.", true);
    }
}

function renderPosts() {
    const posts = getStoredPosts();

    if (!posts.length) {
        userPostsContainer.innerHTML = "";
        return;
    }

    const html = posts
        .slice()
        .reverse()
        .map(function (post) {
            return `
            <div class="post box">
                <div class="post-author">
                    <h4>${post.authorName}</h4>
                    <p class="author-title">${post.authorRole} • ${new Date(post.createdAt).toLocaleString()}</p>
                </div>
                <div class="post-text">
                    <p>${post.text}</p>
                </div>
                <hr>
                <div class="post-buttons">
                    <button type="button">Like</button>
                    <button type="button">Comment</button>
                    <button type="button">Share</button>
                </div>
            </div>
            `;
        })
        .join("");

    userPostsContainer.innerHTML = html;
}

postBtn.addEventListener("click", function () {
    const user = getCurrentUser();

    if (!user) {
        setStatus("Please sign in first to post.", true);
        window.location.href = "auth/login.html?next=../home/home.html";
        return;
    }

    const text = postInput.value.trim();
    if (!text) {
        setStatus("Write something before posting.", true);
        return;
    }

    const posts = getStoredPosts();
    posts.push({
        authorName: user.name,
        authorRole: user.role,
        text,
        createdAt: Date.now(),
    });

    savePosts(posts);
    postInput.value = "";
    setStatus("Post published.", false);
    renderPosts();
});

const currentUser = getCurrentUser();
updateProfileCard(currentUser);
updatePostComposer(currentUser);
renderPosts();
