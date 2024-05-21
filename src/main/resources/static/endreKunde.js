$(function(){
    // Fetch the customer ID from the URL and display the customer data in the form
    const id = window.location.search.substring(1); //Extract the customer ID from the URL query string
    const url = "/hentEnKunde?"+id; // Construct the URL to fetch the customer data

    // Perform a GET request to fetch the customer data
    $.get(url,function(kunde){
        // Populate the form fields with the fetched customer data
        $("#id").val(kunde.id); // Set the hidden ID field
        $("#navn").val(kunde.navn); // Set the name field
        $("#adresse").val(kunde.adresse);
        $("#passord").val(kunde.passord);
    })
    .fail(function() {
        // Display an error message if the request fails
        $("#feil").html("Feil i db - prøv igjen senere");
    });
});

function validerOgEndreKunden(){
    // Validate the form fields
    const navnOK = validerNavn($("#navn").val()); // Validate the name field
    const adresseOK = validerAdresse($("#adresse").val());
    const passordOK = validerPassord($("#passord").val());
    // If all fields are valid, proceed to update the customer
    if (navnOK && adresseOK && passordOK){
        endreKunden(); // Call the function to update the customer
        return true;
    }
    return false;    // If validation fails, return false
}

function endreKunden() {
    // Create an object with the customer data to be updated
    const kunde = {
        id : $("#id").val(), // Include the customer ID to identify which customer to update
        navn : $("#navn").val(), // Include the updated name
        adresse : $("#adresse").val(),
        passord : $("#passord").val()
    }
    // Perform a POST request to update the customer data
    $.post("/endreEnKunde",kunde,function(){
        // Redirect to the ticket registration page upon successful update
        window.location.href = 'lagredeBiletter.html';
    })
    .fail(function(status) {
        // Display an error message if the request fails
        if(status.status==422){
            // Specific error message for status 422
            $("#feil").html("Feil i db - prøv igjen senere");
        }
        else{
            // General validation error message
            $("#feil").html("Valideringsfeil - prøv igjen senere");
        }
    });
}

