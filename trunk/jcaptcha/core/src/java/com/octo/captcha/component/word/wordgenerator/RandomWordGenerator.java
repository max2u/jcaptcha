/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word.wordgenerator;

import java.util.Locale;
import java.util.Random;

/**
 * <p>Random word generator. must be constructed with a String containing all possible chars</p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class RandomWordGenerator implements WordGenerator {

    private char[] possiblesChars;

    private Random myRandom = new Random();

    public RandomWordGenerator(String acceptedChars) {
        possiblesChars = acceptedChars.toCharArray();
    }

    /**
     * Return a word of lenght between min and max lenght
     *
     * @return a String of lenght between min and max lenght
     */
    public String getWord(Integer lenght) {
        StringBuffer word = new StringBuffer(lenght.intValue());
        for (int i = 0; i < lenght.intValue(); i++) {
            word.append(possiblesChars[myRandom.nextInt(possiblesChars.length)]);
        }
        return word.toString();
    }

    /**
     * Return a word of lenght between min and max lenght according to the given locale
     *
     * @param lenght the word lenght
     *
     * @return a String of lenght between min and max lenght according to the given locale
     */
    public String getWord(Integer lenght, Locale locale) {
        return getWord(lenght);
    }

}
