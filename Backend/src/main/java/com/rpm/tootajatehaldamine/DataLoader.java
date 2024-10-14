package com.rpm.tootajatehaldamine;

import com.rpm.tootajatehaldamine.model.Tootaja;
import com.rpm.tootajatehaldamine.repository.TootajadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner { // populates db with dummy data
    private final TootajadRepository repository;

    @Override
    public void run(String... args) {
        int NUMBER_OF_FIELDS = 102;  // change to amount of fields wanted

        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            Tootaja tootaja = new Tootaja();
            tootaja.setNimi("Nimi Perenimi");
            tootaja.setEmail(String.format("email%d@gmail.com", i+1));
            tootaja.setTelefon("+37255667788");
            tootaja.setElukoht(String.format("Jahu %d, Tallinn", i+1));
            tootaja.setLisatud(LocalDateTime.now());
            tootaja.setMuudetud(LocalDateTime.now());
            repository.save(tootaja);
        }
    }
}
