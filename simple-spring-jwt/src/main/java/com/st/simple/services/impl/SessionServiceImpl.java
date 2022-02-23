package com.st.simple.services.impl;

import com.st.simple.entities.Session;
import com.st.simple.repositories.SessionRepo;
import com.st.simple.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepo sessionRepo;

    @Transactional
    @Override
    public String createSession(Session session) {
        sessionRepo.save(session);
        return session.getToken();
    }

    @Override
    public Session findByToken(String token) {
        Session session = sessionRepo.findByToken(token);
        if (session == null) {
            throw new RuntimeException("token incorrect");
        }
        return session;
    }

    @Transactional
    @Override
    public Boolean deleteSession(String token) {
        Session session = sessionRepo.findByToken(token);
        sessionRepo.delete(session);
        return true;
    }
}
