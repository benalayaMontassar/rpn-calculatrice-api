package com.example.calculatrice.repository;

import com.example.calculatrice.model.Operand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperandRepository extends CrudRepository<Operand, String> {
    List<Operand> findAll();

}
