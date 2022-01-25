package in.ashokit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.model.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {

}
