package com.example.paymentbatch.batch;

import com.example.paymentbatch.model.Payment;
import com.example.paymentbatch.repository.PaymentRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentItemWriter implements ItemWriter<Payment> {

    @Autowired
    private PaymentRepository repository;

    @Override
    public void write(Chunk<? extends Payment> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }
}
