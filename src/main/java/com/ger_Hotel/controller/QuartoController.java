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

import com.ger_Hotel.entities.Quarto;
import com.ger_Hotel.service.QuartoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quarto")
public class QuartoController {

    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscarQuartoPorId(@PathVariable Long id) {
        Quarto quarto = quartoService.getQuartoById(id);
        return quarto != null ? ResponseEntity.ok(quarto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> listarTodosQuartos() {
        return ResponseEntity.ok(quartoService.getAllQuartos());
    }

    @PostMapping
    public ResponseEntity<Quarto> salvarQuarto(@RequestBody @Valid Quarto quarto) {
        Quarto salvo = quartoService.salvarQuarto(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizarQuarto(@PathVariable Long id, @RequestBody Quarto quarto) {
        Quarto atualizado = quartoService.updateQuarto(id, quarto);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuarto(@PathVariable Long id) {
        return quartoService.deleteQuarto(id) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.notFound().build();
    }
}
