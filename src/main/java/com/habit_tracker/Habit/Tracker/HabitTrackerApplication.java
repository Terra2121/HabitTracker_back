package com.habit_tracker.Habit.Tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;

@SpringBootApplication
public class HabitTrackerApplication implements CommandLineRunner {

	@Autowired
	private Flyway flyway;

	public static void main(String[] args) {

		SpringApplication.run(HabitTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		flyway.migrate();
	} 
}
