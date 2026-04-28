package com.ticketing.system.config;

import com.ticketing.system.entity.Role;
import com.ticketing.system.entity.RoleType;
import com.ticketing.system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {
    public final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running DataSeeder");
        seedRole();
        log.info("Data Seeder");
    }

    private void seedRole() {
    for(RoleType roleType : RoleType.values()){
        boolean exists= roleRepository.findByName(roleType).isEmpty();
        if(!exists){
            Role role = Role.builder()
                    .name(roleType)
                    .build();
            roleRepository.save(role);
            log.info("Role {} has been created", roleType.name());
        }else {
            log.info("Role {} already exists", roleType.name());
        }
    }
    }
}
