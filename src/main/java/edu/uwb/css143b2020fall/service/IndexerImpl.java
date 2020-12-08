package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

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
            for (String word : words)
                if (word.equals(""))
                    count++;

            if (count != 0) {

                String[] newWords = new String[words.length - count];
                int newWordsIndex = 0;

                for (int wordsIndex = 0; wordsIndex < words.length; wordsIndex++) {
                    if (words[wordsIndex].equals(""))
                        continue;
                    newWords[newWordsIndex++] = words[wordsIndex];
                }
                words = newWords;
            }

            //for loop to add the values to wordMap
            for (int wordI = 0; wordI < words.length; wordI++)
                wordMap.put(new int[]{documentI, wordI}, words[wordI]);
        }


        for (Map.Entry<int[], String> entry : wordMap.entrySet()) {
            int documentNum = entry.getKey()[0];
            int wordNum = entry.getKey()[1];
            String word = entry.getValue();

            if (indexes.containsKey(word)) {
                //tracks how many times the word is in the doc
                if (indexes.get(word).get(documentNum).size() == 0) {
                    indexes.get(word).set(documentNum, Arrays.asList(wordNum));
                } else {
                    List<Integer> temp = indexes.get(word).get(documentNum);
                    ArrayList<Integer> wordsInDocument = new ArrayList<>(temp.size());
                    for (int i = 0; i < temp.size(); i++)
                        wordsInDocument.add(temp.get(i));

                    for (int i = 0; i < wordsInDocument.size(); i++) {
                        if (wordsInDocument.get(i) > wordNum) {
                            wordsInDocument.add(i, wordNum);
                            break;
                        }
                        if (i == wordsInDocument.size() - 1) {
                            wordsInDocument.add(wordNum);
                            break;
                        }
                    }
                    indexes.get(word).set(documentNum, wordsInDocument);
                }
            } else {
                List<List<Integer>> newWordValue = new ArrayList<>(size);
                for (int i = 0; i < size; i++)
                    newWordValue.add(new ArrayList<>(0));
                newWordValue.set(documentNum, Arrays.asList(wordNum));
                indexes.put(word, newWordValue);
            }
        }
        return indexes;
    }
}