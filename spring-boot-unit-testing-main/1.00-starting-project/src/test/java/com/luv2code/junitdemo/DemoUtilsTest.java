package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
* @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // para que los test se muestren con espacios en vez de guiones bajos
*
* */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // para ordenar la ejecución de los test con @Order(n)
public class DemoUtilsTest  {
    DemoUtils demoUtils;

    // se ejecuta antes de cada test
    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("Running a test...");
    }

    // se ejecuta despues de cada test
    @AfterEach
    void afterBeforeEach() {
        System.out.println("Test finished...");
    }

    // se ejecuta antest de finalizar todos los test
    @BeforeAll
    static void beforeAll() {
        System.out.println("This needs to run before all test cases");
    }

    // se ejecuta al finalizar todos los test
    @AfterAll
    static void afterAll() {
        System.out.println("This needs to run after all test cases");
    }

    @Test
    @DisplayName("Equals and Not Equals") // para renombrar el test
    void testEqualsNotEquals() {
    //void test_Equals_Not_Equals() { // si no usamos @DisplayName, podemos usar guiones bajos en vez de espacios
        DemoUtils demoUtils = new DemoUtils();

        // comprueba si son iguales
        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6");
        assertNotEquals(6, demoUtils.add(1, 9), "1 + 9 must not be 6");

    }

    @Test
    @DisplayName("Null and Not Null")
    void testNullAndNotNull() {
        DemoUtils demoUtils = new DemoUtils();

        // comprubea nulos
        assertNull(demoUtils.checkNull(null), "Object should be null");
        assertNotNull(demoUtils.checkNull(new Object()), "Object should not be null");
    }

    @Test
    @DisplayName("Same and Not Same objects")
    @Order(1) // para ordenar la ejecución de los test
    void testSameAndNotSame() {
        DemoUtils demoUtils = new DemoUtils();
        // comprueba si son el mismo objeto, la comparación se hace por referencia(punteros)
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should be the same");
        assertNotSame(demoUtils.getAcademy(), new String("Luv2Code Academy"), "Objects should not be the same");
    }

    @Test
    @DisplayName("True and False")
    @Order(0) // entre menor sea el número, antes se ejecuta el test
    void testTrueFalse() {
        DemoUtils demoUtils = new DemoUtils();
        // comprueba si es true o false
        assertTrue(demoUtils.isGreater(6, 5), "6 is greater than 5");
        assertFalse(demoUtils.isGreater(5, 6), "5 is not greater than 6");
    }

    @Test
    @DisplayName("Array Equals")
    void testArrayEquals() {
        DemoUtils demoUtils = new DemoUtils();
        // comprueba si dos arreglos de String son iguales en cuanto a valores y orden
        assertArrayEquals(new String[]{"A", "B", "C"}, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }

    @Test
    @DisplayName("Iterable equals")
    void testIterableEquals() {
        DemoUtils demoUtils = new DemoUtils();
        List<String> theList = List.of("luv", "2", "code");
        // comprueba si dos listas(objetos iterables) son iguales en cuanto a valores y orden
        assertIterableEquals(theList, demoUtils.getAcademyInList(), "Lists should be the same");
    }

    @Test
    @DisplayName("Lines match")
    void testLinesMatch() {
        DemoUtils demoUtils = new DemoUtils();
        List<String> theList = List.of("luv", "2", "code");
        // comprueba si dos listas(objetos iterables) se le puede poner expresiones regulares para comparar textos
        //assertLinesMatch(theList, List.of("LUV", "2", "CODE"), "Lines should match");
        assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
    }

    @Test
    @DisplayName("Throws and Does Not Throw")
    void testThrowsAndDoesNotThrow() {
        DemoUtils demoUtils = new DemoUtils();
        // comprueba si una función lanza una excepción o no
        assertThrows(Exception.class, () -> demoUtils.throwException(-1), "Should throw exception" );
        assertDoesNotThrow(() -> demoUtils.throwException(1), "Should not throw exception" );
    }

    @Test
    @DisplayName("Timeout")
    void testTimeout() {
        DemoUtils demoUtils = new DemoUtils();
        // comprueba si una función se ejecuta en un tiempo determinado, en esta caso menos de 3 segundos
        // si se pasa del tiempo, el test falla
        assertTimeoutPreemptively(java.time.Duration.ofSeconds(3), () -> demoUtils.checkTimeout(), "Method should execute in 3 seconds" );
    }

    @Test
    @DisplayName("Multiply")
    void testMultiply() {
        DemoUtils demoUtils = new DemoUtils();
        assertAll(
                () -> assertEquals(4, demoUtils.multiply(2, 2)),
                () -> assertEquals(0, demoUtils.multiply(2, 0)),
                () -> assertEquals(-2, demoUtils.multiply(2, -1))
        );}

}
