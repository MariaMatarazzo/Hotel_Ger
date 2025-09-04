package com.ger_Hotel.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ger_Hotel.entities.Reserva;
import com.ger_Hotel.repository.ReservaRepository;

@Service

public class ReservaService {
	private final ReservaRepository reservaRepository;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva salvarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long id, Reserva updatedReserva) {
        Optional<Reserva> existente = reservaRepository.findById(id);
        if (existente.isPresent()) {
            updatedReserva.setId(id);
            return reservaRepository.save(updatedReserva);
        }
        return null;
    }

    public boolean deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}