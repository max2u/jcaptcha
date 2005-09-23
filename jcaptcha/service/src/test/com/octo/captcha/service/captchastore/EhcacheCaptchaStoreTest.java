/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.octo.captcha.service.captchastore;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

public class EhcacheCaptchaStoreTest extends CaptchaStoreTestAbstract {
    private static final String CACHE_NAME = "captchaTest";

    public CaptchaStore initStore() {

        try {
            CacheManager.getInstance().addCache(CACHE_NAME);
            return new EhcacheCaptchaStore(CacheManager.getInstance().getCache(CACHE_NAME));
        } catch (CacheException e) {
            e.printStackTrace();
            fail(e.toString());
            return null;
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        CacheManager.getInstance().removeCache(CACHE_NAME);
    }

}
