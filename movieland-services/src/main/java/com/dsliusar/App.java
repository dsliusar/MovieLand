package com.dsliusar;

import com.dsliusar.file.services.MovieLandService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
        MovieLandService movieLandService = (MovieLandService) context.getBean("performFillDb");
        movieLandService.performFillMovieLand();
     }
}
