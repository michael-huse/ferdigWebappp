package oslo.met.ferdigwebappp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;



    @Repository
    public class kinoBilettRepo {
        @Autowired
        private JdbcTemplate db;

        private Logger logger = LoggerFactory.getLogger(kinoBilettRepo.class);


        // Method to save a ticket record to the database
        public boolean lagreBilett(Bilett nyBilett) {
            // SQL query to insert ticket data into the Bilett table
            String sql = "INSERT INTO Bilett(film, antall, fornavn, etternavn, telefonnummer, epost) VALUES(?,?,?,?,?,?)";

            try{
                // Execute the SQL query with ticket data as parameters
                db.update(sql,
                        nyBilett.getFilm(),
                        nyBilett.getAntall(),
                        nyBilett.getFornavn(),
                        nyBilett.getEtternavn(),
                        nyBilett.getTelefonnummer(),
                        nyBilett.getEpost());
                return true; // Return true if the operation is successful
            }catch (Exception e){
                // Log error message if an exception occurs during ticket insertion
                logger.error("Feil i lagreBilett : "+ e);
                return false; // Return false indicating failure
            }
        }

        // Method to retrieve all tickets from the database
        public List<Bilett> hentAlle() {
            // SQL query to select all ticket records from the Bilett table
            String sql = "SELECT * FROM Bilett ORDER BY etternavn ASC";
            try{
                // Execute the SQL query and map the result set to Bilett objects
                List<Bilett> alleBiletter = db.query(sql, new BeanPropertyRowMapper(Bilett.class));
                return alleBiletter;  // Return the list of tickets
            }
            catch (Exception e) {
                // Log error message if an exception occurs during ticket retrieval
                logger.error("Feil i hentAlleBiletter :" + e);
                return null; // Return null indicating failure
            }
        }

        // Method to delete all tickets from the database
        public boolean slettAlle() {
            // SQL query to delete all ticket records from the Bilett table
            String sql = "DELETE FROM Bilett";

            try {
                // Execute the SQL query to delete all tickets
                db.update(sql);
                return true;
                // Return true if the operation is successful
            }
            catch (Exception e) {
                // Log error message if an exception occurs during deletion
                logger.error("feil i slett alle: " + e);
                return false;// Return false indicating failure

            }
        }

        // Method to delete a ticket by its ID from the database
        public boolean slettEnBilett(int id) {
            // SQL query to delete a ticket record with the given ID from the Bilett table
            String sql = "DELETE FROM Bilett WHERE id=?";

            try {
                // Execute the SQL query with the ticket ID as a parameter
                db.update(sql, id);
                return true; // Return true if the operation is successful
            } catch (Exception e) {
                // Log error message if an exception occurs during deletion
                logger.error("Feil i slettEnBilett" + e);
                return false; // Return false indicating failure
            }
        }

        // Method to retrieve a single ticket by its ID from the database
        public Bilett hentEnBilett (int id){
            // SQL query to select a ticket record with the given ID from the Bilett table
            String sql = "SELECT * FROM Bilett WHERE id=?";
            try {
                // Execute the SQL query with the ticket ID as a parameter and map the result to a Bilett object
                Bilett enBilett = db.queryForObject(sql,BeanPropertyRowMapper.newInstance(Bilett.class),id);
                return enBilett; // Return the ticket object
            } catch (Exception e) {
                // Log error message if an exception occurs during retrieval
                logger.error("Feil i hentEnBilett: " + e);
                return null; // Return null indicating failure
            }
        }

        // Method to update a ticket record in the database
        public boolean endreBilett (Bilett bilett){
            // SQL query to update a ticket record in the Bilett table
            String sql = "UPDATE Bilett SET film=?,antall=?,fornavn=?,etternavn=?,telefonnummer=?, epost=? where id=?";

            try {
                // Execute the SQL query with ticket data as parameters
                db.update(sql,
                        bilett.getFilm(),
                        bilett.getAntall(),
                        bilett.getFornavn(),
                        bilett.getEtternavn(),
                        bilett.getTelefonnummer(),
                        bilett.getEpost(),
                        bilett.getId()
                );
                return true; // Return true if the operation is successful
            } catch (Exception e) {
                // Log error message if an exception occurs during update
                logger.error("Feil i endreBilett: " + e);
                return false; // Return false indicating failure
            }
        }




        // Method to save a customer record to the database
        public boolean lagreKunde(Kunde kunde) {
            // SQL query to insert customer data into the Kunde table
            String sql = "INSERT INTO Kunde (navn,adresse,passord) VALUES(?,?,?)";
            try{
                // Execute the SQL query with customer data as parameters
                db.update(sql,kunde.getNavn(),kunde.getAdresse(),kunde.getPassord());
                return true; // Return true if the operation is successful
            }
            catch(Exception e){
                // Log error message if an exception occurs during customer insertion
                logger.error("Feil i lagreKunde : "+e);
                return false; // Return false indicating failure
            }
        }

        // Method to retrieve all customers from the database
        public List<Kunde> hentAlleKunder() {
            // SQL query to select all customer records from the Kunde table
            String sql = "SELECT * FROM Kunde";
            try{
                // Execute the SQL query and map the result set to Kunde objects
                List<Kunde> alleKunder = db.query(sql,new BeanPropertyRowMapper(Kunde.class));
                return alleKunder;  // Return the list of customers
            }
            catch (Exception e){
                // Log error message if an exception occurs during customer retrieval
                logger.error("Feil i hentAlleKunder : "+e);
                return null;  // Return null indicating failure
            }
        }

        // Method to retrieve a single customer by their ID from the database
        public Kunde hentEnKunde(int id) {
            // SQL query to select a customer record with the given ID from the Kunde table
            String sql = "SELECT * FROM Kunde WHERE KundeNr=?";
            try{
                // Execute the SQL query with the customer ID as a parameter and map the result to a Kunde object
                Kunde enKunde = db.queryForObject(sql, new Object[]{id},BeanPropertyRowMapper.newInstance(Kunde.class));
                return enKunde; // Return the customer object
            }
            catch(Exception e) {
                // Log error message if an exception occurs during customer retrieval
                logger.error("Feil i hentEnKunde : " + e);
                return null; // Return null indicating failure
            }
        }

        // Method to update a customer record in the database
        public boolean endreEnKunde(Kunde kunde){
            // SQL query to update a customer record in the Kunde table
            String sql = "UPDATE Kunde SET navn=?,adresse=?,passord=? where KundeNr=?";
            try{
                // Execute the SQL query with customer data as parameters
                db.update(sql,kunde.getNavn(),kunde.getAdresse(),kunde.getPassord(),kunde.getKundeNr());
                return true; // Return true if the operation is successful
            }
            catch(Exception e){
                // Log error message if an exception occurs during update
                logger.error("Feil i endreEnKunde : "+e);
                return false; // Return false indicating failure
            }
        }

        // Method to delete a customer by their ID from the database
        public boolean slettEnKunde(int id) {
            // SQL query to delete a customer record with the given ID from the Kunde table
            String sql = "DELETE FROM Kunde WHERE KundeNr=?";
            try
            // Execute the SQL query with the customer ID as a parameter
            {db.update(sql,id);
                return true; // Return true if the operation is successful
            }
            catch(Exception e){
                // Log error message if an exception occurs during deletion
                logger.error("Feil i slettEnKunde : "+e);
                return false; // Return false indicating failure
            }
        }


        // Method to delete all customers from the database
        public boolean slettAlleKunder () {
            // SQL query to delete all customer records from the Kunde table
            String sql = "DELETE FROM Kunde";
            try {
                // Execute the SQL query to delete all customers
                db.update(sql);
                return true; // Return true if the operation is successful
            }
            catch(Exception e){
                // Log error message if an exception occurs during deletion
                logger.error("Feil i slettAlleKunder : "+e);
                return false; // Return false indicating failure
            }
        }

        // Method to check if a given customer name and password match an entry in the database
        public boolean sjekkNavnOgPassord (Kunde kunde) {
            // Create an array of parameters containing customer name and password
            Object[] param = new Object[]{kunde.getNavn(),kunde.getPassord()};
            // SQL query to count the number of matching records in the Kunde table
            String sql = "SELECT COUNT(*) FROM Kunde WHERE navn=? AND passord=?";
            try{
                // Execute the SQL query with parameters and retrieve the count
                int antall = db.queryForObject(sql,param,Integer.class);
                // Return true if the count is greater than 0 (indicating a match), otherwise return false
                if (antall>0){
                    return true;
                }
                return false;
            }
            catch (Exception e){
                // Log error message if an exception occurs during retrieval
                logger.error("Feil i sjekkNavnOgPassord : "+e);
                return false; // Return false indicating failure
            }
            }
        }








