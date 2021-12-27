package com.v1.resavation;

import com.v1.resavation.utils.DatabaseSeeding;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResavationApplication implements CommandLineRunner {

    private final DatabaseSeeding databaseSeeding;

    public ResavationApplication(DatabaseSeeding databaseSeeding) {
        this.databaseSeeding = databaseSeeding;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResavationApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        databaseSeeding.seed();
    }
}
