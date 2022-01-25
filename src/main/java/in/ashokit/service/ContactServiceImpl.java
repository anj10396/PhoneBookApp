package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dao.IContactRepository;
import in.ashokit.model.Contact;
@Service
public class ContactServiceImpl implements IContactService {
//@Autowired
	private  IContactRepository contactRepository;
	
	
	public ContactServiceImpl(IContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public boolean saveContact(Contact contact) {
		Contact saveContact=contactRepository.save(contact);
		if(saveContact!=null)
		return true;
		else
			return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contacts=contactRepository.findAll();
					return contacts;
				}

	@Override
	public Contact getContactById(Integer id) {
		Optional<Contact> opt=contactRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			return null;
		
	}

	@Override
	public boolean deleteContactById(Integer id) {
		contactRepository.deleteById(id);
		return true;
	}

}
