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

    public HashMap<String, Double> GetPlatformSales() {
        List<String> platforms = videoGameRepository.findAll().stream().filter(v -> v.getYear() >= 2013).map(w -> w.getPlatform()).distinct().collect(Collectors.toList());
        HashMap<String, Double> globalSales = new HashMap<String, Double>();
        for(String x: platforms){
            Double totalSales = videoGameRepository.findAll().stream().filter(y -> y.getYear() >= 2013).filter(z -> z.getPlatform().equals(x)).map(a -> a.getGlobalsales()).reduce((double) 0, (e1, e2) ->e1 + e2);
            globalSales.put(x, totalSales);
        }

        return globalSales;
    }

    public HashMap<String, Integer> GetRankedPublisher() {
        List<String> publishers = videoGameRepository.findAll().stream().filter(y -> y.getGame_rank() <= 300 && y.getGenre().equals("Action")).map(w -> w.getPublisher()).distinct().collect(Collectors.toList());
        HashMap<String,Integer> gameAmountPublished = new HashMap<String, Integer>();
        for(String x: publishers){
            Integer totalGames = Math.toIntExact(videoGameRepository.findAll().stream().filter(y -> y.getGame_rank() <= 300 && y.getGenre().equals("Action")).filter(l -> l.getPublisher().equals(x)).count());

            gameAmountPublished.put(x, totalGames);
        }

        return gameAmountPublished;
    }

}
