package com.dcc.videoGameApi.controllers;

import com.dcc.videoGameApi.models.VideoGame;
import com.dcc.videoGameApi.repository.VideoGameRepository;
import com.dcc.videoGameApi.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VideoGameController {

    @Autowired
    private VideoGameService service;

    //Example endpoint to return count of all games on DB. Should return 10000 in Postman
    @GetMapping("/count")
    public long GetCount(){
        return service.GetCountOfGames();
    }

    @GetMapping("/games")
    public List<VideoGame> GetAll() {return service.GetAllVideoGames();}


    @RequestMapping(value = "/games/{id}")
    public List<VideoGame> GetById(@PathVariable Integer id) {return service.GetVideoGameById(id);}

}


