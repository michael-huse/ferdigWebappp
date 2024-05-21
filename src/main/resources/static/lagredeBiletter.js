// jQuery function to run when the document is ready
$(function(){
    hentAlle(); // Call hentAlle function to fetch and display all tickets
});

// Function to fetch all tickets from the server
function hentAlle() {
    $.get("/hentAlle", function(data) { // Send a GET request to the "/hentAlle" endpoint
        formaterData(data); // Call formaterData function to format and display the received data
    })

        .fail(function(jqXHR) { // Handle any errors from the GET request
            const json = $.parseJSON(jqXHR.responseText); // Parse the error response
            $("#feil").html(json.message); // Display the error message in the "feil" div
        });
}

// Function to format and display the received ticket data
function formaterData(biletter){
    let ut = "<table class='table table-striped'>" + // Start of the HTML table
        "<tr>" +  // Table header row
        "<th>Film</th><th>Antall</th>" + // Table headers
        "<th>Fornavn</th>" + "<th>Etternavn</th>" +
        "<th>Telefonnummer</th>" + "<th>Epost</th>";

    // Loop through each ticket and create a row in the table
    for (let i in biletter) {
        ut += "<tr>" +
            "<td>" + biletter[i].film + "</td>" +  // Table data cell for Film
            "<td>" + biletter[i].antall + "</td>" +
            "<td>" + biletter[i].fornavn + "</td>" +
            "<td>" + biletter[i].etternavn + "</td>" +
            "<td>" + biletter[i].telefonnummer + "</td>" +
            "<td>" + biletter[i].epost + "</td>" +
            // Edit button with a link to edit the ticket
            "<td> <a class='btn btn-primary' href='endreBilett2.html?id=" + biletter[i].id + "'> Endre </a> </td>" +
            // Delete button with an onclick event to call slettEnBilett function
            "<td> <button class='btn btn-danger' onclick='slettEnBilett(" + biletter[i].id + ")'>Slett</button> </td>" +
            "</tr>"; // End of the table row
    }
    $("#biletter").html(ut); // Set the HTML content of the "biletter" div to the table
}




function slettEnBilett(id) {
    const url="/slettEnBilett?id="+id; // Construct the URL with the ticket ID
    $.get(url,function () { // Send a GET request to the constructed URL
        window.location.href = 'lagredeBiletter.html'; // Reload the page to reflect the changes
        alert("Biletten er slettet!");  // Show an alert confirming the deletion
    })
        .fail(function(jqXHR) { // Handle any errors from the GET request
            const json = $.parseJSON(jqXHR.responseText); // Parse the error response
            $("#feil").html(json.message); // Display the error message in the "feil" div
        });
}

function slettAlle() {
    const ok = confirm("Sikker på å slette alle?");  // Confirm the deletion with the user
    if (ok) { // If the user confirms
        $.get("/slettAlle", function () { // Send a GET request to delete all tickets
            hentAlle(); // Fetch and display the updated list of tickets
        })
            .fail(function (jqXHR) { // Handle any errors from the GET request
                const json = $.parseJSON(jqXHR.responseText);  // Parse the error response
                $("#feil").html(json.message);  // Display the error message in the "feil" div
            });
    }

}