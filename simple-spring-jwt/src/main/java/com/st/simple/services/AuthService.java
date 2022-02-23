package com.st.simple.services;

import com.st.simple.validators.AuthValidator;

public interface AuthService {
    String authentication(AuthValidator auth);
}
