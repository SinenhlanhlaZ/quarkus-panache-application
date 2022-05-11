package za.co.zamambo.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import za.co.zamambo.Entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public List<User> findByLastName(String lastname)
    {
        String query = "SELECT firstName, lastName FROM tbl_users";
        return list(query, lastname);
    }

    public String createUser(String fname, String lname)
    {
        String query = "INSERT INTO tbl_users (firstName, lastNa me) VALUES (?, ?)";
        return "Entered user:" + fname + lname;
    }
}
