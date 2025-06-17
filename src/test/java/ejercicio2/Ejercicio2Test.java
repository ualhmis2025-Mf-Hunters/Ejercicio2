package ejercicio2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class Ejercicio2Test {
	@ParameterizedTest(name = "Test {index}: Fecha {0}/{1}/{2} - válida: {3}")
    @CsvFileSource(resources = "/ejercicio2.csv", numLinesToSkip = 1)
    void testIsDateCorrect(int day, int month, int year, boolean expected) {
        Ejercicio2 myDate = new Ejercicio2();
        assertEquals(expected, myDate.isDateCorrect(day, month, year),
            String.format("Fecha %d/%d/%d debería ser %s", 
                day, month, year, expected ? "válida" : "inválida"));
    }
}
