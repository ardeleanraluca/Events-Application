package com.demo.project.tests;

public class UserInterfaceDBImp implements UserInterfaceDB {

    @Override
    public User getUser() {
        return new User("Ardelean Raluca", TipRisc.MIC);
    }
}
