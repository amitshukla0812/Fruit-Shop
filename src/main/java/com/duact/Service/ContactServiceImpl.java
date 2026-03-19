package com.duact.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.Entity.Contact;
import com.duact.Repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Override
    public void saveContact(Contact contact) {
        contactRepo.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

	@Override
	public void deleteContact(Long id) {
		contactRepo.deleteById(id);
		
	}
}
