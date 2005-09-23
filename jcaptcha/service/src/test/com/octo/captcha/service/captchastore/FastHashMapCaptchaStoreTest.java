/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.octo.captcha.service.captchastore;

public class FastHashMapCaptchaStoreTest extends CaptchaStoreTestAbstract {

    public CaptchaStore initStore() {
        return new FastHashMapCaptchaStore();
    }

}
