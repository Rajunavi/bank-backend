package com.bufferzero.bank.repository;

import com.bufferzero.bank.entities.AccHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccHolderRepo extends JpaRepository<AccHolder, Integer> {
    public AccHolder findById(int id);



    @Query("SELECT a FROM AccHolder a WHERE CAST(a.accNo AS string) LIKE CONCAT('%', :accNo, '%')")
    List<AccHolder> findAccountBySearch(@Param("accNo") long accNo);
}
