/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.image;


import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;

/**
 * <p>Default service implementation : use a ehCache as captcha store, and a DefaultGimpyEngine </p> It is initialized
 * with thoses default values : <ul> <li>min guaranted delay : 180s </li> <li>max store size : 100000 captchas </li>
 * <li>max store size before garbage collection : non applicable </li> </ul>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class DefaultManageableImageCaptchaService extends EhcacheManageableImageCaptchaService
        implements ImageCaptchaService {

    public DefaultManageableImageCaptchaService() {
        super(new DefaultGimpyEngine(), 180,
                100000, "image");
    }


    public DefaultManageableImageCaptchaService(String cacheName) {
        super(new DefaultGimpyEngine(), 180,
                100000, cacheName);
    }


}
