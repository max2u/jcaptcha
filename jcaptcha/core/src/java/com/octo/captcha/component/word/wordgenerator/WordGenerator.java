/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word.wordgenerator;

import java.util.Locale;

/**
 * <p>This interface defines methods to retrieve random words </p>.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public interface WordGenerator {

    /**
     * Return a word of lenght between min and max lenght
     *
     * @return a String of lenght between min and max lenght
     */
    String getWord(Integer lenght);

    /**
     * Return a word of lenght between min and max lenght according to the given locale
     *
     * @param lenght the word lenght
     *
     * @return a String of lenght between min and max lenght according to the given locale
     */
    String getWord(Integer lenght, Locale locale);

}
