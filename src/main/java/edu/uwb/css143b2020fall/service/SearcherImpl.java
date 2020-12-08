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

        if (keyWords.length == 1) {
            if (index.containsKey(keyWords[0]) == false)
                return result;
            List<List<Integer>> wordLocation = index.get(keyWords[0]);
            for (int i = 0; i < wordLocation.size(); i++) {
                if (wordLocation.get(i).size() != 0) {
                    result.add(i);
                }
            }
        } else {
            for (String word : keyWords)
                if (index.containsKey(word) == false)
                    return result;
            int numberOfDocs = index.get(keyWords[0]).size();
            for (int docIndex = 0; docIndex < numberOfDocs; docIndex++) {
                List<List<Integer>> wordOrder = new ArrayList<>(keyWords.length);
                for (String word : keyWords)
                    wordOrder.add(index.get(word).get(docIndex));
                if (inOrder(wordOrder))
                    result.add(docIndex);
            }
        }
        return result;
    }


    private boolean inOrder(List<List<Integer>> wordOrder) {
        for (int i = 0; i < wordOrder.get(0).size(); i++) {
            boolean order = inOrderHelper(wordOrder, 1, wordOrder.get(0).get(i));
            if (order)
                return true;
        }
        return false;
    }


    private boolean inOrderHelper(List<List<Integer>> wordOrder, int wordIndex, int previous) {
        if (wordOrder.size() <= wordIndex)
            return true;

        for (Integer order : wordOrder.get(wordIndex)) {
            if (order != previous + 1)
                continue;
            return inOrderHelper(wordOrder, wordIndex + 1, previous + 1);
        }
        return false;
    }
}