package za.co.zamambo.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.zamambo.Entity.Address;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
}
