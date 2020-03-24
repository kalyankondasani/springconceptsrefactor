package com.spring.testing.init.springconceptsrefactor.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.testing.init.springconceptsrefactor.models.CustomException;
import com.spring.testing.init.springconceptsrefactor.models.CustomValidator;
import com.spring.testing.init.springconceptsrefactor.models.Employee;
import com.spring.testing.init.springconceptsrefactor.models.Greeting;
import com.spring.testing.init.springconceptsrefactor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestConroller {
    private CustomValidator customValidator;
    private TestService testService;

    Logger logger = LoggerFactory.getLogger(TestConroller.class);


    @Autowired
    public TestConroller(CustomValidator customValidator, TestService testService) {
        this.customValidator = customValidator;
        this.testService = testService;
    }



    @GetMapping(value = "/testing")

    @ResponseBody
    public ResponseEntity<String> greeting(@RequestParam(value = "name", required = false) String name) throws CustomException, InterruptedException {
        Greeting greeting = new Greeting();
        greeting.setValue(name);
        customValidator.validateRequest(greeting);

        logger.info("request cmae to controller");
        String response = testService.callCommand(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static void main(String[] args) throws IOException {
        List<Employee> employeeList =  new ArrayList<>();

        Map<Integer, String> employeeNameMap = new HashMap<>();
        employeeNameMap.put(1, "Kalyan");
        employeeNameMap.put(2, "Ravi");
        employeeNameMap.put(3, "Madhavi");
        employeeNameMap.put(4, "KNMR");
        employeeNameMap.put(5, "Ajay");
        employeeNameMap.put(6, "Movva");
        employeeNameMap.put(7, "CNU");
        employeeNameMap.put(8, "RK");
        employeeNameMap.put(9, "Sundar");
        employeeNameMap.put(10, "Sai");


        for(int i=1; i<=10; i++) {

            Employee employeeOne = new Employee();
            employeeOne.setEmployeeId(323221 + i);
            employeeOne.setEmployeeName(employeeNameMap.get(i));
            employeeOne.setVerificationId(323231l + i);
            employeeList.add(employeeOne);
        }

        List<Employee> filteredList = new ArrayList<>();
        filteredList = employeeList.stream().filter(e -> e.getEmployeeName().contains("K")).collect(Collectors.toList());
        Boolean value = employeeList.stream().anyMatch(e -> e.getEmployeeName().contains("Z"));
        if(value) {
            System.out.println("Yes it's there");
        }

        System.out.println("Filtered List : " + filteredList);


        System.out.println("Employees list is : " + employeeList);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/TestingEmployee.json"), employeeList);

        ObjectMapper objectMapper1 = new ObjectMapper();
        String valueAsString = objectMapper1.writeValueAsString(employeeList);
        System.out.println("The value of the object is: + " + valueAsString);

    }
}
