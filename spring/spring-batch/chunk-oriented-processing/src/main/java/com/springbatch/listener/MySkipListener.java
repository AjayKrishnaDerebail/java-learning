package com.springbatch.listener;

import com.springbatch.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class MySkipListener implements SkipListener<Product, Product> {
    @Override
    public void onSkipInRead(@NonNull Throwable t) {
        System.out.println("Skipped reading due to: " + t.getMessage());
        if(t instanceof FlatFileParseException){
          writeFailureRecord(((FlatFileParseException) t).getInput());
        }
    }

    @Override
    public void onSkipInWrite(@NonNull Product item, @NonNull Throwable t) {
        System.out.println("Skipped writing item " + item + " due to: " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(@NonNull Product item, @NonNull Throwable t) {
        System.out.println("Skipped processing item " + item + " due to: " + t.getMessage());
        if(t instanceof ValidationException){
            System.out.println("Validation failed for item: " + item);
            writeFailureRecord(item);
        }
    }

    private <T>void writeFailureRecord(T item) {
        try {
            // Get the resources directory path
            File resourcesDir = ResourceUtils.getFile("resources/failedRecords");

            // Create a timestamp for the filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = String.format("failed_record_%s.txt", timestamp);

            // Create the file
            File outputFile = new File(resourcesDir, filename);

            // Write the failed record to the file
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(String.format("Failed Item: %s%n", item));
            }

            System.out.println("Failed record written to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Failed to write error record to file: " + e.getMessage());
            log.error("Error details {} :" ,e.getMessage() , e);
        }
    }
}