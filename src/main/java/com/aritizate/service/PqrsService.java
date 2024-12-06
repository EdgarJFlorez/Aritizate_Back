package com.aritizate.service;

import com.aritizate.model.Pqrs;
import com.aritizate.repository.PqrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrsService {

    @Autowired
    private PqrsRepository pqrsRepository;

    public Pqrs savePqrs(Pqrs pqrs) {
        return pqrsRepository.save(pqrs);
    }
}
