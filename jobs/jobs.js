const jobSearchInput = document.getElementById("jobSearchInput");
const jobCards = Array.from(document.querySelectorAll(".job-card"));

function filterJobCards(query) {
    const normalizedQuery = query.trim().toLowerCase();

    jobCards.forEach(function (card) {
        const cardText = card.textContent.toLowerCase();
        const shouldShow = !normalizedQuery || cardText.includes(normalizedQuery);
        card.style.display = shouldShow ? "block" : "none";
    });
}

jobSearchInput.addEventListener("input", function (event) {
    filterJobCards(event.target.value);
});

    