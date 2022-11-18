package com.springData.FirstSpringData.service;

import com.springData.FirstSpringData.orm.Job;
import com.springData.FirstSpringData.orm.Worker;
import com.springData.FirstSpringData.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Service
public class WorkerServiceCrud {
    private  final WorkerRepository workerRepository;
    private boolean system=true;
    private SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
    public WorkerServiceCrud(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }
    public void init (Scanner scanner) throws Exception {
        while(this.system) {
            System.out.println("What do you want to do?");
            System.out.println("0-Exit");
            System.out.println("1-add a new worker");
            System.out.println("2-update existing worker");
            System.out.println("3-delete an existing worker");
            System.out.println("4-see all workers in the database");
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
                default:
                    this.system = false;
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
        this.workerRepository.findAll().forEach(e -> System.out.println("id: "+ e.getId() +" name: "+ e.getName() +" cpf: "+ e.getCpf() +"salary: "+ e.getSalary() +"hiring date: "+ e.getHiring_Date()));
    }
}
