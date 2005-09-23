/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.TreeMap;

/**
 * <p>Container for words that is initialized from a Dictionnary. </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class DefaultSizeSortedWordList implements SizeSortedWordList {

    private TreeMap sortedWords = new TreeMap();

    private Locale locale;

    private Random myRandom = new Random();

    /**
     * A word list has to be constructed with a locale
     */
    public DefaultSizeSortedWordList(Locale locale) {
        this.locale = locale;
    }

    ;

    /**
     * Return a locale for this list
     *
     * @return th e locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Adds a word to the list
     */
    public void addWord(String word) {
        Integer lenght = new Integer(word.length());
        if (sortedWords.containsKey(lenght)) {
            ArrayList thisLenghtwords = (ArrayList) sortedWords.get(lenght);
            thisLenghtwords.add(word);
            sortedWords.put(lenght, thisLenghtwords);
        } else {
            ArrayList thisLenghtwords = new ArrayList();
            thisLenghtwords.add(word);
            sortedWords.put(lenght, thisLenghtwords);
        }
        //words.add(word);
        //lengths.add(new Integer(word.length()));

    }

    /**
     * Return the min lenght of contained word in this wordlist
     *
     * @return the min lenght of contained word in this wordlist
     */
    public Integer getMinWord() {
        return (Integer) sortedWords.firstKey();
    }

    /**
     * Return the max lenght of contained word in this wordlist
     *
     * @return the max lenght of contained word in this wordlist
     */
    public Integer getMaxWord() {
        return (Integer) sortedWords.lastKey();
    }

    /**
     * Return a word of randomly choosen of the specified lenght. Return null if none found
     *
     * @return a word of this lenght
     */
    public String getNextWord(Integer lenght) {
        if (sortedWords.containsKey(lenght)) {
            ArrayList thisLenghtwords = (ArrayList) sortedWords.get(lenght);
            int pickAWord = myRandom.nextInt(thisLenghtwords.size());
            return (String) thisLenghtwords.get(pickAWord);
        } else {
            return null;
        }
    }
}
