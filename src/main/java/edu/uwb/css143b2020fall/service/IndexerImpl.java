package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        int size = docs.size();

        //wordMap the variable to store doc number and the place in doc.
        Map<int[], String> wordMap = new HashMap<>();

        for (int documentI = 0; documentI < size; documentI++) {

            String[] words = docs.get(documentI).split(" ");

            //getting rid of the spaces
            int count = 0;
            for (String word : words) {
                if (word.equals(""))
                    count++;

                if (count != 0) {

                    String[] newWords = new String[words.length - count];
                    int newWordsIndex = 0;

                    for (int wordsIndex = 0; wordsIndex < words.length; wordsIndex++) {

                    }
                }


            }
        }
        return indexes;
    }
}