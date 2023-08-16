package com.picpay_challenge.controller;

import com.picpay_challenge.domain.user.User;
import com.picpay_challenge.dto.CreateUserDTO;
import com.picpay_challenge.dto.DetailsUserDTO;
import com.picpay_challenge.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsUserDTO> createUser(@RequestBody CreateUserDTO userData) {
        User newUser = userService.createUser(userData);
        System.out.println(newUser.getEmail());
        return new ResponseEntity<DetailsUserDTO>(new DetailsUserDTO(newUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DetailsUserDTO>> getAllUsers(@PageableDefault() Pageable pageable) {
        Page<DetailsUserDTO> users = userService.getAllUsers(pageable).map(DetailsUserDTO::new);
        return new ResponseEntity<Page<DetailsUserDTO>>(users, HttpStatus.OK);
    }
}
