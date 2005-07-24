/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.octo.captcha.service.captchastore;

import org.apache.commons.collections.FastHashMap;

/**
 * <p><ul><li></li></ul></p>
 *
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class FastHashMapCaptchaStore extends MapCaptchaStore {
    public FastHashMapCaptchaStore() {
        this.store = new FastHashMap();
    }
}
