package com.picpay_challenge.service;

import com.picpay_challenge.domain.user.User;
import com.picpay_challenge.domain.user.UserType;
import com.picpay_challenge.dto.CreateUserDTO;
import com.picpay_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.STORE) {
            throw new Exception("Usuário lojista não está autorizado a realizar transação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Usuário não tem saldo suficiente para realizar transação");
        }
    }

    public User findUserById(Long id) {
        return this.userRepository.getReferenceById(id);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(CreateUserDTO userData) {
        User newUser = new User(userData);
        this.saveUser(newUser);
        return newUser;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
