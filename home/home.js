// THEME TOGGLE 
// Light and Dark Theme Implementation

class ThemeManager {
    constructor() {
        this.currentTheme = this.loadTheme();
        this.init();
    }

    init() {
        this.applyTheme(this.currentTheme);
        this.setupThemeToggleButton();
    }

    loadTheme() {
        // Try to load theme from localStorage, default to light
        return localStorage.getItem('theme') || 'light';
    }

    saveTheme(theme) {
        localStorage.setItem('theme', theme);
    }

    applyTheme(theme) {
        const html = document.documentElement;
        
        if (theme === 'dark') {
            html.setAttribute('data-theme', 'dark');
            document.body.style.background = '#1a1a1a';
            document.body.style.color = '#e0e0e0';
        } else {
            html.setAttribute('data-theme', 'light');
            document.body.style.background = 'linear-gradient(180deg, #f6f9fc 0%, #eef2f7 100%)';
            document.body.style.color = '#1f2937';
        }

        this.currentTheme = theme;
        this.saveTheme(theme);
    }

    toggleTheme() {
        const newTheme = this.currentTheme === 'light' ? 'dark' : 'light';
        this.applyTheme(newTheme);
        this.updateThemeButtonIcon();
    }

    setupThemeToggleButton() {
        const themeBtn = document.getElementById('themeToggleBtn');
        if (themeBtn) {
            themeBtn.addEventListener('click', () => this.toggleTheme());
            this.updateThemeButtonIcon();
        }
    }

    updateThemeButtonIcon() {
        const themeBtn = document.getElementById('themeToggleBtn');
        if (themeBtn) {
            themeBtn.innerHTML = this.currentTheme === 'light' ? '🌙' : '☀️';
            themeBtn.title = this.currentTheme === 'light' ? 'Switch to Dark Mode' : 'Switch to Light Mode';
        }
    }
}

//DEBOUNCE FUNCTION
// Delays function execution until after specified delay has passed without new calls

function debounce(func, delay) {
    let timeoutId;
    return function(...args) {
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => {
            func.apply(this, args);
        }, delay);
    };
}

// Example usage: Debounced user search
const debouncedSearch = debounce((query) => {
    console.log('Searching for:', query);
    performUserSearch(query);
}, 500);

//THROTTLE FUNCTION
// Limits function execution to at most once per specified interval

function throttle(func, limit) {
    let inThrottle;
    return function(...args) {
        if (!inThrottle) {
            func.apply(this, args);
            inThrottle = true;
            setTimeout(() => {
                inThrottle = false;
            }, limit);
        }
    };
}

// Example usage: Throttled scroll event listener
const throttledScroll = throttle(() => {
    console.log('Scroll event throttled');
    loadMorePosts();
}, 1000);


//PROMISES AND ASYNC/AWAIT

// Async function to load more posts
async function loadMorePosts() {
    try {
        console.log('Loading more posts...');
        
        // Simulate fetching posts with async/await
        const posts = await new Promise((resolve) => {
            setTimeout(() => {
                resolve([
                    { author: 'Emma Davis', content: 'Excited about new project!' },
                    { author: 'David Lee', content: 'Tips for career growth' }
                ]);
            }, 1000);
        });

        appendPostsToContainer(posts);
    } catch (error) {
        console.error('Error loading posts:', error);
    }
}

// Async function to perform user search with async/await - searches real localStorage users
async function performUserSearch(query) {
    try {
        // Get all users from localStorage
        const allUsers = window.AppStorage.read(window.AppStorage.keys.users, []);
        const currentUser = window.AppStorage.read(window.AppStorage.keys.user, null);
        
        console.log('All users from storage:', allUsers);
        console.log(`Searching users for: "${query}"`);
        
        // Perform search with delay simulation
        const results = await new Promise((resolve) => {
            setTimeout(() => {
                // Filter users dynamically based on query
                let filteredUsers = allUsers;
                
                // If query is empty, show all users
                if (!query || !query.trim()) {
                    resolve([]);
                    return;
                }
                
                const searchTerm = query.trim().toLowerCase();
                filteredUsers = allUsers.filter(user => {
                    // Search in name, email, and role
                    return (
                        user.name.toLowerCase().includes(searchTerm) ||
                        user.email.toLowerCase().includes(searchTerm) ||
                        (user.role && user.role.toLowerCase().includes(searchTerm))
                    );
                });
                
                // Exclude current user from search results
                if (currentUser) {
                    filteredUsers = filteredUsers.filter(user => user.email !== currentUser.email);
                }
                
                // Map to display format
                const displayUsers = filteredUsers.map(user => ({
                    id: user.email,
                    name: user.name,
                    title: user.role || 'User',
                    email: user.email
                }));
                
                console.log('Search results:', displayUsers);
                resolve(displayUsers);
            }, 300);
        });

        displaySearchResults(results, query);
        return results;
    } catch (error) {
        console.error('Search error:', error);
    }
}

// Async function to create a post (combines async/await with promises)
async function createPost(postContent) {
    try {
        console.log('Creating post...');
        
        // Simulate validation
        const validated = await validatePostContent(postContent);
        
        // Simulate API call to save post
        const savedPost = await savePostToServer({
            content: postContent,
            timestamp: new Date(),
            likes: 0
        });

        console.log('Post created successfully:', savedPost);
        return savedPost;
    } catch (error) {
        console.error('Failed to create post:', error);
        showPostError(error.message);
    }
}

