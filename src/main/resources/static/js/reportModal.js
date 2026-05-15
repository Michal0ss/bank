function openReportModal() {
    document.getElementById("reportModal").style.display = "flex";
}

function closeReportModal() {
    document.getElementById("reportModal").style.display = "none";
}

window.addEventListener("click", function(event) {
    const modal = document.getElementById("reportModal");
    if (event.target === modal) {
        closeReportModal();
    }
});