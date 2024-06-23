package com.epam.bsp.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import static com.epam.bsp.Solution.findLevenshteinDistance;

public class FuzzySearchEngine {
    public List<String> corpus;

    public FuzzySearchEngine(List<String> corpus) {
        this.corpus = corpus;
    }

    /**
     * Returns the most similar texts from the corpus based on a query text.
     *
     * NOTE: Please use the Levenshtein distance to measure the similarity between strings.
     *
     * NOTE: If there are multiple texts with the same Levenshtein distance for the query text
     *   within the corpus, order the results lexicographically.
     *
     * NOTE: If the corpus contains less than 'nResults' texts, please return all the available ones.
     *
     * @param queryText a query text that should be used to retrieve texts from the corpus.
     * @param nResults the number of texts that should be returned (the most similar ones).
     * @return the best matches from the corpus.
     */
    public List<String> findBestMatches(String queryText, int nResults) {
        List<String> results = new ArrayList<>(corpus);
        
        results.sort(Comparator.comparingInt((String s) -> findLevenshteinDistance(s, queryText))
                               .thenComparing(Comparator.naturalOrder()));
        
        return results.subList(0, Math.min(nResults, results.size()));
    }
}
