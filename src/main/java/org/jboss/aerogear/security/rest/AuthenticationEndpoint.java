/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.aerogear.security.rest;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.CredentialBuilder;
import org.jboss.aerogear.security.rest.http.AuthenticationRequest;
import org.jboss.aerogear.security.rest.http.AuthenticationResponse;
import org.jboss.aerogear.security.rest.http.UserInfo;
import org.jboss.aerogear.security.util.HttpResponseBuilder;
import org.jboss.aerogear.security.util.Secret;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.IdentityManager;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>JAX-RS Endpoint to authenticate users using otp.</p>
 *
 * @author anil saldhana
 * @author Pedro Silva
 */
@Stateless
@Path("/auth")
@TransactionAttribute
public class AuthenticationEndpoint {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private CredentialBuilder credentialBuilder;

    @Inject
    private IdentityManager identityManager;

    @Inject
    private PicketBoxIdentity identity;

    @Inject
    private HttpResponseBuilder httpResponseBuilder;

    @Inject
    private Secret secret;

    /**
     * <p>Performs the authentication using the informations provided by the {@link org.jboss.aerogear.security.rest.http.AuthenticationRequest}</p>
     *
     * @param authcRequest
     * @return
     */
    @Path("/otp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse login(final AuthenticationRequest authcRequest) {

        credentialBuilder.otpCredential(authcRequest);
        authenticationManager.login(authcRequest);

        return httpResponseBuilder.createResponse(authcRequest);
    }

    /**
     * <p>Performs the authentication using the informations provided by the {@link org.jboss.aerogear.security.rest.http.AuthenticationRequest}</p>
     *
     * @param authcRequest
     * @return
     */
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse signin(final AuthenticationRequest authcRequest) {

        credentialBuilder.simpleCredential(authcRequest);
        authenticationManager.login(authcRequest);

        return httpResponseBuilder.createResponse(authcRequest);
    }

    @Path("/logout")
    @GET
    public void logout() {
        authenticationManager.logout();
    }

    @Path("/otp/secret")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo getInfo() {
        return httpResponseBuilder.buildUserInfoResponse(secret.generate());
    }

    @Path("/userinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo getUserInfo() {
        return httpResponseBuilder.buildUserInfoResponse();
    }
}