package evidence_pojisteni.models.enums;

public enum InsuranceType {
    LIFE("Životní pojištění"),
    ACCIDENT("Úrazové pojištění"),
    VEHICLE("Pojištění vozidla"),
    PROPERTY("Pojištění majetku"),
    TRAVEL("Cestovní pojištění"),
    BUSINESS("Pojištění podnikání"),
    RESPONSIBILITY("Pojištění odpovědnosti");

    private final String description;

    InsuranceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
