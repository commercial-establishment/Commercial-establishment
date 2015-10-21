package kz.hts.ce.service;

import kz.hts.ce.entity.User;
import kz.hts.ce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepository> {

    @Autowired
    protected UserService(UserRepository repository) {
        super(repository);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User login(String username, String password){
        User user = findByUsername(username);

        return null;
    }
}
