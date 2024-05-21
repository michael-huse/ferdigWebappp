// Function to validate inputs and save customer if all validations pass
function validerOgLagreKunde(){
    // Validate name input and store the result
    const navnOK = validerNavn($("#navn").val());
    // Validate address input and store the result
    const adresseOK = validerAdresse($("#adresse").val());
    const passordOK = validerPassord($("#passord").val());
    // If all validations pass, call the function to save the customer
    if (navnOK && adresseOK && passordOK){
        lagreKunde();
     }
}


// Function to save the customer
function lagreKunde() {
    // Create a customer object with values from the form inputs
    const kunde = {
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
        passord : $("#passord").val()
    }
    const url = "/lagreKunde"; // URL for the POST request

    // Send a POST request to save the customer
    $.post( url, kunde, function() {
        // On success, redirect to the 'billettRegistrering.html' page
        window.location.href = 'billettRegistrering.html';
    })
    .fail(function(status) { // Handle any errors from the POST request
        // If the status code is 422, display a database error message
        if(status.status==422){
            $("#feil").html("Feil i db - prøv igjen senere"); // Error message for database issues
        }
        // Otherwise, display a validation error message
        else{
            $("#feil").html("Valideringsfeil - prøv igjen senere"); // Error message for validation issues
        }
    });
};
