package com.gfg.serviceskeleton.repository;

import com.gfg.serviceskeleton.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {

    List<Hello> findByUuid(@Param("uuid") String uuid);
}
