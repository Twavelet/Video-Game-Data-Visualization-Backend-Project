package com.dcc.videoGameApi.service;

import com.dcc.videoGameApi.models.VideoGame;
import com.dcc.videoGameApi.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository videoGameRepository;

    public long GetCountOfGames(){
        return videoGameRepository.count();
    }

    public List<VideoGame> GetAllVideoGames(){

        return videoGameRepository.findAll().stream().toList();
    }

    public List<VideoGame> GetVideoGameById(Integer id){
       List <VideoGame> videoGame = videoGameRepository.findAll().stream().filter(v -> id.equals(v.getId())).toList();

        return videoGame;
    }

}
