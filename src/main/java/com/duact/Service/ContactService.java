package com.duact.Service;

import java.util.List;
import com.duact.Entity.Contact;

public interface ContactService {

    void saveContact(Contact contact);

    List<Contact> getAllContacts();

	void deleteContact(Long id);
}
