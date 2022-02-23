package com.st.simple.services;

import com.st.simple.entities.Session;

public interface SessionService {
    String createSession(Session session);
    Session findByToken(String token);
    Boolean deleteSession(String token);
}
