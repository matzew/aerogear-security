/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.security.authz;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates classes or methods to indicate accessing the class (or the method),
 * is restricted to a "user" that matches the given roles:
 * <pre>
 *     @Path("/myApp")
 *     @Secure({"admin"})
 *     public class SomeRestEndpoint {
 *         ...
 *     }
 * </pre>
 * 
 * <b>Note:</b> You need to enable this annotation by adding the {@link SecurityInterceptor}
 * in your <code>beans.xml</code> file:
 * <pre>
 *     ...
 *     &lt;interceptors&gt;
 *         &lt;class&gt;org.jboss.aerogear.security.interceptor.SecurityInterceptor&lt;/class&gt;
 *     &lt;/interceptors&gt;
 *   ...
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.METHOD })
@InterceptorBinding
public @interface Secure {

    /**
     * List of roles that are allowed to access the annotated resource.
     */
    @Nonbinding
    String[] value();
}
