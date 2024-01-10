package com.kcst.sendserver.room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorManager {


    private ExecutorService executorService=  Executors.newFixedThreadPool(5);


   public void init(){


       executorService.submit(() -> {
           
       });


    }





}
