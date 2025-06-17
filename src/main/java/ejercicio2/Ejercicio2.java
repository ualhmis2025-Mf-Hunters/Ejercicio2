package ejercicio2;

public class Ejercicio2 {
	public boolean isDateCorrect(int day, int month, int year) {
        // Validar mes
        if (month < 1 || month > 12) {
            return false;
        }

        // Validar día
        if (day < 1) {
            return false;
        }

        // Meses con 31 días
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return day <= 31;
        }
        // Meses con 30 días
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        // Febrero (28 días, sin bisiestos)
        else {
            return day <= 28;
        }
    }
}
