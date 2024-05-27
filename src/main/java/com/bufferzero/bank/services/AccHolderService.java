package com.bufferzero.bank.services;

import com.bufferzero.bank.auto.Message;
import com.bufferzero.bank.entities.AccHolder;
import com.bufferzero.bank.exceptions.AccountNotFoundException;
import com.bufferzero.bank.exceptions.AccountTransactionExp;
import com.bufferzero.bank.repository.AccHolderRepo;
import com.bufferzero.bank.requests.AccHolderRequest;
import com.bufferzero.bank.requests.AmountTransactionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AccHolderService {
    @Autowired
    private AccHolderRepo accHolderRepo;

    @Value("${upload.directory}")
    private String uploadDirectory;

    public ResponseEntity<Message> addAccount(AccHolderRequest accHolderRequest, MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
        String fileName = dateTimeString+ "_" + originalFilename; // Generate a unique filename
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDirectory + fileName);
        Files.write(path, bytes);

        AccHolder accHolder = new AccHolder(accHolderRequest.getFirstName(),accHolderRequest.getLastName(),accHolderRequest.getMobileNo(),accHolderRequest.getAccBalance(), accHolderRequest.getEmail(), fileName);

        accHolderRepo.save(accHolder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Message("Account Created Successfully"));
    }

    public ResponseEntity<AccHolder> getAccountById(int id){
        Optional<AccHolder> accHolderOpt = Optional.ofNullable(accHolderRepo.findById(id));

        AccHolder accHolder = accHolderOpt.get();

        return ResponseEntity.ok(accHolder);
//        if (accHolder != null) {
//
//                byte[] imageData = Files.readAllBytes(Paths.get(uploadDirectory, accHolder.getFileName()));
//                accHolder.setFileName(Base64.getEncoder().encodeToString(imageData)); // Convert image data to Base64 string
//
//            return ResponseEntity.ok(accHolder);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    public ResponseEntity<Resource> getProfileImage(int id) throws IOException{
        Optional<AccHolder> accHolderOpt = Optional.ofNullable(accHolderRepo.findById(id));

        AccHolder accHolder = accHolderOpt.get();

        //get the image path from Acc obj
        Path imagePath = Paths.get(uploadDirectory, accHolder.getFileName());
        //fetch the img from particular path
        Resource resource = new FileSystemResource(imagePath.toFile());
        String contentType = Files.probeContentType(imagePath);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
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
                acc.setFirstName(accHolderRequest.getFirstName());
                acc.setLastName(accHolderRequest.getLastName());
                acc.setMobileNo(accHolderRequest.getMobileNo());
            accHolderRepo.save(acc);
            return ResponseEntity.ok(acc);
        } else {
            throw new AccountNotFoundException("Account Not Found");
        }
    }

    public List<AccHolder> findAccountBySearch(@Param("accNo") long accNo){
            return accHolderRepo.findAccountBySearch(accNo);
    }

    public ResponseEntity<Message> amoutTransaction(int id, AmountTransactionReq amtTrReq) throws AccountTransactionExp{
        AccHolder acc = accHolderRepo.findById(id);
            String type = amtTrReq.getTypeOfOp();
        switch (type){
            case "Deposit":
                if(amtTrReq.getAmt() < 100) throw new AccountTransactionExp("Deposit Amount should be more than 100", "");
                else acc.setAccBalance(acc.getAccBalance() + amtTrReq.getAmt());
                break;
            case "Withdraw":
                if(amtTrReq.getAmt() < 100) throw new AccountTransactionExp("Withdraw Amount should be more than 100", "");
                else if(acc.getAccBalance() - 500 < amtTrReq.getAmt()) throw new AccountTransactionExp("Insufficient Balance!","");
                else acc.setAccBalance(acc.getAccBalance() - amtTrReq.getAmt());
                break;
            default:
                break;
        }


        accHolderRepo.save(acc);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Message("Successfully "+amtTrReq.getTypeOfOp()+", Your Account  Balance: Rs."+acc.getAccBalance()));
    }
}
