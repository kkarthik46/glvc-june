package com.example.paymentbatch.batch;

import com.example.paymentbatch.model.Payment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PaymentItemProcessor implements ItemProcessor<Payment, Payment> {
	@Override
    public Payment process(Payment payment) {
        payment.setStatus("Completed");
        return payment;
    }
    
}
