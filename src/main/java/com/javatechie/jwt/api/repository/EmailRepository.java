package com.javatechie.jwt.api.repository;

import com.javatechie.jwt.api.entity.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel,Long> {
}
