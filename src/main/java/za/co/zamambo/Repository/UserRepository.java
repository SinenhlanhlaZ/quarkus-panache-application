package za.co.zamambo.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.zamambo.Entity.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
