package com.bufferzero.bank.controller;

import com.bufferzero.bank.auto.Message;
import com.bufferzero.bank.entities.AccHolder;
import com.bufferzero.bank.exceptions.AccountNotFoundException;
import com.bufferzero.bank.exceptions.AccountTransactionExp;
import com.bufferzero.bank.requests.AccHolderRequest;
import com.bufferzero.bank.requests.AmountTransactionReq;
import com.bufferzero.bank.services.AccHolderService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/bank")
@Builder
public class AccHolderController {

    @Autowired
    private AccHolderService accHolderService;

//    public static String uploadDirectory = System.getProperty("user.dir") + "/bankapp/images/";


//    @Value("${upload.directory}")
//    private String uploadDirectory;

    @PostMapping("/addaccount")
    public ResponseEntity<?> addaccount(@ModelAttribute @Valid  AccHolderRequest accHolderRequest, @RequestParam("image") MultipartFile file) throws IOException {

        return accHolderService.addAccount(accHolderRequest, file);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Message> amoutTransaction(@PathVariable("id") int id, @RequestBody AmountTransactionReq amtTrReq) throws AccountTransactionExp {
        return accHolderService.amoutTransaction(id,amtTrReq);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccHolder> getAccountById(@PathVariable int id) {
        return accHolderService.getAccountById(id);
    }

    //get image
    @GetMapping("/account/profileImage/{id}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable int id) throws IOException{
        return accHolderService.getProfileImage(id);
    }

    // this conroller getting all the customer details
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
    public ResponseEntity<AccHolder> updateAccount(@PathVariable("id") int id, @Valid @RequestBody AccHolderRequest accHolderRequest) throws AccountNotFoundException, AccountTransactionExp {
        return accHolderService.updateAccount(id, accHolderRequest);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccHolder>> searchAccount(@RequestParam long accNo){
        List<AccHolder> foundAccount = accHolderService.findAccountBySearch(accNo);
        if (!foundAccount.isEmpty()) {
            return ResponseEntity.ok(foundAccount);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
