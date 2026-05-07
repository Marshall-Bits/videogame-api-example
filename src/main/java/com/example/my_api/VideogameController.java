package com.example.my_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@RequiredArgsConstructor
public class VideogameController {

    private final VideogameService videogameService;

    @GetMapping
    public ResponseEntity<List<Videogame>> getAll() {
        return ResponseEntity.ok(videogameService.getAllVideogames());
    }
}
