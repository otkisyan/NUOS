function validateForm() {
    const startValue = parseFloat(document.getElementById("tabulationForm:startValue_input").value);
    const endValue = parseFloat(document.getElementById("tabulationForm:endValue_input").value);
    const stepValue = parseFloat(document.getElementById("tabulationForm:stepValue_input").value);

    if (endValue < startValue) {
        alert("End value cannot be less than start Value.");
        return false;
    }

    if (stepValue === 0) {
        alert("Step value cannot be zero.");
        return false;
    }

    return true;
}