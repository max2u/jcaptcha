/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.octo.captcha.service.captchastore;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.MockCaptcha;
import junit.framework.TestCase;

import java.util.Collection;

public class FastHashMapCaptchaStoreTest extends CaptchaStoreTestAbstract {

    public CaptchaStore initStore() {
        return new FastHashMapCaptchaStore();
    }

}
