/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.sound.speller;

import junit.framework.TestCase;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.sound.WordToSoundMock;

public class SoundSpellerFactoryTest extends TestCase {

    public void testSpellerSoundFactory() throws Exception {
        try {
            new SpellerSoundFactory(null, null, null);
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
        try {
            new SpellerSoundFactory(new RandomWordGenerator("a"), null, null);
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }

        try {
            new SpellerSoundFactory(null, new WordToSoundMock(), null);
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
    }

}
