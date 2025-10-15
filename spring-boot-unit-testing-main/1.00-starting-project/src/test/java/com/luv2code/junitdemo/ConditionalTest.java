package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("no ejecutar hasta que se resuelva el jira #123") // deshabilita una prueba unitaria
    void testDisabled(){

    }

    @Test
    @EnabledOnOs(OS.WINDOWS) // habilita la prueba solo para windows
    void testOnlyOnWindows(){
        System.out.println("habilitado solo para windows");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC}) // habilita la prueba solo para linux o mac
    void testOnlyOnLinuxOrMac(){
        System.out.println("habilitado solo para linux o mac");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17) // habilita la prueba solo para java 17
    void testOnlyForJava17(){
        System.out.println("habilitado solo para java 17");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_25) // habilita la prueba para java entre 17 y 25
    void testForJava17to25(){
        System.out.println("habilitado para java 17 a 25");
    }

    // NOTA: en el ide de intelij se pueden configurar las variables de entorno y las propiedades del sistema
    @Test
    @EnabledIfEnvironmentVariable(named = "TESTCORP_PRUEBA", matches = "DEV") // habilita la prueba si la variable de entorno TESTCORP_PRUEBA es igual a DEV
    void testIfEnvironmentVariable(){
        System.out.println("habilitado si la variable de entorno TESTCORP_PRUEBA es igual a DEV");
    }

    // NOTA: en el ide de intelij se pueden configurar las variables de entorno y las propiedades del sistema
    @Test
    @EnabledIfSystemProperty(named = "TESCORP_SYS_PRUEBA", matches = "CI_CD_DEPLOYD") // habilita la prueba si la propiedad del sistema TESCORP_SYS_PRUEBA es igual a CI_CD_DEPLOYD
    void testIfSystemProperty(){
        System.out.println("habilitado si la propiedad del sistema TESCORP_SYS_PRUEBA es igual a CI_CD_DEPLOYD");
    }
}
