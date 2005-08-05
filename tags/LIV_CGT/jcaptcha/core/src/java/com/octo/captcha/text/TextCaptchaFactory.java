/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */

package com.octo.captcha.text;

import com.octo.captcha.Captcha;
import com.octo.captcha.CaptchaFactory;

import java.util.Locale;

/**
 * <p>Implements a TextCaptcha Factory</p>.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public abstract class TextCaptchaFactory implements CaptchaFactory {

    /**
     * builds a captcha.
     *
     * @return a captcha
     */
    public final Captcha getCaptcha() {
        return getTextCaptcha();
    }

    /**
     * build Localized captcha (don't forget those captcha are for human
     * beings!).
     *
     * @param locale
     * @return a captcha corresponding to the locale
     */
    public final Captcha getCaptcha(final Locale locale) {
        return getTextCaptcha(locale);
    }

    /**
     * TextCaptcha.
     *
     * @return a Text captcha
     */
    public abstract TextCaptcha getTextCaptcha();

    /**
     * a TextCaptcha.
     *
     * @return a localized TextCaptcha
     */
    public abstract TextCaptcha getTextCaptcha(Locale locale);

}
