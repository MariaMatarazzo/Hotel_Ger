package com.ger_Hotel.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ger_Hotel.entities.Quarto;
import com.ger_Hotel.repository.QuartoRepository;

@Service
public class QuartoService {
	private final QuartoRepository quartoRepository;


	    @Autowired
	    public QuartoService(QuartoRepository quartoRepository) {
	        this.quartoRepository = quartoRepository;
	    }

	    public List<Quarto> getAllQuartos() {
	        return quartoRepository.findAll();
	    }

	    public Quarto getQuartoById(Long id) {
	        return quartoRepository.findById(id).orElse(null);
	    }

	    public Quarto salvarQuarto(Quarto quarto) {
	        return quartoRepository.save(quarto);
	    }

	    public Quarto updateQuarto(Long id, Quarto updatedQuarto) {
	        Optional<Quarto> existente = quartoRepository.findById(id);
	        if (existente.isPresent()) {
	            updatedQuarto.setId(id);
	            return quartoRepository.save(updatedQuarto);
	        }
	        return null;
	    }

	    public boolean deleteQuarto(Long id) {
	        if (quartoRepository.existsById(id)) {
	            quartoRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	}



