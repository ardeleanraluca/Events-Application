package com.demo.project;

import com.demo.project.service.LocationSeviceInterface;
import com.demo.project.tests.OperatiiDobanda;
import com.demo.project.tests.TipDobanda;
import com.demo.project.tests.UserInterfaceDB;
import com.demo.project.tests.UserInterfaceDBImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
    @Autowired
    LocationSeviceInterface locationService;
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);

        OperatiiDobanda operatiiDobanda = new OperatiiDobanda();
        System.out.println("\nDobanda: " + operatiiDobanda.calculDobanda(TipDobanda.MICA));


        UserInterfaceDB userInterfaceDB = new UserInterfaceDBImp();
        OperatiiDobanda operatiiDobanda2 = new OperatiiDobanda(userInterfaceDB);
        System.out.println("Dobanda cu risc: " + operatiiDobanda2.calculDobandaCuRisc());
    }


}
