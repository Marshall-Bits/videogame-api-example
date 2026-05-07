package com.example.my_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideogameService {

    private final VideogameRepository videogameRepository;

    public List<Videogame> getAllVideogames() {
        return videogameRepository.findAll();
    }
}
