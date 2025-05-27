package com.own.jsonschemavalidator.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MasterSchemaRepository extends JpaRepository<MasterSchema,Long> {


    @Query(value = "Select  sample from master_schema where value = ?",nativeQuery = true)
    String getValue(String value);



}
