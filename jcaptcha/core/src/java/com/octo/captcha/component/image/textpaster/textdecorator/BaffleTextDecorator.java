/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.octo.captcha.component.image.textpaster.textdecorator;

import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.textpaster.ChangeableAttributedString;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.Random;

/**
 * <p/>
 * text decorator that paint holes on the string (erase some parts)
 * </p>
 * You may specify the number of holes per glyph : 3 by default. You may specify the color of holes :
 * white by default.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue </a>
 * @version 1.0
 * @see {http://www.parc.xerox.com/research/istl/projects/captcha/default.html}
 */
public class BaffleTextDecorator implements TextDecorator {

    private Random myRandom = new Random();

    /**
     * circleXRatio
     */
    private static double circleXRatio = 0.7;

    /**
     * circleYRatio
     */
    private static double circleYRatio = 0.5;

    /**
     * Number of holes per glyph. Default : 3
     */
    private Integer numberOfHolesPerGlyph = new Integer(3);

    /**
     * ColorGenerator for the holes
     */
    private ColorGenerator holesColorGenerator = null;


    /**
     * @param numberOfHolesPerGlyph
     * @param holesColor            Color of the holes
     */
    public BaffleTextDecorator(Integer numberOfHolesPerGlyph, Color holesColor) {
        this.numberOfHolesPerGlyph = numberOfHolesPerGlyph != null ? numberOfHolesPerGlyph
                : this.numberOfHolesPerGlyph;
        this.holesColorGenerator = new SingleColorGenerator(holesColor != null ? holesColor
                : Color.white);
    }

    /**
     * @param numberOfHolesPerGlyph Number of holes around glyphes
     * @param holesColorGenerator   The colors for holes
     */
    public BaffleTextDecorator(Integer numberOfHolesPerGlyph, ColorGenerator holesColorGenerator) {
        this.numberOfHolesPerGlyph = numberOfHolesPerGlyph != null ? numberOfHolesPerGlyph
                : this.numberOfHolesPerGlyph;
        this.holesColorGenerator = holesColorGenerator != null ? holesColorGenerator
                : new SingleColorGenerator(Color.white);
    }


    public void decorateSAttributedString(Graphics2D g2, AttributedString attributedWord, ChangeableAttributedString newAttrString) {


        g2.setColor(holesColorGenerator.getNextColor());

        for (int j = 0; j < attributedWord.getIterator().getEndIndex(); j++) {
            Rectangle2D bounds = newAttrString.getBounds(j).getFrame();
            double circleMaxSize = (double) bounds.getWidth() / 2;
            for (int i = 0; i < numberOfHolesPerGlyph.intValue(); i++) {
                double circleSize = circleMaxSize * (1 + myRandom.nextDouble()) / 2;
                double circlex = bounds.getMinX() + bounds.getWidth() * circleXRatio
                        * myRandom.nextDouble();
                double circley = bounds.getMinY() - bounds.getHeight() * circleYRatio
                        * myRandom.nextDouble();
                Ellipse2D circle = new Ellipse2D.Double(circlex, circley, circleSize, circleSize);
                g2.fill(circle);
            }
        }
    }
}

