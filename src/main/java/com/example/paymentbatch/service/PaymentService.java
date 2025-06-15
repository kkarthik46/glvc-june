package com.example.paymentbatch.service;

import com.example.paymentbatch.model.Payment;
import com.example.paymentbatch.repository.PaymentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private PaymentRepository repository;

    // Absolute path to uploads folder (auto-created inside the project root)
    private final String DIR = new File("uploads").getAbsolutePath();

    public String storeFile(MultipartFile file) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (!created) {
                    throw new IOException("Failed to create upload directory: " + DIR);
                }
            }

            String path = DIR + File.separator + file.getOriginalFilename();
            File destFile = new File(path);
            file.transferTo(destFile);
            return path;
        } catch (IOException e) {
            throw new RuntimeException("Error storing uploaded file: " + e.getMessage(), e);
        }
    }

    public void startBatchJob(String path) {
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("filePath", path)
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(job, parameters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start batch job: " + e.getMessage(), e);
        }
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}
