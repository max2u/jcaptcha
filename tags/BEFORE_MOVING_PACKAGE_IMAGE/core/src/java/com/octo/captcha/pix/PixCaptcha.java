/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 */


package com.octo.captcha.pix;

import com.octo.captcha.Captcha;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>String question about a BufferedImage challenge answered via string.</p>
 * @author <a href="mailto:mag@octo.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class PixCaptcha implements Captcha, Serializable {

    private String question;

    private BufferedImage challenge;

    private String response;

    /**
     * Constructor of a PixCatpcha.
     * Captcha shouldn't be directly constructed.
     * It should be built from the factory.
     * @param question
     * @param challenge
     * @param response
     */
    public PixCaptcha(String question, BufferedImage challenge, String response) {
        this.question = question;
        this.challenge = challenge;
        this.response = response;
    };


    /**
     * Accessor captcha question
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @return the challenge
     */
    public Object getChallenge() {
        return getPixChallenge();
    }

    /**
     * Validation routine from the CAPTCHA interface. this methods verify if the response is not null and a String
     * and then compares the given response to the internal string.
     * @param response
     * @return true if the given response equals the internal response, false otherwise.
     */
    public Boolean validateResponse(Object response) {
        return (null != response && response instanceof String) ? validateResponse((String) response) : Boolean.FALSE;
    }

    /**
     *
     * @return the pix challenge
     */
    public BufferedImage getPixChallenge() {
        return challenge;
    };

    /**
     * Very simple validation routine that compares the given response to the internal string.
     * @return true if the given response equals the internal response, false otherwise.
     */
    private Boolean validateResponse(String response) {
        return Boolean.valueOf(response.equals(this.response));
    };

    public String getResponse() {
        return response;
    }

    /**
     * Dispose the challenge, once this method is call the getChallenge method will return null.<br>
     * It has been added for technical reasons : a captcha is always used in a two step fashion<br>
     * First submit the challenge, and then wait until the response arrives.<br>
     * It had been asked to have a method to dispose the challenge that is no longer used after being dipslayed.
     * So here it is!
     *
     */
    public void disposeChallenge() {
        this.challenge = null;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        //write serializable values
        out.writeUTF(question);
        out.writeUTF(response);
        //write image as jpeg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(this.getPixChallenge());


    }

    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        //read serializable values
        this.question = in.readUTF();
        this.response = in.readUTF();
        // read image as JPEG object
        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
        this.challenge = (BufferedImage) decoder.decodeAsBufferedImage();
    }

}
