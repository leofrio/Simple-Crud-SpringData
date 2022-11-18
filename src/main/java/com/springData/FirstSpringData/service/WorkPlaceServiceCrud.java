package com.springData.FirstSpringData.service;

import com.springData.FirstSpringData.orm.WorkPlace;
import com.springData.FirstSpringData.orm.Worker;
import com.springData.FirstSpringData.repository.WorkPlaceRepository;
import com.springData.FirstSpringData.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

@Service
public class WorkPlaceServiceCrud {
    private  final WorkPlaceRepository workPlaceRepository;
    private boolean system=true;
    public WorkPlaceServiceCrud(WorkPlaceRepository workPlaceRepository) {
        this.workPlaceRepository = workPlaceRepository;
    }
    public void init (Scanner scanner) throws Exception {
        while(system) {
            System.out.println("What do you want to do?");
            System.out.println("0-Exit");
            System.out.println("1-add a new work place");
            System.out.println("2-update existing work place ");
            System.out.println("3-delete an existing work place");
            System.out.println("4-see all work places in the database");
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
                    showAllWorkPlaces();
                    break;
                default:
                    this.system = false;
            }
        }
    }
    public void save(Scanner scanner) {
        System.out.println("Please type the work place name");
        String name=scanner.nextLine();
        System.out.println("please type the work place address");
        String address=scanner.nextLine();
        WorkPlace workPlace=new WorkPlace();
        workPlace.setName(name);
        workPlace.setAddress(address);
        this.workPlaceRepository.save(workPlace);
        System.out.println("the work place " + workPlace.getName() + " was registered!");
    }
    public  void update(Scanner scanner) throws Exception {
        System.out.println("type the id of the work place you want to update");
        int id= Integer.parseInt(scanner.nextLine());
        WorkPlace workPlace= this.workPlaceRepository.findById(id).orElseThrow(() -> new Exception("the work place was not found"));
        boolean updating=true;
        while(updating) {
            System.out.println("selected worker: id: "+ workPlace.getId() +" name: "+ workPlace.getName() +" address: "+ workPlace.getAddress());
            System.out.println("what would you like to update from work place " + workPlace.getName() + "?");
            System.out.println("0-exit");
            System.out.println("1-name");
            System.out.println("2-address");
            int action =Integer.parseInt(scanner.nextLine());
            switch(action) {
                case 1:
                    System.out.println("type the new name for work place " + workPlace.getName() + "?");
                    String name=scanner.nextLine();
                    workPlace.setName(name);
                    this.workPlaceRepository.save(workPlace);
                    System.out.println("work place " + workPlace.getName() + " updated!");
                    break;
                case 2:
                    System.out.println("type the new address for work place " + workPlace.getName());
                    String address=scanner.nextLine();
                    workPlace.setAddress(address);
                    this.workPlaceRepository.save(workPlace);
                    System.out.println("work place " + workPlace.getName() + " updated!");
                    break;
                default:
                    updating=false;
            }
        }

    }
    public void delete(Scanner scanner) {
        System.out.println("type the id of the work place you want to delete");
        String id= scanner.nextLine();
        this.workPlaceRepository.deleteById(Integer.parseInt(id));
        System.out.println("worker deleted!");
    }
    public void showAllWorkPlaces() {
        this.workPlaceRepository.findAll().forEach(e -> System.out.println("id: "+ e.getId() +" name: "+ e.getName() +" address: "+ e.getAddress() ));
    }
}
