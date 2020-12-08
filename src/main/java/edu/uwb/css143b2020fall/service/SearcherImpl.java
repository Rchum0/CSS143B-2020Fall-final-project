package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        String[] keyWords = keyPhrase.split(" ");
        if(keyWords.length == 1){
            if(index.containsKey(keyWords[0]) == false)
                return result;
            List<List<Integer>> wordLocation = index.get(keyWords[0]);
            for(int i = 0; i < wordLocation.size(); i++){
                if(wordLocation.get(i).size() != 0){
                    result.add(i);
                }
            }
        }else {

        }



        return result;
    }
}