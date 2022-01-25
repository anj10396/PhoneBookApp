package in.ashokit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.Contact;
import in.ashokit.service.ContactServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/contact")
@Api("THIS IS THE API DOCUMENTATION FOR PHONE BOOK APPLICATION")
public class ContactRestController {
	//@Autowired
	private ContactServiceImpl contactService;

	public ContactRestController(ContactServiceImpl contactService) {
		this.contactService = contactService;
	}

	@ApiOperation("THIS END POINT IS USED TO SAVE A CONTACT IN DB TABLE")
	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {

		boolean savedContact = contactService.saveContact(contact);
		if (savedContact) 
		{
			return new ResponseEntity<>("Contact Saved Successfully", HttpStatus.CREATED);
		} else
		{
			return new ResponseEntity<>("Some Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation("THIS END POINT IS USED TO GET ALL CONTACTS FROM DB")
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {
		//return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
		
		List<Contact> allContacts=contactService.getAllContacts();
		return new ResponseEntity(allContacts,HttpStatus.OK);		
	}

	@ApiOperation("THIS END POINT IS USED TO GET ONE CONTACT BY ID FROM DB")
	@GetMapping("/{contactId}")
	public ResponseEntity<?> getContactById(@PathVariable Integer contactId) {
		Contact contact = contactService.getContactById(contactId);
		if (contact != null) {
			return new ResponseEntity<>(contact, HttpStatus.OK);
		}
		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}

	@ApiOperation("THIS END POINT IS TO UPADATE A CONTACT FROM DB")
	@PutMapping
	public ResponseEntity<String> updateContact(@RequestBody Contact contact) {
		if (contact.getContactId() != null) {
			boolean savedContact = contactService.saveContact(contact);
			if (savedContact) {
				return new ResponseEntity<>("Contact updated Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Some error occured", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation("THIS END POINT IS USED TO DELETE ONE CONTACT")
	@DeleteMapping("/{contactId}")
	public ResponseEntity<String> deleteContactById(@PathVariable Integer contactId) {
		if (contactService.deleteContactById(contactId)) {
			return new ResponseEntity<>("Contact Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Some Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
