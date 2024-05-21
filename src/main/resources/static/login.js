// Function to validate inputs and initiate the login process if validation is successful
function validerOgLogin(){
    // Validate the username and password inputs
    const navnOK = validerNavn($("#navn").val());
    const passordOK = validerPassord($("#passord").val());
    // If both inputs are valid, call the login function
    if (navnOK && passordOK){
        login();
    }
}

// Function to log out the user
function logout(){
    const url = "/logout";  // URL endpoint for logging out
    $.get( url, function() {
        // On successful logout, redirect to the login page
            window.location.href = 'login.html';
    })
}
// Function to handle the login process
function login(){
    // Create an object with the username and password from the input fields
    const kunde = {
        navn : $("#navn").val(),
        passord : $("#passord").val()
    }

    const url = "/login"; // URL endpoint for login
    $.get( url, kunde, function(innlogget) {
        // If the login is successful (server returns true), redirect to the ticket registration page
        if(innlogget){
            window.location.href = 'billettRegistrering.html';
        }
        else{
            $("#feil").html("Feil brukernavn eller passord");
            // If login fails, display an error message
        }
    })
    .fail(function() {
        // If there is a server error, display a server error message
            $("#feil").html("Serverfeil- prøv igjen senere");
        }
    );
}