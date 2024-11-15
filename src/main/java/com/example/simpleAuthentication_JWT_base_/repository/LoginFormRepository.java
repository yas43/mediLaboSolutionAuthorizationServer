package com.example.simpleAuthentication_JWT_base_.repository;

import com.example.simpleAuthentication_JWT_base_.model.*;
import org.springframework.data.jpa.repository.*;

public interface LoginFormRepository extends JpaRepository<LoginForm,Integer> {
}
