package com.duact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duact.Entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
