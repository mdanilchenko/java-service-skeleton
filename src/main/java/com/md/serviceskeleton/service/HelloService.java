package com.md.serviceskeleton.service;

import com.md.serviceskeleton.entity.Hello;
import com.md.serviceskeleton.exception.ResourceNotFoundException;
import com.md.serviceskeleton.repository.HelloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {

    private HelloRepository repository;

    public HelloService(HelloRepository repository) {
        this.repository = repository;
    }

    public Hello getByUuid(String uuid) {
        List<Hello> hellos = this.repository.findByUuid(uuid);

        return hellos.size() == 0 ? null : hellos.get(0);
    }

    public List<Hello> getAll() {
        return this.repository.findAll();
    }

    public void delete(Hello hello) {
        this.repository.delete(hello);
    }

    public Hello updateByUuid(String uuid, Hello hello) {
        Hello originalHello = getByUuid(uuid);
        if (originalHello == null) {
            throw new ResourceNotFoundException("Hello", "UUID", uuid);
        }

        hello.setId(originalHello.getId());
        return repository.save(hello);
    }

    public Hello save(Hello hello) {
        return repository.save(hello);
    }
}
