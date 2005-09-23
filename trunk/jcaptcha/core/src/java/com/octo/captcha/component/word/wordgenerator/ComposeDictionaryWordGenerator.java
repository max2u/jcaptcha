/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word.wordgenerator;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.word.DictionaryReader;
import com.octo.captcha.component.word.SizeSortedWordList;

import java.util.Locale;

/**
 * <p>This Word Generator use a Dictionnary to compose new Words from existing words.</p> It avoid dictionnary
 * systematic submission, and may compose words of any length.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class ComposeDictionaryWordGenerator extends DictionaryWordGenerator {

    public ComposeDictionaryWordGenerator(DictionaryReader reader) {
        super(reader);
    }

    /**
     * Return a word of lenght between min and max lenght according to the given locale
     *
     * @return a String of lenght between min and max lenght according to the given locale
     */
    public String getWord(Integer lenght, Locale locale) {
        SizeSortedWordList words = getWordList(locale);
        //get the middle
        int firstLenght = (lenght.intValue() / 2);
        //try to find a first word
        String firstWord = null;
        for (int i = firstLenght; i < 50; i++) {
            firstWord = words.getNextWord(new Integer(firstLenght + i));
            if (firstWord != null) {
                firstWord = firstWord.substring(0, firstLenght);
                break;
            }
        }
        String secondWord = null;
        for (int i = firstLenght; i < 50; i++) {
            secondWord = words.getNextWord(new Integer(lenght.intValue()
                    - firstLenght + i));
            if (secondWord != null) {
                secondWord =
                        secondWord.substring(secondWord.length()
                                - lenght.intValue()
                                + firstLenght,
                                secondWord.length());
                break;
            }
        }

        //if first word is still null, try with a smaller int avoiding
        // infinite loop by chexking size

        firstWord = checkAndFindSmaller(firstWord, firstLenght, locale);
        secondWord = checkAndFindSmaller(secondWord, lenght.intValue()
                - firstLenght, locale);
        return firstWord + secondWord;

    }

    private String checkAndFindSmaller(String firstWord, int lenght,
                                       Locale locale) {
        //if first word is still null, try with a smaller int
        // avoiding infinite loop by chexking size
        if (firstWord == null) {
            if (lenght > 1) {
                firstWord = getWord(new Integer(lenght), locale);
            } else {
                throw new CaptchaException("No word of lenght : " +
                        lenght + " exists in dictionnary! please " +
                        "update your dictionary or your range!");
            }
        }
        return firstWord;
    }

}
