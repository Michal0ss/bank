document.addEventListener('DOMContentLoaded', function()  {
    let CODER_TTL_SECONDS = 30;
    let currentCode = "";
    let secondsLeft = CODER_TTL_SECONDS;
    let codeElement = document.getElementById("blikCode");
    let timerElement = document.getElementById("blikTimer");
    let copyElement = document.getElementById("copyBlikBtn");
    let statusElement = document.getElementById("blikCopyStatus");

    if (!codeElement || !timerElement || !copyElement || !statusElement) {
        return;
    }

    function randomSix() {
        return String(Math.floor(Math.random() * 1000000)).padStart(6, "0");
    }

    function renderTimer(){
        timerElement.textContent = "00:" + String(secondsLeft).padStart(2, "0");
    }

    function setNewCode(){

        currentCode = randomSix();
        codeElement.textContent = currentCode;
        codeElement.setAttribute("data-code", currentCode);
        secondsLeft = CODER_TTL_SECONDS;
        renderTimer();
    }

    function tick(){
        //     Uruchamiana co 1 sekundę.
        // Zmniejsza licznik o 1, aktualizuje timer, a gdy dojdzie do 0 — wywołuje setNewCode().
        secondsLeft -=1;
        if (secondsLeft <= 0) {
            codeElement.textContent = "------";
            setTimeout(function(){setNewCode();},150);
            return;
        }
        renderTimer();
    }

    function setStatus(messsage){
    //     Pokazuje komunikat użytkownikowi pod przyciskiem kopiowania (Skopiowano / Nie udało się...).
        // Czyści komunikat po krótkim czasie.
        statusElement.textContent = messsage;
        setTimeout(function(){if (statusEl.textContent === msg) {
            statusEl.textContent = "";}},1500);

    }

    copyElement.addEventListener("click", function(){
        if (!currentCode) return;


        if (navigator.clipboard && navigator.clipboard.writeText) {
            navigator.clipboard.writeText(currentCode)
                .then(function () { setStatus("Skopiowano"); })
                .catch(function () { setStatus("Nie udalo sie skopiowac"); });
        } else {
            var temp = document.createElement("input");
            temp.value = currentCode;
            document.body.appendChild(temp);
            temp.select();
            try {
                document.execCommand("copy");
                setStatus("Skopiowano");
            } catch (e) {
                setStatus("Nie udalo sie skopiowac");
            }
            document.body.removeChild(temp);
        }
    });
    setNewCode();
    setInterval(tick,1000);

});