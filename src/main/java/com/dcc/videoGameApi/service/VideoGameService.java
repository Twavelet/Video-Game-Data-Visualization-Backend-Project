package com.dcc.videoGameApi.service;

import com.dcc.videoGameApi.models.VideoGame;
import com.dcc.videoGameApi.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public HashMap<String, Integer> GetPlatformSales() {
        List<String> platforms = videoGameRepository.findAll().stream().filter(v -> v.getYear() > 2013).map(w -> w.getPlatform()).distinct().collect(Collectors.toList());
        HashMap<String, Integer> globalSales = new HashMap<String, Integer>();
        for(String x: platforms){
            Integer totalSales = Math.toIntExact(videoGameRepository.findAll().stream().filter(y -> y.getYear() > 2013).filter(z -> z.getPlatform().equals(x)).count());
            globalSales.put(x, totalSales);
        }

        return globalSales;
    }
}
