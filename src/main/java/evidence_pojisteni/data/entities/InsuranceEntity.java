package evidence_pojisteni.data.entities;

import evidence_pojisteni.models.enums.InsuranceType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long insuranceId;

    @ManyToOne
    private ClientEntity client;

    @Column
    @Enumerated(EnumType.STRING)
    private InsuranceType clientInsurance;

    @Column
    private Long insuranceAmount;

    @Column
    private LocalDate insuranceDate;

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDate getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(LocalDate insuranceDate) {
        this.insuranceDate = insuranceDate;
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

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }
}
