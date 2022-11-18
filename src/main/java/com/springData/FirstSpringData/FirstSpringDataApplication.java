package com.springData.FirstSpringData;

import com.springData.FirstSpringData.orm.Job;
import com.springData.FirstSpringData.repository.JobRepository;
import com.springData.FirstSpringData.service.JobServiceCrud;
import com.springData.FirstSpringData.service.WorkPlaceServiceCrud;
import com.springData.FirstSpringData.service.WorkerServiceCrud;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FirstSpringDataApplication implements CommandLineRunner {
	private final JobServiceCrud jobService;
	private final WorkerServiceCrud workerService;
	private final WorkPlaceServiceCrud workPlaceService;
	private boolean system=true;

	public FirstSpringDataApplication(JobServiceCrud jobService, WorkerServiceCrud workerService, WorkPlaceServiceCrud workPlaceService) {
		this.jobService = jobService;
		this.workerService = workerService;
		this.workPlaceService = workPlaceService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner =new Scanner(System.in);
		while(this.system) {
			System.out.println("What do you want to do?");
			System.out.println("0-Exit");
			System.out.println("1-Jobs");
			System.out.println("2-Workers");
			System.out.println("3-Workplace");
			int action = Integer.parseInt(scanner.nextLine());
			switch(action) {
				case 1:
					jobService.init(scanner);
					break;
				case 2:
					workerService.init(scanner);
					break;
				case 3:
					workPlaceService.init(scanner);
					break;
				default:
					this.system=false;
					break;
			}
		}
	}
}
