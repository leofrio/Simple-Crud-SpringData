package com.springData.FirstSpringData.service;

import com.springData.FirstSpringData.orm.Job;
import com.springData.FirstSpringData.orm.WorkPlace;
import com.springData.FirstSpringData.orm.Worker;
import com.springData.FirstSpringData.repository.JobRepository;
import com.springData.FirstSpringData.repository.WorkPlaceRepository;
import com.springData.FirstSpringData.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class WorkerServiceCrud {
    private  final WorkerRepository workerRepository;
    private final JobRepository jobRepository;
    private final WorkPlaceRepository workPlaceRepository;
    private SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
    public WorkerServiceCrud(WorkerRepository workerRepository,JobRepository jobRepository,WorkPlaceRepository workPlaceRepository) {
        this.workerRepository = workerRepository;
        this.jobRepository =jobRepository;
        this.workPlaceRepository=workPlaceRepository;
    }
    public void init (Scanner scanner) throws Exception {
        boolean system=true;
        while(system) {
            System.out.println("What do you want to do?");
            System.out.println("0-Exit");
            System.out.println("1-add a new worker");
            System.out.println("2-update existing worker");
            System.out.println("3-delete an existing worker");
            System.out.println("4-see all workers in the database");
            System.out.println("5-find a Worker By name");
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
                    showAllWorkers();
                    break;
                case 5 :
                    findByName(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }
    public void save(Scanner scanner) throws ParseException {
        System.out.println("Please type the workers name");
        String name=scanner.nextLine();
        System.out.println("please type the workers cpf");
        String cpf=scanner.nextLine();
        System.out.println("please type the workers salary");
        double salary=Double.parseDouble(scanner.nextLine());
        System.out.println("please type the workers hiring date(dd/mm/yyyy)");
        Date date=myFormat.parse(scanner.nextLine().trim());
        Worker newWorker= new Worker();
        newWorker.setName(name);
        newWorker.setCpf(cpf);
        newWorker.setSalary(salary);
        newWorker.setHiring_Date(date);
        this.workerRepository.save(newWorker);
        System.out.println("the worker " + newWorker.getName() + " was registered!");
    }
    public  void update(Scanner scanner) throws Exception {
        System.out.println("type the id of the worker you want to update");
        int id= Integer.parseInt(scanner.nextLine());
        Worker worker= this.workerRepository.findById(id).orElseThrow(() -> new Exception("the worker was not found"));
        boolean updating=true;
        while(updating) {
            System.out.println("selected worker: id: "+ worker.getId() +" name: "+ worker.getName() +" cpf: "+ worker.getCpf() +"salary: "+ worker.getSalary() +"hiring date: "+ worker.getHiring_Date() );
            System.out.println("what would you like to update from worker " + worker.getName() + "?");
            System.out.println("0-exit");
            System.out.println("1-name");
            System.out.println("2-cpf");
            System.out.println("3-salary");
            System.out.println("4-hiring date");
            System.out.println("5-job");
            System.out.println("6-work place");
            int action =Integer.parseInt(scanner.nextLine());
            switch(action) {
                case 1:
                    System.out.println("type the new name for worker " + worker.getName());
                    String name=scanner.nextLine();
                    worker.setName(name);
                    this.workerRepository.save(worker);
                    System.out.println("worker " + worker.getName() + " updated!");
                    break;
                case 2:
                    System.out.println("type the new cpf for worker " + worker.getName());
                    String cpf=scanner.nextLine();
                    worker.setCpf(cpf);
                    this.workerRepository.save(worker);
                    System.out.println("worker " + worker.getName() + " updated!");
                    break;
                case 3:
                    System.out.println("type the new salary for worker " + worker.getName());
                    double salary=Double.parseDouble(scanner.nextLine().trim());
                    worker.setSalary(salary);
                    this.workerRepository.save(worker);
                    System.out.println("worker " + worker.getName() + " updated!");
                    break;
                case 4:
                    System.out.println("type the new hiring date for worker " + worker.getName()+ "(dd/mm/yyyy)");
                    Date date=myFormat.parse(scanner.nextLine().trim());
                    worker.setHiring_Date(date);
                    this.workerRepository.save(worker);
                    System.out.println("worker " + worker.getName() + " updated!");
                    break;
                case 5:
                    System.out.println("type the new job for worker " + worker.getName());
                    String jobText =scanner.nextLine();
                    Job job=this.jobRepository.findByName(jobText).get(0);
                    worker.setJob(job);
                    this.workerRepository.save(worker);
                    System.out.println(job.getName() + " added to worker " + worker.getName());
                    break;
                case 6:
                    boolean updatingWorkPlaces=true;
                    while(updatingWorkPlaces){
                        System.out.println("what do you want to do with " + worker.getName() + "'s workplaces?");
                        System.out.println("0-exit");
                        System.out.println("1-add a new work place");
                        System.out.println("2-delete a work place");
                        System.out.println("3-show all work places used by " + worker.getName());
                        System.out.println("4-show all avaliable workplaces");
                        int awp=Integer.parseInt(scanner.nextLine());
                        switch (awp) {
                            case 1:
                                System.out.println("type the id of the workplacde you want to add");
                                int idaux= Integer.parseInt(scanner.nextLine());
                                WorkPlace workPlace =this.workPlaceRepository.findById(idaux).orElseThrow( () -> new Exception("id not found"));
                                if(!worker.getWorkPlaces().isEmpty()) {
                                    List<WorkPlace> workPlaces =worker.getWorkPlaces();
                                    workPlaces.add(workPlace);
                                    worker.setWorkPlaces(workPlaces);
                                    this.workerRepository.save(worker);
                                }
                                else {
                                    List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();
                                    workPlaces.add(workPlace);
                                    worker.setWorkPlaces(workPlaces);
                                    this.workerRepository.save(worker);
                                }
                                System.out.println("work places updated!");
                                break;
                            case 2:
                                if(worker.getWorkPlaces().isEmpty()){
                                    System.out.println("worker " + worker.getName() + " doest have any added workplaces");
                                    break;
                                }
                                else {
                                    List<WorkPlace> workPlaces =worker.getWorkPlaces();
                                    System.out.println("type the id of the workplace you want to delete");
                                    int ida=Integer.parseInt(scanner.nextLine());
                                    workPlaces.removeIf(e -> e.getId() == ida);
                                    worker.setWorkPlaces(workPlaces);
                                    this.workerRepository.save(worker);
                                    break;
                                }
                            case 3:
                                if(worker.getWorkPlaces().isEmpty()) {
                                    System.out.println("worker " + worker.getName() + " doest have any added workplaces");
                                    break;
                                } else {
                                    System.out.println("workplaces frequented by " + worker.getName());
                                    List<WorkPlace> workPlaces=worker.getWorkPlaces();
                                    workPlaces.forEach(e -> System.out.println("id: " + e.getId() + " name: " + e.getName() + " address: " +e.getAddress() ));
                                }
                                break;
                            case 4:
                                this.workPlaceRepository.findAll().forEach(e -> System.out.println("id: " + e.getId() + " name: " + e.getName() + " address: " +e.getAddress() ));
                                break;
                            default:
                                updatingWorkPlaces=false;
                                break;
                        }
                    }
                default:
                    updating=false;
            }
        }

    }
    public void delete(Scanner scanner) {
        System.out.println("type the id of the worker you want to delete");
        String id= scanner.nextLine();
        this.workerRepository.deleteById(Integer.parseInt(id));
        System.out.println("worker deleted!");
    }
    public void showAllWorkers() {
        this.workerRepository.findAll().forEach(e ->{
            System.out.print("id: "+ e.getId() +" name: "+ e.getName() +" cpf: "+ e.getCpf() +"salary: "+ e.getSalary() +" hiring date: "+ e.getHiring_Date());
            System.out.print(e.getJob() == null ? " job: unemployed" : " job: " + e.getJob().getName() +" work places: [");
            e.getWorkPlaces().forEach(a -> System.out.print("id: " + a.getId() + ",name: " + a.getName()+ ",address: " +a.getAddress() + "],"));
            System.out.println("");
        });
    }
    public void findByName(Scanner scanner) {
        System.out.println("type the name of the worker(s) you want to find");
        String name=scanner.nextLine();
        List<Worker> list =workerRepository.findByName(name);
        list.forEach(e -> System.out.println("id: "+ e.getId() +" name: "+ e.getName() +" cpf: "+ e.getCpf() +"salary: "+ e.getSalary() +" hiring date: "+ e.getHiring_Date() + " job: " + e.getJob() +" workPlaces: " + e.getWorkPlaces()));

    }
}
