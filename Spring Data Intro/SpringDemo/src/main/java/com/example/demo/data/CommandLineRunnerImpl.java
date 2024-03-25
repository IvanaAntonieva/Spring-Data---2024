package com.example.demo.data;

import com.example.demo.data.entities.Account;
import com.example.demo.data.entities.User;
import com.example.demo.data.service.UserService;
import com.example.demo.data.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = this.userService.findUserById(1);
        User user2 = this.userService.findUserById(2);

        this.accountService.withdrawMoney(BigDecimal.valueOf(50), 1);
    }
}
