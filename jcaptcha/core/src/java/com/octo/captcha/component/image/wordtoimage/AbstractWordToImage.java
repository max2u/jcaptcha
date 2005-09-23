/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.wordtoimage;

import com.octo.captcha.CaptchaException;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

/**
 * <p/>
 * Implementation skeleton for the WordToImage component </p> Basically this class implements the imageFromWord method
 * proceding as folow : <ul> <li>Checks the word lenght</li> <li>Creates an java.text.AttributedString from the
 * word</li> <li>Apply font to the AttributedString using the abstract method getFont</li> <li>Create an image for the
 * background using the abstact method getBackround</li> <li>Put the text on the backround using the abstact method
 * pasteText</li> <li>Return the newly created image</li> </ul> <p/>This class implements the Template method pattern
 * from the GOF design patterns.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue </a>
 * @version 1.0
 */
public abstract class AbstractWordToImage implements WordToImage {

    /**
     * Creates an image of the provided String This method is a skeleton for creation algorithm. it proceeds as folows :
     * <ul> <li>Checks the word lenght</li> <li>Creates an java.text.AttributedString from the word</li> <li>Apply font
     * to the AttributedString using the abstract method getFont</li> <li>Create an image for the background using the
     * abstact method getBackround</li> <li>Put the text on the backround using the abstact method pasteText</li>
     * <li>Return the newly created image</li> </ul>
     *
     * @return an image representation of the word
     *
     * @throws com.octo.captcha.CaptchaException
     *          if word is invalid or if image generation fails.
     */
    public BufferedImage getImage(String word) throws CaptchaException {
        int wordLenght;
        //check word
        wordLenght = checkWordLenght(word);
        //create attribute string from word
        AttributedString attributedWord = getAttributedString(word, wordLenght);

        //create backgound
        BufferedImage background = getBackround();
        //apply text on background
        return pasteText(background, attributedWord);

    }

    AttributedString getAttributedString(String word, int wordLenght) {
        AttributedString attributedWord = new AttributedString(word);
        //apply font to string

        for (int i = 0; i < wordLenght; i++) {
            Font font = getFont();//get the new font for next character
            //apply font to next character
            attributedWord.addAttribute(TextAttribute.FONT, font, i, i + 1);
        }
        return attributedWord;
    }

    int checkWordLenght(String word) throws CaptchaException {
        int wordLenght;
        if (word == null) {
            throw new CaptchaException("null word");
        } else {
            wordLenght = word.length();
            if (wordLenght > this.getMaxAcceptedWordLenght()
                    || wordLenght < getMinAcceptedWordLenght()) {
                throw new CaptchaException("invalid lenght word");
            }
        }
        return wordLenght;
    }

    /**
     * Method from imageFromWord method to apply font to String. Implementations must take into account the minFontSize
     * and the MaxFontSize.
     *
     * @return a Font
     */
    abstract Font getFont();

    /**
     * Generates a backround image on wich text will be paste. Implementations must take into account the imageHeigt and
     * imageWidth.
     *
     * @return the background image
     */
    abstract BufferedImage getBackround();

    /**
     * Pastes the attributed string on the backround image and return the final image. Implementation must take into
     * account the fact that the text must be readable by human and non by programs
     *
     * @return the final image
     *
     * @throws CaptchaException if any exception accurs during paste routine.
     */
    abstract BufferedImage pasteText(final BufferedImage background,
                                     final AttributedString attributedWord) throws CaptchaException;

}