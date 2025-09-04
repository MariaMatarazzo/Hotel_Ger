package com.ger_Hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ger_Hotel.entities.Hospede;
import com.ger_Hotel.repository.HospedeRepository;

@Service
public class HospedeService {
	 private final HospedeRepository hospedeRepository;

	    @Autowired
	    public HospedeService(HospedeRepository hospedeRepository) {
	        this.hospedeRepository = hospedeRepository;
	    }

	    public List<Hospede> getAllHospedes() {
	        return hospedeRepository.findAll();
	    }

	    public Hospede getHospedeById(Long id) {
	        return hospedeRepository.findById(id).orElse(null);
	    }

	    public Hospede salvarHospede(Hospede hospede) {
	        return hospedeRepository.save(hospede);
	    }

	    public Hospede updateHospede(Long id, Hospede updatedHospede) {
	        Optional<Hospede> existente = hospedeRepository.findById(id);
	        if (existente.isPresent()) {
	            updatedHospede.setId(id);
	            return hospedeRepository.save(updatedHospede);
	        }
	        return null;
	    }

	    public boolean deleteHospede(Long id) {
	        if (hospedeRepository.existsById(id)) {
	            hospedeRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	}