package za.co.zamambo.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.zamambo.Entity.Contact;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact> {

}
