package com.example.scoreboard.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scoreboard.model.entity.Contest;
import com.example.scoreboard.model.repository.ContestRepository;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    public Iterable<Contest> getAll() {
        return this.contestRepository.findAll();
    }

    /* Get bu id */

    public Optional<Contest> get(Long id) {
        return contestRepository.findById(id);
    }

    /* Save : Ajout + modif */

    public Contest save(Contest g) {
        return this.contestRepository.save(g);
    }

    /** delete ById */

    public void delete(Long id) {
        contestRepository.deleteById(id);
    }

    public void delete(Contest g) {
        contestRepository.delete(g);
    }

}
