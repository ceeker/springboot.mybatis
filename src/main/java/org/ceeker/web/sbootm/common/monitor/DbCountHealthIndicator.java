package org.ceeker.web.sbootm.common.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.repository.CrudRepository;



public class DbCountHealthIndicator implements HealthIndicator {
    private CrudRepository crudRepository;

    public DbCountHealthIndicator(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Health health() {
        try {
            long count = crudRepository.count();
            if (count >= 0) {
                return Health.up().withDetail("count", count).build();
            } else {
                return Health.unknown().withDetail("count", count).build();
            }
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
