package org.jboss.aerogear.security.spi;

import org.apache.deltaspike.security.api.User;

public interface AuthenticationProvider {
    public boolean hasRole(User user, String param);
}