package evidence_pojisteni.models.dto;

import evidence_pojisteni.models.enums.InsuranceType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InsuranceDTO {

    private long insuranceId;

    @NotNull
    private Long clientId;

    @NotNull
    private InsuranceType clientInsurance;

    @NotNull(message = "Zadejte částku k pojištění.")
    @Positive(message = "Částka musí být větší než 0.")
    private Long insuranceAmount;

    private LocalDate insuranceDate;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Long getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Long insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public InsuranceType getClientInsurance() {
        return clientInsurance;
    }

    public void setClientInsurance(InsuranceType clientInsurance) {
        this.clientInsurance = clientInsurance;
    }

    public LocalDate getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(LocalDate insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
