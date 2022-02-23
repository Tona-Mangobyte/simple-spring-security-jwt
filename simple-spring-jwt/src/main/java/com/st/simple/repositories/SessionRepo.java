package com.st.simple.repositories;

import com.st.simple.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, String> {
    Session findByToken(String token);
}
