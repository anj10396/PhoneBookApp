package in.ashokit.service;

import java.util.List;

import in.ashokit.model.Contact;

public interface IContactService {
	public boolean saveContact(Contact contact);
	public List<Contact> getAllContacts();
	public Contact getContactById(Integer id);
	public boolean deleteContactById(Integer id);
}
