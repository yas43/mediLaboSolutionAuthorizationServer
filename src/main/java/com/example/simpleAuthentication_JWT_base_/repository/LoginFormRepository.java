package com.example.simpleAuthentication_JWT_base_.repository;

import com.example.simpleAuthentication_JWT_base_.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface LoginFormRepository extends JpaRepository<LoginForm,Integer> {
    Optional<LoginForm>findByUsername(String username);
}
