package com.spring.testing.init.springconceptsrefactor.service;

import com.spring.testing.init.springconceptsrefactor.command.TestCommand;
import com.spring.testing.init.springconceptsrefactor.contoller.TestConroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private TestCommand testCommand;
    Logger logger = LoggerFactory.getLogger(TestConroller.class);
    public TestService(TestCommand testCommand) {
        this.testCommand = testCommand;
    }

    public String callCommand(String param) throws InterruptedException {
        String response = testCommand.execute(param);
        Thread.sleep(5000);
        logger.info("service level log");
        return response;
    }
}
