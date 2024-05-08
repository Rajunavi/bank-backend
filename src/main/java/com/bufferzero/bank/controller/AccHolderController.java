package com.bufferzero.bank.controller;

import com.bufferzero.bank.auto.Message;
import com.bufferzero.bank.entities.AccHolder;
import com.bufferzero.bank.exceptions.AccountNotFoundException;
import com.bufferzero.bank.requests.AccHolderRequest;
import com.bufferzero.bank.services.AccHolderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bank")
public class AccHolderController {

    @Autowired
    private AccHolderService accHolderService;

    @PostMapping("/addaccount")
    public ResponseEntity<?> addaccount(@RequestBody @Valid AccHolderRequest accHolderRequest){
        return accHolderService.addAccount(accHolderRequest);
    }

    @GetMapping("/getAllAccounts")
    public List<AccHolder> getAllAcc(){
        return accHolderService.getAllAccounts();
    }

    @GetMapping("/getAccout/{id}")
    public AccHolder getAccount(@PathVariable("id") int id) throws Exception {
        return accHolderService.getAccoutDetails(id);
    }
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Message> deleteAccount(@PathVariable("id") int id) throws AccountNotFoundException {
        return accHolderService.deleteAccount(id);
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccHolder> updateAccount(@PathVariable("id") int id,@Valid @RequestBody AccHolderRequest accHolderRequest) throws AccountNotFoundException {
        return accHolderService.updateAccount(id, accHolderRequest);
    }
}
