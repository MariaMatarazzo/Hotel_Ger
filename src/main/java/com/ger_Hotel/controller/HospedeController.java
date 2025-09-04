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

import com.ger_Hotel.entities.Hospede;
import com.ger_Hotel.service.HospedeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospede")
public class HospedeController {

    private final HospedeService hospedeService;

    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> buscarHospedePorId(@PathVariable Long id) {
        Hospede hospede = hospedeService.getHospedeById(id);
        return hospede != null ? ResponseEntity.ok(hospede) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Hospede>> listarTodosHospedes() {
        return ResponseEntity.ok(hospedeService.getAllHospedes());
    }

    @PostMapping
    public ResponseEntity<Hospede> salvarHospede(@RequestBody @Valid Hospede hospede) {
        Hospede salvo = hospedeService.salvarHospede(hospede);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospede> atualizarHospede(@PathVariable Long id, @RequestBody Hospede hospede) {
        Hospede atualizado = hospedeService.updateHospede(id, hospede);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHospede(@PathVariable Long id) {
        return hospedeService.deleteHospede(id) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.notFound().build();
    }
}
