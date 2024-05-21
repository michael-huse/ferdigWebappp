package oslo.met.ferdigwebappp;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


    @RestController
    public class BestillingController {

        // Redirect the root URL to the login page
        @GetMapping("/")
        public String home() {
            return "redirect:/login.html";
        }


        @Autowired // Autowire the repository for handling ticket operations henter bilettrepo
        kinoBilettRepo rep;

        // Save a ticket, send error if fails
        @PostMapping("/lagreBilett")
        public void lagreBilett(Bilett bilett, HttpServletResponse response) throws IOException {
            if(!rep.lagreBilett(bilett)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
        }

        // Fetch all tickets, send error if fails
        @GetMapping("/hentAlle")
        public List<Bilett> hentAlle(HttpServletResponse response) throws IOException {
            List<Bilett> alleBiletter = rep.hentAlle();
            if (alleBiletter==null) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
            return rep.hentAlle();
        }

        // Return a list of hardcoded movies
        @GetMapping("/hentFilmer")
        public List<Filmer> hentFilmer() {
            List<Filmer> listFilmer = new ArrayList<>();
            listFilmer.add(new Filmer("Titanic"));
            listFilmer.add(new Filmer("Eternal sunshine of the spotless mind"));
            listFilmer.add(new Filmer("The iron giant"));
            return listFilmer;

        }


        // Delete all tickets, send error if fails
        @GetMapping("/slettAlle")
        public void slettAlle(HttpServletResponse response) throws IOException{
            if(!rep.slettAlle()){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - Prøv igjen senere");
            }
        }

        // Fetch a single ticket by ID, send error if fails
        @GetMapping("/hentEnBilett")
        public Bilett hentEnBilett(int id, HttpServletResponse response) throws IOException{
            Bilett biletten = rep.hentEnBilett(id);
            if(biletten==null){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
            }
            return rep.hentEnBilett(id);
        }

        // Update a ticket, send error if fails
        @PostMapping("/endreBilett")
        public void endreBilett(Bilett bilett, HttpServletResponse response) throws IOException{
            if(!rep.endreBilett(bilett)){
                response.sendError((HttpStatus.INTERNAL_SERVER_ERROR.value()),"Feil i DB - Prøv igjen senere");
            }
        }

        // Delete a single ticket by ID, send error if fails
        @GetMapping("/slettEnBilett")
        public void slettEnBilett(int id, HttpServletResponse response) throws IOException {
            if (!rep.slettEnBilett(id)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
            }
        }

        // Logger for the controller
        Logger logger = LoggerFactory.getLogger(BestillingController.class);


        // Validate customer data
        private boolean validerKunde(Kunde kunde){
            String regexNavn = "[a-zA-ZæøåÆØÅ\\.\\ \\-]{2,20}";
            String regexAdresse = "[0-9a-zA-ZæøåÆØÅ\\ \\.\\-]{2,50}";
            String regexPassord = "(?=.*[a-zA-ZæøåÆØÅ])(?=.*\\d)[a-zA-ZæøåÆØÅ\\d]{8,}"; // minimum 8 tegn, en bokstav og et tall
            boolean navnOK = kunde.getNavn().matches(regexNavn);
            boolean adresseOK = kunde.getAdresse().matches(regexAdresse);
            boolean passordOK = kunde.getPassord().matches(regexPassord);
            if (navnOK && adresseOK && passordOK){
                return true;
            }
            logger.error("Valideringsfeil");
            return false;
        }
        // Save a customer, send error if validation or save fails
        @PostMapping("/lagreKunde")
        public void lagreKunde(Kunde kunde, HttpServletResponse response) throws IOException {
            if(!validerKunde(kunde)){
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
            }
            else {
                if(!rep.lagreKunde(kunde)) {
                    response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
                }
            }
        }

        // Fetch all customers if logged in, send error if fails
        @GetMapping("/hentKunder")
        public List<Kunde> hentAlleKunder (HttpServletResponse response) throws IOException {
            List<Kunde> alleKunder = new ArrayList<>();
            if(session.getAttribute("Innlogget")!=null){
                alleKunder = rep.hentAlleKunder();
                if(alleKunder==null) {
                    response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
                }
                return alleKunder;
            }
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }

        // Fetch a single customer by ID, send error if fails
        @GetMapping("/hentEnKunde")
        public Kunde hentEnKunde(int id, HttpServletResponse response) throws IOException {
            Kunde kunden = rep.hentEnKunde(id);
            if(kunden == null){
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
            return kunden;
        }

        // Update a customer, send error if validation or update fails
        @PostMapping("/endreEnKunde")
        public void endreEnKunde(Kunde kunde, HttpServletResponse response) throws IOException{
            if(!validerKunde(kunde)) {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
            }
            else {
                if(!rep.endreEnKunde(kunde)){
                    response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
                }
            }
        }

        // Delete a single customer by ID, send error if fails
        @GetMapping("/slettEnKunde")
        public void slettEnKunde(int id,HttpServletResponse response) throws IOException{
            if(!rep.slettEnKunde(id)){
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
        }

        // Delete all customers, send error if fails
        @GetMapping("/slettAlleKunder")
        public void slettAlleKunder(HttpServletResponse response) throws IOException{
            if(!rep.slettAlleKunder()){
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
        }

        @Autowired private HttpSession session;
        /* Automatically injects the current HTTP session,
           allowing us to manage session attributes such as storing user login state
           and accessing session data across different methods in the controller. */


        // Log in a customer, setting session attribute if successful
        @GetMapping("/login")
        public boolean login(Kunde kunde) {

            if(rep.sjekkNavnOgPassord(kunde)){
                session.setAttribute("Innlogget",kunde);
                return true;
            }
            return false;
        }

        // Log out the customer, removing session attribute
        @GetMapping("/logout")
        public void logout() {
            session.removeAttribute("Innlogget");
        }
    }




