/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word.wordgenerator;

import java.util.Locale;

/**
 * <p>Description: dummy word generator contructed with a String returning the same string, with right length</p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class DummyWordGenerator implements WordGenerator {

    private String word = "JCAPTCHA";

    public DummyWordGenerator(String word) {
        this.word = word == null || "".equals(word) ? this.word : word;
    }

    /**
     * Return a word of lenght between min and max lenght
     *
     * @return a String of lenght between min and max lenght
     */
    public String getWord(Integer lenght) {
        int mod = lenght.intValue() % word.length();
        String cut = "";
        int mul = (lenght.intValue() - mod) / word.length();
        if (mod > 0) {
            cut = word.substring(0, mod);
        }
        StringBuffer returned = new StringBuffer();
        for (int i = 0; i < mul; i++) {
            returned.append(word);
        }
        returned.append(cut);
        return returned.toString();
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
