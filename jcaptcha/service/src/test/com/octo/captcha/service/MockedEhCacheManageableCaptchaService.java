/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */
package com.octo.captcha.service;

import com.octo.captcha.Captcha;

/**
 * User: mag Date: 17 oct. 2004 Time: 12:47:51
 */
public class MockedEhCacheManageableCaptchaService extends EhcacheManageableCaptchaService {

    protected MockedEhCacheManageableCaptchaService(com.octo.captcha.engine.CaptchaEngine captchaEngine,

                                                    int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize) {
        super(captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize);
    }

    /**
     * This method must be implemented by sublcasses and : Retrieve the challenge from the captcha Make and return a
     * clone of the challenge Return the clone It has be design in order to let the service dipose the challenge of the
     * captcha after rendering. It should be implemented for all captcha type (@see ImageCaptchaService implementations
     * for exemple)
     *
     * @return a Challenge Clone
     */
    protected Object getChallengeClone(Captcha captcha) {
        return new String(captcha.getChallenge().toString()) + MockedCaptchaService.CLONE_CHALLENGE;
    }

}
