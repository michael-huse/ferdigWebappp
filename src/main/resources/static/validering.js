// Function to validate the name input
function validerNavn(navn){
    // Regular expression to match names with 2 to 20 characters (letters, spaces, dots, hyphens, and Norwegian letters)
    const regexp = /[a-zA-ZæøåÆØÅ\.\ \-]{2,20}/;
    // Test the name against the regular expression
    const ok = regexp.test(navn);
    // If the name does not match the regular expression
    if(!ok){
        // Display an error message in the "feilNavn" span
        $("#feilNavn").html("Navnet må bestå av 2 til 20 bokstaver");
        return false; // Return false indicating the validation failed
    }
    else{
        // Clear any error message in the "feilNavn" span
        $("#feilNavn").html("");
        return true;  // Return true indicating the validation passed
    }
}


function validerAdresse(adresse){
    var regexp = /[0-9a-zA-ZæøåÆØÅ\ \.\-]{2,50}/;
    var ok = regexp.test(adresse);
    if(!ok){
        $("#feilAdresse").html("Adressen må bestå av 2 til 20 bokstaver og tall");
        return false;
    }
    else{
        $("#feilAdresse").html("");
        return true;
    }
}


function validerPassord(passord){
    var regexp = /(?=.*[a-zA-ZæøåÆØÅ])(?=.*\d)[a-zA-ZæøåÆØÅ\d]{8,}/;
    var ok = regexp.test(passord);
    if(!ok){
        $("#feilPassord").html("Passordet må være minimum 8 tegn, et av de en bokstav og et tall");
        return false;
    }
    else{
        $("#feilPassord").html("");
        return true;
    }
}