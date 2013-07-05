/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.security.exception;

import org.jboss.resteasy.spi.UnauthorizedException;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.jboss.aerogear.security.exception.HttpStatus.AUTHENTICATION_FAILED;

@Provider
public class HttpExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException exception) {

        Exception e = exception.getCausedByException();

        if (e instanceof UnauthorizedException) {
            return Response.status(AUTHENTICATION_FAILED.getCode())
                    .entity(AUTHENTICATION_FAILED.toString())
                    .build();
        } else {
            return Response.status(BAD_REQUEST).build();
        }
    }
}
