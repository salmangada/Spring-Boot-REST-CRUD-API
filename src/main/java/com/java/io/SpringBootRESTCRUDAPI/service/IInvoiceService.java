package com.java.io.SpringBootRESTCRUDAPI.service;

import com.java.io.SpringBootRESTCRUDAPI.entity.Invoice;

import java.util.List;

public interface IInvoiceService {

    Long saveInvoice(Invoice inv);

    void updateInvoice(Invoice inv);

    void deleteInvoice(Long id);

    Invoice getOneInvoice(Long id);

    List<Invoice> getAllInvoices();

    boolean isInvoiceExist(Long id);

    Integer updateInvoiceNumberById(String number,Long id);


}
