package evidence_pojisteni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EvidencePojisteniApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvidencePojisteniApplication.class, args);
    }
}
