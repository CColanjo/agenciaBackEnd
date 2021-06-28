package com.colanjo.agencia.repository;

import com.colanjo.agencia.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRespository extends JpaRepository<Paciente, Integer> {
}
