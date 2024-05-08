package com.bufferzero.bank.repository;

import com.bufferzero.bank.entities.AccHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccHolderRepo extends JpaRepository<AccHolder, Integer> {
    public AccHolder findById(int id);
}
