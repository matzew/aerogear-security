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
package org.jboss.aerogear.security.util;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;

/**
 * Small utility class to validate PKCS12
 * <p>
 * <b>Note:</b>
 * This class do not offer a deep level of security or check a trust chain of a certificate.
 * It only if the file provided is a valid PKCS12
 * </p>
 */
public final class PKCS12Util {

    private static final String ALGORITHM = "PKCS12";

    private PKCS12Util(){}

    /**
     * Check if the file provide is PKCS12
     * @param cert certificate to be validated
     * @param pass password to be provided
     * @throws CertificateException to indicate an invalid certificate
     */
    public static void validate(byte[] cert, String pass) throws Exception {

        try {
            KeyStore keyStore = KeyStore.getInstance(ALGORITHM);
            keyStore.load(new ByteArrayInputStream(cert), pass.toCharArray());
        } catch (Exception e) {
            throw new Exception("Certificate is not valid!", e);
        }
    }
}
