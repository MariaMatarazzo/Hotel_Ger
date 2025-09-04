package com.ger_Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ger_Hotel.entities.Reserva;

public interface ReservaRepository  extends JpaRepository<Reserva, Long> {

}
