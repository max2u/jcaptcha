/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * Copyright (c) 2005 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service;

import java.util.Locale;

/**
 * <p>Main interface of the package. Used by client applications to expose Captchas Challenge validate the response in a
 * transparent and easy way. The flow of operations for using this service is : <ul> <li>Call the getQuestionForID
 * method to retrive a challenge and present it to the final user.(could be localized)</li> <li>Call the
 * getChallengeForID method to retrive a challenge and present it to the final user.(could be localized)</li> <li>Call
 * the validateResponseForID method to know if the final user is a human or not.</li> </ul>
 * <p/>
 * Developpers should implement this interface using the following rules : <br/> When the getChallengeForID method is
 * called, If no captcha exist for this id, create a new captcha return the challenge.
 * <p/>
 * else if the getChallenge method has been called on the stored captcha, generate a new captcha, else return this
 * captcha challenge.
 * <p/>
 * <br/> The getQuestionForId should be called after the getChallenge (because this generate a new captcha), and must
 * first see if a captcha is associated with this ID : If no, associates a new one. When the getQuestionForId method is
 * called return the question <br/> When the validateResponseForID method is called. Throw a CaptchaServiceException if
 * the ID is invalid else return a boolean, and free the ID (remove the captcha). <br/> All method may throw a
 * CaptchaServiceException if an error occurs during Captcha Generation. </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public interface CaptchaService {


    /**
     * Method to retrive the challenge corresponding to the given ticket.
     *
     * @param ID ticket
     *
     * @return the challenge
     *
     * @throws CaptchaServiceException if the ticket is invalid
     */
    Object getChallengeForID(String ID) throws CaptchaServiceException;

    /**
     * Method to retrive the challenge corresponding to the given ticket.
     *
     * @param ID     ticket
     * @param locale the desired localized capthca
     *
     * @return the localized challenge
     *
     * @throws CaptchaServiceException if the ticket is invalid
     */
    Object getChallengeForID(String ID, Locale locale) throws CaptchaServiceException;

    /**
     * Method to retrive the question corresponding to the given ticket.
     *
     * @param ID ticket
     *
     * @return the question
     *
     * @throws CaptchaServiceException if the ticket is invalid
     */
    String getQuestionForID(String ID) throws CaptchaServiceException;

    /**
     * Method to retrive the question corresponding to the given ticket.
     *
     * @param ID     ticket
     * @param locale the desired localized capthca
     *
     * @return the localized question
     *
     * @throws CaptchaServiceException if the ticket is invalid
     */
    String getQuestionForID(String ID, Locale locale) throws CaptchaServiceException;


    /**
     * Method to validate a response to the challenge corresponding to the given ticket.
     *
     * @param ID ticket
     *
     * @return true if the response is correct, false otherwise.
     *
     * @throws CaptchaServiceException if the ticket is invalid
     */
    Boolean validateResponseForID(String ID, Object response) throws CaptchaServiceException;
}