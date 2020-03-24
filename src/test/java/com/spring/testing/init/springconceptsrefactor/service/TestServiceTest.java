package com.spring.testing.init.springconceptsrefactor.service;

import com.spring.testing.init.springconceptsrefactor.command.TestCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {


    @Test
    public void testService() throws InterruptedException {
        TestCommand testCommand = Mockito.mock(TestCommand.class);
        TestService testService = new TestService(testCommand);

        Mockito.when(testCommand.execute(Mockito.anyString())).thenReturn("testing response");
        String callCommand = testService.callCommand("");
        Assertions.assertThat(callCommand).isEqualTo("testing response");


    }
}