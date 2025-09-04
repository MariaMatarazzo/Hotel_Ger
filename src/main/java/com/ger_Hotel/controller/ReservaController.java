package com.ger_Hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ger_Hotel.entities.Reserva;
import com.ger_Hotel.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodasReservas() {
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @PostMapping
    public ResponseEntity<Reserva> salvarReserva(@RequestBody @Valid Reserva reserva) {
        Reserva salva = reservaService.salvarReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva atualizada = reservaService.updateReserva(id, reserva);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        return reservaService.deleteReserva(id) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.notFound().build();
    }
}