package com.mockitodemo.test;

import com.mockitodemo.CalculatorService;
import com.mockitodemo.MockitoExample;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

public class MockitoExampleTest {

    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    MockitoExample mockitoExample = new MockitoExample();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    //UC-1
    @Test
    public void givenTwoNumbersAddition_WhenProper_ShouldReturnCorrectAddition() {
        //add the behavior of calc service to add two numbers
        mockitoExample.setCalculatorService(calcService);
        when(calcService.add(10.0, 20.0)).thenReturn(30.0);

        //test the add functionality
        Assert.assertEquals(mockitoExample.add(10.0, 20.0), 30.0, 0);
        verify(calcService, times(1)).add(10.0, 20.0);
        verify(calcService, never()).multiply(10.0, 20.0);
    }

    //UC-2
    @Test
    public void givenTwoNumbersSubtraction_WhenProper_ShouldReturnCorrectAddition(){
        //add the behavior of calc service to add two numbers
        mockitoExample.setCalculatorService(calcService);
        when(calcService.subtract(10.0,20.0)).thenReturn(10.0);

        //test the add functionality
        Assert.assertEquals(mockitoExample.subtract(10.0, 20.0),10.0,0);
        verify(calcService, times(1)).subtract(10.0, 20.0);
        verify(calcService, never()).divide(10.0,20.0);
    }

    //UC-3
    @Test(expected = RuntimeException.class)
    public void givenTwoNumbersDivision_WhenDivideByZero_ShouldThrowException(){
        //add the behavior to throw exception
        doThrow(new ArithmeticException())
                .when(calcService).divide(10.0,0.0);

        //test the add functionality
        Assert.assertEquals(calcService.divide(10.0, 0.0),new ArithmeticException());
    }
}