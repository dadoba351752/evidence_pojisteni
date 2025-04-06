package evidence_pojisteni.models.exceptions;

public class InsuranceNotFoundException extends RuntimeException {
    public InsuranceNotFoundException(String message) {
      super(message);
    }

    public InsuranceNotFoundException() {
      super("Pojištění nenalezeno.");
    }

}
