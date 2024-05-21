$(function(){
    // Retrieve the ticket with the customer ID from the URL and display it in the form
    const id = window.location.search.substring(1); // Get the ID from the URL
    const url = "/hentEnBilett?"+id; // Create the URL to fetch the ticket
    $.get(url,function(bilett){
        // Populate the form fields with the retrieved ticket data
        $("#id").val(bilett.id); // Include the ID in the form, hidden in HTML
        $("#filmene").val(bilett.film);
        $("#antall").val(bilett.antall);
        $("#fornavn").val(bilett.fornavn);
        $("#etternavn").val(bilett.etternavn);
        $("#telefonnummer").val(bilett.telefonnummer);
        $("#epost").val(bilett.epost);
    });
});

$(function(){
    // Fetch the list of films when the page is ready
    hentFilmer();
});

function hentFilmer() {
    // Send a GET request to retrieve the list of films
    $.get("/hentFilmer", function(filmer) {
        // Format the film options and display them in the form
        formaterFilmer(filmer);
    });
}

function formaterFilmer(filmer) {
    // Create a dropdown (select) element with the list of films
    let ut = "<select id='valgtFilm'>";
    for (const film of filmer) {
        ut+="<option value='"+film.tittel+"'>"+film.tittel+"</option>";
    }
    ut+="</select>";
    // Insert the dropdown into the form
    $("#filmene").html(ut);
}


function endreBiletten() {
    // Create an object with the form data to send in the POST request
    const bilett = {
        id : $("#id").val(), // Include this to know which customer to update
        film: $("#filmene").val(),
        antall: $("#antall").val(),
        fornavn: $("#fornavn").val(),
        etternavn:  $("#etternavn").val(),
        telefonnummer:  $("#telefonnummer").val(),
        epost:  $("#epost").val()
    }
    // Send a POST request to update the ticket
    $.post("/endreBilett",bilett,function(){
        // Redirect to the index page after the update
        window.location.href = 'lagredeBiletter.html';
        alert("Billetten er endret!");
    });
}

