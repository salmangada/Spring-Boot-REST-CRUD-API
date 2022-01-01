package com.java.io.SpringBootRESTCRUDAPI.controller;

import com.java.io.SpringBootRESTCRUDAPI.entity.Invoice;
import com.java.io.SpringBootRESTCRUDAPI.exception.InvoiceNotFoundException;
import com.java.io.SpringBootRESTCRUDAPI.service.IInvoiceService;
import com.java.io.SpringBootRESTCRUDAPI.util.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice/rest")
public class InvoiceRestController {

    @Autowired
    private IInvoiceService service;

    @Autowired
    private InvoiceUtil util;

    //saving the data
    @PostMapping("/saveInvoice")
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice inv){
        ResponseEntity<String> resp = null;
        try{
            Long id = service.saveInvoice(inv);
            resp= new ResponseEntity<String>(
                    "Invoice '"+id+"' created", HttpStatus.CREATED); //201-created
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-Internal Server Error
        }
        return resp;
    }

    // Getting all data
    @GetMapping("/getAllInvoices")
    public ResponseEntity<?> getAllInvoices() {
        ResponseEntity<?> resp=null;
        try {
            List<Invoice> list= service.getAllInvoices();
            resp= new ResponseEntity<List<Invoice>>(list,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Getting single data
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getOneInvoice(@PathVariable Long id){
        ResponseEntity<?> resp= null;
        try {
            Invoice inv= service.getOneInvoice(id);
            resp= new ResponseEntity<Invoice>(inv,HttpStatus.OK);
        }catch (InvoiceNotFoundException nfe) {
            throw nfe;
        }catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    //deleting the data
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id){

        ResponseEntity<String> resp= null;
        try {
            service.deleteInvoice(id);
            resp= new ResponseEntity<String> (
                    "Invoice '"+id+"' deleted",HttpStatus.OK);

        } catch (InvoiceNotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp= new ResponseEntity<String>(
                    "Unable to delete Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    // Updating the data
    @PutMapping("/modify/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){

        ResponseEntity<String> resp = null;
        try {
            //db Object
            Invoice inv= service.getOneInvoice(id);
            //copy non-null values from request to Database object
            util.copyNonNullValues(invoice, inv);
            //finally update this object
            service.updateInvoice(inv);
            resp = new ResponseEntity<String>(
                    "Invoice '"+id+"' Updated",
                    HttpStatus.RESET_CONTENT); //205- Reset-Content(PUT)

        } catch (InvoiceNotFoundException nfe) {
            throw nfe; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to Update Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
        }
        return resp;
    }

    //Update using the invoice number
    @PatchMapping("/modify/{id}/{number}")
    public ResponseEntity<String> updateInvoiceNumberById(
            @PathVariable Long id,
            @PathVariable String number
    )
    {
        ResponseEntity<String> resp = null;
        try {
            service.updateInvoiceNumberById(number, id);
            resp = new ResponseEntity<String>(
                    "Invoice '"+number+"' Updated",
                    HttpStatus.PARTIAL_CONTENT); //206- Reset-Content(PUT)

        } catch(InvoiceNotFoundException pne) {
            throw pne; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to Update Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
        }
        return resp;
    }
}
