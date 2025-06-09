package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Dto.ResultDto;
import model_service.MatrixService;

public class MatrixServiceTest {

    private MatrixService matrixService;
    private int[][] testMatrixEvenSteps;
    private int[][] testMatrixOddSteps;

    @BeforeEach
    public void setUp() {
        // Matriz 2x2 (2+2-1=3 pasos, impar)
        testMatrixOddSteps = new int[][]{
            {1, -1},
            {-1, 1}
        };

        // Matriz 3x3 (3+3-1=5 pasos, impar)
        // Pero vamos a hacer un 2x3 (2+3-1=4 pasos, par)
        testMatrixEvenSteps = new int[][]{
            {1, -1, 1},
            {-1, 1, -1}
        };
    }

    @Test
    @DisplayName("Prueba de generación de matriz")
    public void testGenerateMatrix() {
        int[][] generatedMatrix = MatrixService.generateMatrix(3, 4);
        assertEquals(3, generatedMatrix.length);
        assertEquals(4, generatedMatrix[0].length);
    }

    @Test
    @DisplayName("Validación de número de pasos par/impar")
    public void testValidateNumberOfStepsIsEven() {
        matrixService = new MatrixService(testMatrixOddSteps);
        assertFalse(matrixService.validateNumberOfStepsIsEven(), "Debería ser impar (3 pasos)");

        matrixService = new MatrixService(testMatrixEvenSteps);
        assertTrue(matrixService.validateNumberOfStepsIsEven(), "Debería ser par (4 pasos)");
    }

    @Test
    @DisplayName("Validación del número de pasos correcto")
    public void testGetNumberOfSteps() {
        matrixService = new MatrixService(testMatrixEvenSteps);
        int steps = matrixService.getNumberOfSteps();
        assertEquals(4, steps, "Número de pasos incorrecto para 2x3");
    }

    @Test
    @DisplayName("Prueba de runAlgorithm con matriz sin solución")
    public void testRunAlgorithmNoSolution() {
        // Matriz sin solución (imposible lograr suma 0 al final)
        int[][] noSolutionMatrix = {
            {1, 1},
            {1, 1}
        };
        matrixService = new MatrixService(noSolutionMatrix);
        ResultDto result = matrixService.runAlgorithm();
        assertFalse(result.pathFound, "No debería encontrar camino");
    }

    @Test
    @DisplayName("Prueba de runAlgorithm con matriz con solución")
    public void testRunAlgorithmWithSolution() {
        // Matriz pequeña con camino válido
        int[][] solutionMatrix = {
            {1, -1},
            {-1, 1}
        };
        matrixService = new MatrixService(solutionMatrix);
        ResultDto result = matrixService.runAlgorithm();

        assertTrue(result.pathFound, "Debería encontrar camino");
        assertNotNull(result.validPath, "Camino no debería ser nulo");
        assertFalse(result.validPath.isEmpty(), "Camino no debería estar vacío");
    }

    @Test
    @DisplayName("Verificación de llamadas a backtrack")
    public void testBacktrackCalls() {
        matrixService = new MatrixService(testMatrixEvenSteps);
        ResultDto result = matrixService.runAlgorithm();
        assertTrue(result.callsWithoutPrune > 0, "Debería haber al menos una llamada sin poda");
        assertTrue(result.callsWithPrune > 0, "Debería haber al menos una llamada con poda");
    }
}
