package com.rpm.tootajatehaldamine.service;

import com.rpm.tootajatehaldamine.model.Tootaja;
import com.rpm.tootajatehaldamine.repository.TootajadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DatabaseService {
    private final TootajadRepository tootajadRepository;

    @Transactional(readOnly = true)
    public Page<Tootaja> getTootajad(Pageable pageable) {
        return tootajadRepository.findAll(pageable);
    }

    public void addTootaja(Tootaja request) {
        Tootaja tootaja = new Tootaja();
        tootaja.setNimi(request.getNimi());
        tootaja.setEmail(request.getEmail());
        tootaja.setTelefon(request.getTelefon());
        tootaja.setElukoht(request.getElukoht());
        tootaja.setLisatud(LocalDateTime.now());
        tootaja.setMuudetud(LocalDateTime.now());

        tootajadRepository.save(tootaja);
    }

    public void editTootaja(Tootaja request) {
        Optional<Tootaja> tootajaOptional = tootajadRepository.findById(request.getId());
        if (tootajaOptional.isPresent()) {
            Tootaja tootaja = tootajaOptional.get();
            tootaja.setNimi(request.getNimi());
            tootaja.setEmail(request.getEmail());
            tootaja.setTelefon(request.getTelefon());
            tootaja.setElukoht(request.getElukoht());
            tootaja.setMuudetud(LocalDateTime.now());

            tootajadRepository.save(tootaja);
        }
    }

    public void deleteTootaja(Long id) {
        tootajadRepository.deleteById(id);
    }
}
