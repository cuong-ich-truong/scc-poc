package com.zuhlke.scc.web.domain.incident;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidentRepository extends CrudRepository<IncidentEntity, String> {

    @EnableScan
    List<IncidentEntity> findAll();
}
