
const form = document.querySelector("form");
form.addEventListener("submit", function (e) {
    const pass1field = form.querySelector("input[name='password']");
    const pass2field = form.querySelector("input[name='submitPassword']")
    const pass1 = form.querySelector("input[name='password']").value;
    const pass2 = form.querySelector("input[name='submitPassword']").value;
    if (pass1 !== pass2) {
        e.preventDefault();

        document.getElementById("label-confPassword").style.color = 'red';
        pass1field.value = "";
        pass2field.value = "";

        pass1field.focus();
    }
});