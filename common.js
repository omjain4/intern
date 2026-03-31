const STORAGE_RESET_FLAG = "linkdin_storage_reset_once";

if (!sessionStorage.getItem(STORAGE_RESET_FLAG)) {
    localStorage.clear();
    sessionStorage.setItem(STORAGE_RESET_FLAG, "true");
}

window.AppStorage = {
    keys: {
        user: "linkdinuser",
        users: "linkdinusers",
        posts: "linkdinposts"
    },

    read: function (key, fallbackValue) {
        const raw = localStorage.getItem(key);
        if (!raw) {
            return fallbackValue;
        }
        return JSON.parse(raw);
    },

    write: function (key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    },

    remove: function (key) {
        localStorage.removeItem(key);
    }
};
