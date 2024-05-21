package oslo.met.ferdigwebappp;

public class Filmer {

        private String tittel;

    /*
     * Parameterized constructor
     * Allows for the creation of a Bilett object with all fields set.
     * Useful for testing and manual object creation in code.
     */
        public Filmer(String tittel) {
            this.tittel = tittel;
        }

    /*
     * No-argument constructor
     * Required by frameworks like Spring and libraries like JPA/Hibernate
     * for object creation and deserialization of JSON data into Java objects.
     */

    public Filmer() {

        }

        public String getTittel() {
            return tittel;
        }

        public void setTittel(String tittel) {
            this.tittel = tittel;
        }
    }

