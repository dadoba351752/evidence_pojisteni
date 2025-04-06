package evidence_pojisteni.models.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ClientDTO {

    @NotBlank(message = "Vyplňte jméno.")
    @Pattern(
            regexp = "^[A-Ža-ž\\s\\-]+$",
            message = "Zadejte platné jméno."
    )
    private String name;

    @NotBlank(message = "Vyplňte příjmení.")
    @Pattern(
            regexp = "^[A-Ža-ž\\s\\-]+$",
            message = "Zadejte platné příjmení."
    )
    private String surname;

    @NotBlank(message = "Vyplňte telefonní číslo.")
    @Pattern(
            regexp = "^(\\+420 ?)?\\d{3} ?\\d{3} ?\\d{3}$",
            message = "Zadejte platné české telefonní číslo"
    )
    private String phoneNumber;

    @NotBlank(message = "Vyplňte email.")
    @Email(message = "Zadejte platný email.")
    private String email;

    @NotBlank(message = "Vyplňte ulici.")
    @Pattern(
            regexp = "^[A-Ža-ž0-9\\s\\.\\-]+$",
            message = "Zadejte platnou ulici."
    )
    private String street;

    @NotBlank(message = "Vyplňte číslo popisné.")
    @Pattern(
            regexp = "^\\d+[A-Za-z]?(\\/\\d+[A-Za-z]?)?$",
            message = "Zadejte platné číslo popisné."
    )
    private String houseNumber;

    @NotBlank(message = "Vyplňte město.")
    @Pattern(
            regexp = "^[A-Ža-ž\\s\\-]+$",
            message = "Zadejte platný název města."
    )
    private String city;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Datum narození musí být v minulosti.")
    @NotNull(message = "Vyplňte datum narození.")
    private LocalDate birthDate;

    private long clientId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
