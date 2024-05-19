package spring.learn.spring.service.impl;

import spring.learn.spring.service.DBInitializationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!test")
@Service
public class DbInit implements CommandLineRunner {
    private DBInitializationService dbInitService;

    public DbInit(DBInitializationService dbInitService) {
        this.dbInitService = dbInitService;
    }

    @Override
    public void run(String... args) {
        dbInitService.initPermissions();
        dbInitService.initRoles();
        dbInitService.initUsers();
    }
}
