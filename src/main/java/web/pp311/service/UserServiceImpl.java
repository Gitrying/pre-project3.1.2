package web.pp311.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.pp311.dao.UserDao;
import web.pp311.model.User;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {this.userDao = userDao;}

    @Override
    @Transactional(readOnly = true)
    public List<User> allUsers() {return userDao.allUsers();}

    @Override
    public void add(User user) {userDao.add(user);}

    @Override
    public void delete(int id) {userDao.delete(id);}

    @Override
    public void edit(User user, int id) {userDao.edit(user, id);}

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {return userDao.getById(id);}
}