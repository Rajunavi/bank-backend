package com.bufferzero.bank.services;

import com.bufferzero.bank.auto.Message;
import com.bufferzero.bank.entities.AccHolder;
import com.bufferzero.bank.exceptions.AccountNotFoundException;
import com.bufferzero.bank.repository.AccHolderRepo;
import com.bufferzero.bank.requests.AccHolderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccHolderService {
    @Autowired
    private AccHolderRepo accHolderRepo;

    public ResponseEntity<Message> addAccount(AccHolderRequest accHolderRequest){
        AccHolder accHolder = new AccHolder(accHolderRequest.getName(),accHolderRequest.getMobileNo(),accHolderRequest.getAccBalance(), accHolderRequest.getEmail());
        accHolderRepo.save(accHolder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Message("Account Created Successfully"));
    }

    public List<AccHolder> getAllAccounts(){
        return accHolderRepo.findAll();
    }

    public AccHolder getAccoutDetails(int id) throws Exception {
        AccHolder acc = accHolderRepo.findById(id);
        if(acc != null){
        return acc;
        } else {
            throw new Exception("Account Not Found");
        }
    }

    public ResponseEntity<Message> deleteAccount(int id) throws AccountNotFoundException {
        AccHolder acc = accHolderRepo.findById(id);
        if(acc != null){
            accHolderRepo.deleteById(id);
            return ResponseEntity.ok(new Message("Account deleted"));
        } else {
            throw new AccountNotFoundException("Account Not Found");
        }
    }

    public ResponseEntity<AccHolder> updateAccount(int id,AccHolderRequest accHolderRequest) throws AccountNotFoundException {
        AccHolder acc = accHolderRepo.findById(id);
        if(acc != null){
            acc.setAccBalance(accHolderRequest.getAccBalance());
            acc.setName(accHolderRequest.getName());
            acc.setMobileNo(accHolderRequest.getMobileNo());
            accHolderRepo.save(acc);
            return ResponseEntity.ok(acc);
        } else {
            throw new AccountNotFoundException("Account Not Found");
        }
    }
}