// Helper promise function for validation
function validatePostContent(content) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (content.trim().length === 0) {
                reject(new Error('Post content cannot be empty'));
            } else if (content.length > 5000) {
                reject(new Error('Post content exceeds maximum length'));
            } else {
                resolve(true);
            }
        }, 300);
    });
}

// Helper promise function to save post to server
function savePostToServer(postData) {
    return new Promise((resolve, reject) => {
        try {
            const currentUser = window.AppStorage.read(window.AppStorage.keys.user, null);
            
            if (!currentUser) {
                reject(new Error('User not logged in'));
                return;
            }

            // Create post object with generated ID and timestamp
            const newPost = {
                id: Math.random().toString(36).substr(2, 9),
                ...postData,
                author: currentUser.name,
                authorEmail: currentUser.email,
                createdAt: new Date().toISOString()
            };

            // Get existing posts from storage
            const posts = window.AppStorage.read(window.AppStorage.keys.posts, []);
            
            // Add new post to the beginning
            posts.unshift(newPost);
            
            // Save updated posts to storage
            window.AppStorage.write(window.AppStorage.keys.posts, posts);
            
            console.log('Post saved successfully:', newPost);
            resolve(newPost);
        } catch (error) {
            reject(new Error('Failed to save post: ' + error.message));
        }
    });
}

//DOM MANIPULATION AND EVENT LISTENERS
document.addEventListener('DOMContentLoaded', async () => {
    // Initialize theme manager
    window.themeManager = new ThemeManager();

    // Setup user search input with debounce
    const userSearchInput = document.getElementById('userSearchInput');
    if (userSearchInput) {
        userSearchInput.addEventListener('input', (e) => {
            debouncedSearch(e.target.value);
        });
    }

    // Setup post button
    const postBtn = document.getElementById('postBtn');
    if (postBtn) {
        postBtn.addEventListener('click', async () => {
            const postInput = document.getElementById('postInput');
            const content = postInput.value;
            
            try {
                const post = await createPost(content);
                if (post) {
                    postInput.value = '';
                    showPostSuccess('Post created successfully!');
                }
            } catch (error) {
                console.error('Error creating post:', error);
            }
        });
    }

    // Setup scroll listener with throttle
    window.addEventListener('scroll', throttledScroll);

    // Demo: Load user profile on page load
    // Uncomment to test: await loadUserProfile(1);
    // Demo: Load dashboard data in parallel
    // Uncomment to test: await loadDashboardData(1);
});

//HELPER FUNCTIONS
function displaySearchResults(users, query) {
    const searchResults = document.getElementById('userSearchResults');
    const userSearchList = document.getElementById('userSearchList');
    const userSearchEmpty = document.getElementById('userSearchEmpty');
    
    console.log('displaySearchResults called with:', { users, query });
    
    if (!searchResults || !userSearchList) {
        console.error('Search result elements not found');
        return;
    }
    
    // Clear previous results and messages
    userSearchList.innerHTML = '';
    if (userSearchEmpty) {
        userSearchEmpty.hidden = true;
    }
    
    // Only show results if there's a query
    if (!query || !query.trim()) {
        searchResults.hidden = true;
        return;
    }
    
    // Show results container
    searchResults.hidden = false;
    
    // Check if we have users to display
    if (users && users.length > 0) {
        users.forEach(user => {
            const li = document.createElement('li');
            li.className = 'search-result-item';
            li.innerHTML = `
                <div class="search-user-name">${user.name}</div>
                <div class="search-user-meta">${user.title} • ${user.email}</div>
            `;
            li.style.cursor = 'pointer';
            
            li.addEventListener('mouseenter', (e) => {
                e.target.closest('li').style.backgroundColor = '#f3f5f7';
            });
            li.addEventListener('mouseleave', (e) => {
                e.target.closest('li').style.backgroundColor = 'white';
            });
         
            
            userSearchList.appendChild(li);
        });
    } else {
        // Only show empty message if no users found
        if (userSearchEmpty) {
            userSearchEmpty.hidden = false;
            userSearchEmpty.textContent = `No results for "${query}"`;
        }
    }
}

function appendPostsToContainer(posts) {
    const container = document.getElementById('userPostsContainer');
    if (container) {
        posts.forEach(post => {
            const postDiv = document.createElement('div');
            postDiv.className = 'post box';
            postDiv.innerHTML = `
                <div class="post-author">
                    <h4>${post.author}</h4>
                </div>
                <div class="post-text">
                    <p>${post.content}</p>
                </div>
                <hr>
                <div class="post-buttons">
                    <button type="button">Like</button>
                    <button type="button">Comment</button>
                    <button type="button">Share</button>
                </div>
            `;
            container.appendChild(postDiv);
        });
    }
}

function showPostError(message) {
    const postStatus = document.getElementById('postStatus');
    if (postStatus) {
        postStatus.textContent = `Error: ${message}`;
        postStatus.style.color = '#d32f2f';
    }
}

function showPostSuccess(message) {
    const postStatus = document.getElementById('postStatus');
    if (postStatus) {
        postStatus.textContent = message;
        postStatus.style.color = '#388e3c';
        setTimeout(() => {
            postStatus.textContent = '';
        }, 3000);
    }
}
