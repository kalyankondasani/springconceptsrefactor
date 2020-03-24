package com.spring.testing.init.springconceptsrefactor.scenarios;


public class TestScenario {

    private static String field1;
    private static String field2;

    private Boolean equals() {

        if(field1.equals(field2)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }



    public static void main(String[] args) {
        TestScenario testScenario = new TestScenario();
        field1 = new String("Kalyan");
        field2 = new String("Kalyan");
        Boolean equals = testScenario.equals();
        System.out.println("The response is :" + equals.toString());

    }

}
