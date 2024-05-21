// When the document is ready, execute the hentFilmer function
$(function(){
    hentFilmer();
});

// Function to fetch films from the server
function hentFilmer() {
    // Send a GET request to the /hentFilmer endpoint
    $.get("/hentFilmer", function(filmer) {
        formaterFilmer(filmer);
    });
}

// Function to format the list of films into a dropdown
function formaterFilmer(filmer) {
    // Initialize the dropdown HTML
    let ut = "<select id='valgtFilm'>";
    // Loop through each film and create an option element
    for (const film of filmer) {
        ut+="<option value='"+film.tittel+"'>"+film.tittel+"</option>";
    }
    // Close the select element
    ut+="</select>";
    // Insert the dropdown into the element with id 'filmene'
    $("#filmene").html(ut);
}

// Initialize variables to store validated input values
let utFornavn="";
let utEtternavn="";
let utEpost="";
let utTlf="";

// Regular expressions for validating input fields
const regexNavn = /^(?=.*[a-zA-Z])\S+$/;
const regexEpost = /^(?=.*[a-zA-Z0-9_\-\.])\S+@\S+\.\S{2,5}$/;
const regexNummer = /^(?=.*[4|9]\d{7})((0047)?|(\+47)?)[4|9]\d{7}$/;


// Function to validate first name input
function inputValideringfornavn() {

    let fornavninput=$("#fornavn").val(); // Get the value of the first name input
// Check if the input matches the regex for names
    if (regexNavn.test(fornavninput)) {
        utFornavn=fornavninput; // Store the valid input
        document.getElementById('fornavnValidering').innerText=""; // Clear validation message
    } else {
        utFornavn="feil"; // Mark the input as invalid
        document.getElementById('fornavnValidering').innerText="Du må skrive inn et fornavn"; // Show validation message
    }
}

function inputValideringetternavn() {

    let etternavninput=$("#etternavn").val();

    if (regexNavn.test(etternavninput)){
        utEtternavn=etternavninput;
        document.getElementById('etternavnValidering').innerText="";
    } else {
        utEtternavn="feil";
        document.getElementById('etternavnValidering').innerText="Du må skrive inn et etternavn";
    }
}


function inputValideringTelefonnummer() {

    let tlfinput=$("#telefonnummer").val();

    if (regexNummer.test(tlfinput)){
        utTlf=tlfinput;
        document.getElementById('tlfValidering').innerText="";
    } else {
        utTlf="feil";
        document.getElementById('tlfValidering').innerText="Du må skrive inn et telefonnummer";
    }
}

function inputValideringEpost() {

    let epostInput=$("#epost").val();

    if (regexEpost.test(epostInput)){
        utEpost=epostInput;
        document.getElementById('epostValidering').innerText="";
    } else {
        utEpost="feil";
        document.getElementById('epostValidering').innerText="Du må skrive inn en epost";
    }
}


//Function to save the ticket information
function lagre() {
// Check if any required fields are empty
    if ($("#valgtFilm").val() === "" || $("#antall").val() === "" || utFornavn === "" || utEtternavn === "" || utTlf === "" || utEpost === "") {
        alert("Alle felt må være utfylt");
        // Stop the function execution
    }
    // Check if all validated fields are correct
    if (utFornavn!=="feil" && utEtternavn!=="feil" && utTlf!=="feil" && utEpost!=="feil") {
    // Create a ticket object with the form values
        const bilett = {
            film: $("#valgtFilm").val(),
            antall: $("#antall").val(),
            fornavn: utFornavn,
            etternavn: utEtternavn,
            telefonnummer: utTlf,
            epost: utEpost
        };
        // Send a POST request to save the ticket
        $.post("/lagreBilett",bilett,function() {
            alert("Bilett lagret!");
            // Callback function can be used to handle the response
        })
        // Clear the form inputs
        $("#valgtFilm").val("");
        $("#antall").val("");
        $("#fornavn").val(""); //tøm input-feltene
        $("#etternavn").val("");
        $("#telefonnummer").val("");
        $("#epost").val("");

    } else {
        alert("Something wrong"); // Alert if validation fails

    }


}
