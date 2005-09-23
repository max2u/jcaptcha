/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */
package com.octo.captcha.component.sound.wordtosound;

import com.octo.captcha.component.sound.soundconfigurator.SoundConfigurator;

import javax.sound.sampled.AudioInputStream;

/**
 * WordToSound abstract implementation
 *
 * @author Benoit
 * @version 1.0
 */
public abstract class AbstractWordToSound implements WordToSound {
    protected int maxAcceptedWordLenght;

    protected int minAcceptedWordLenght;

    protected SoundConfigurator configurator = null;

    /**
     * Constructor with a configurator
     *
     * @param voiceName             Voice Name to be use to produce the sound by default (with getSound())
     * @param voicePackages         Voice Packages where voices are defined see WordToSoundFreeTTS.defaultVoicePackage
     * @param minAcceptedWordLenght Lenght Minimal of generated words
     * @param maxAcceptedWordLenght Lenght Maximal of generated words
     */
    public AbstractWordToSound(SoundConfigurator configurator, int minAcceptedWordLenght,
                               int maxAcceptedWordLenght) {
        this.configurator = configurator;
        this.minAcceptedWordLenght = minAcceptedWordLenght;
        this.maxAcceptedWordLenght = maxAcceptedWordLenght;
    }

    public int getMaxAcceptedWordLenght() {
        return maxAcceptedWordLenght;
    }

    public int getMinAcceptedWordLenght() {
        return minAcceptedWordLenght;
    }

    /**
     * Add effect to the sound
     *
     * @param sound The sound to modify
     *
     * @return The modified sound.
     */
    protected abstract AudioInputStream addEffects(AudioInputStream sound);
}
