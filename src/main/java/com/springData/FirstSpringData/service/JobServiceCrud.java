package com.springData.FirstSpringData.service;

import com.springData.FirstSpringData.orm.Job;
import com.springData.FirstSpringData.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class JobServiceCrud {
    private  final JobRepository jobRepository;
    private boolean system=true;
    public JobServiceCrud(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public void init (Scanner scanner) throws Exception {
        while(system) {
            System.out.println("What do you want to do?");
            System.out.println("0-Exit");
            System.out.println("1-add a new Job");
            System.out.println("2-update existing job");
            System.out.println("3-delete an existing job");
            System.out.println("4-see all jobs in the database");
            int action = Integer.parseInt(scanner.nextLine());
            switch(action) {
                case 1:
                    save(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    delete(scanner);
                    break;
                case 4:
                    showAllJobs();
                    break;
                default:
                    this.system = false;
            }
        }
    }
    public void save(Scanner scanner){
        System.out.println("Please type the Job name");
        String name=scanner.nextLine();
        System.out.println("please type the Job description");
        String description=scanner.nextLine();
        Job newJob= new Job();
        newJob.setName(name);
        newJob.setDescription(description);
        this.jobRepository.save(newJob);
        System.out.println("the job " + newJob.getDescription() + " was saved!");
    }
    public  void update(Scanner scanner) throws Exception {
        System.out.println("type the id of the job you want to change the description");
        String id=scanner.nextLine();
        Job ujob=this.jobRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new Exception("that job doesnt exist") );
        System.out.println("type the new description for " + ujob.getName());
        String newDescription=scanner.nextLine();
        ujob.setDescription(newDescription);
        this.jobRepository.save(ujob);
        System.out.println("job " + ujob.getName() +" updated!");
    }
    public void delete(Scanner scanner) {
        System.out.println("type the id of the job you want to delete");
        String id= scanner.nextLine();
        this.jobRepository.deleteById(Integer.parseInt(id));
        System.out.println("job deleted!");
    }
    public void showAllJobs() {
        this.jobRepository.findAll().forEach(e -> System.out.println("id: " + e.getId() + " name: "+ e.getName() + " description: " + e.getDescription()));
    }
}
