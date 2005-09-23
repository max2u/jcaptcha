/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.textpaster;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

/**
 * <p/>
 * Randomly pastes the attributed string twice, with a random offset. </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue </a>
 * @version 1.0
 */
public class DoubleRandomTextPaster extends RandomTextPaster {

    /**
     * @param minAcceptedWordLenght Max lenght of a word
     * @param maxAcceptedWordLenght Min lenght of a word
     * @param textColor             Unique color of text
     */
    public DoubleRandomTextPaster(Integer minAcceptedWordLenght, Integer maxAcceptedWordLenght,
                                  Color textColor) {
        super(minAcceptedWordLenght, maxAcceptedWordLenght, textColor);
    }

    /**
     * @param minAcceptedWordLenght Max lenght of a word
     * @param maxAcceptedWordLenght Min lenght of a word
     * @param colorGenerator        color generator of text
     */
    public DoubleRandomTextPaster(Integer minAcceptedWordLenght, Integer maxAcceptedWordLenght,
                                  ColorGenerator colorGenerator) {
        super(minAcceptedWordLenght, maxAcceptedWordLenght, colorGenerator);
    }

    /**
     * @param minAcceptedWordLenght Max lenght of a word
     * @param maxAcceptedWordLenght Min lenght of a word
     * @param colorGenerator        color generator of text
     * @param manageColorPerGlyph   Boolean to set if each glyph can have a new color from the color generator
     */
    public DoubleRandomTextPaster(Integer minAcceptedWordLenght, Integer maxAcceptedWordLenght,
                                  ColorGenerator colorGenerator, Boolean manageColorPerGlyph) {
        super(minAcceptedWordLenght, maxAcceptedWordLenght, colorGenerator, manageColorPerGlyph);
    }

    /**
     * Pastes the attributed string on the backround image and return the final image. Implementation must take into
     * account the fact that the text must be readable by human and non by programs. Paste the text randomly on the
     * background <
     *
     * @return the final image
     *
     * @throws com.octo.captcha.CaptchaException
     *          if any exception occurs during paste routine.
     */
    public BufferedImage pasteText(final BufferedImage background,
                                   final AttributedString attributedWord) throws CaptchaException {
        BufferedImage out = copyBackground(background);
        Graphics2D g2 = pasteBackgroundAndSetTextColor(out, background);

        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // this attribute doesn't do anything in JDK 1.4, but maybe it will in JDK 1.5
        // attributedString.addAttribute(TextAttribute.WIDTH, TextAttribute.WIDTH_EXTENDED);

        // convert string into a series of glyphs we can work with
        ChangeableAttributedString newAttrString = new ChangeableAttributedString(g2,
                attributedWord, kerning);

        // space out the glyphs with a little kerning
        newAttrString.useMinimumSpacing(kerning);
        // shift string to a random spot in the output imge
        Point2D firstTextStartingPoint = newAttrString.moveToRandomSpot(background);
        // now draw each glyph at the appropriate spot on the image.
        if (isManageColorPerGlyph()) {
            newAttrString.drawString(g2, getColorGenerator());
        } else {
            newAttrString.drawString(g2);
        }

        // now we do the same thing again, with a slight x,y shift
        newAttrString.moveToRandomSpot(background, firstTextStartingPoint);
        if (isManageColorPerGlyph()) {
            newAttrString.drawString(g2, getColorGenerator());
        } else {
            newAttrString.drawString(g2);
        }

        g2.dispose();
        return out;
    }

}
