package com.example.calculatrice.repository;

import com.example.calculatrice.model.StackRpn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StackRpnRepository extends CrudRepository<StackRpn, Long> {

    List<StackRpn> findAll();

}
