package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    // if the number is divisible by 3, return "Fizz"
    // if the number is divisible by 5, return "Buzz"
    // if the number is divisible by both 3 and 5, return "FizzBuzz"
    // if the number is not divisible by either 3 or 5, return the number

    @Test
    @DisplayName("Divisible by 3")
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";
        //fail("errror"); // para obligar a una prueba a fallar
        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    @Test
    @DisplayName("Divisible by 5")
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @Test
    @DisplayName("Divisible by 3 and 5")
    @Order(3)
    void testForDivisibleByThreeAndFive(){
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @Test
    @DisplayName("Not Divisible by 3 or 5")
    @Order(4)
    void testForNotDivisibleByThreeOrFive(){
        String expected = "1";
        assertEquals(expected, FizzBuzz.compute(1), "Should return 1");
    }

    /*
    * prueba parametrizada que lee los datos de un archivo csv
    * el archivo csv debe estar en src/test/resources
    * el archivo csv debe tener dos columnas: la primera columna es el valor de entrada,
    * la segunda columna es el valor esperado
    * el archivo csv no debe tener encabezado.
    *
    * esta funcion es como un bucle que prueba todos los valores del archivo csv
    * */
    @DisplayName("Tessting with small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources= "/small-test-data.csv")
    @Order(5)
    void testWithSmallDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value), "Should return " + expected);
    }

    @DisplayName("Tessting with Medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources= "/medium-test-data.csv")
    @Order(5)
    void testWithMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value), "Should return " + expected);
    }

    @DisplayName("Tessting with Large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources= "/large-test-data.csv")
    @Order(5)
    void testWithLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value), "Should return " + expected);
    }
}
