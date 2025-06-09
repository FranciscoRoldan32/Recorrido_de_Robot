package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Dto.ResultDto;
import model_service.MatrixService;

public class MatrixServiceTest {

    private MatrixService matrixService;
    private int[][] testMatrixEvenSteps;
    private int[][] testMatrixOddSteps;

    @Before
    public void setUp() {
        // Matriz 2x2 (2+2-1=3 pasos, impar)
        testMatrixOddSteps = new int[][]{
            {1, -1},
            {-1, 1}
        };

        // Matriz 2x3 (2+3-1=4 pasos, par)
        testMatrixEvenSteps = new int[][]{
            {1, -1, 1},
            {-1, 1, -1}
        };
    }

    @Test
    public void testGenerateMatrix() {
        int[][] generatedMatrix = MatrixService.generateMatrix(3, 4);
        assertEquals(3, generatedMatrix.length);
        assertEquals(4, generatedMatrix[0].length);
    }

    @Test
    public void testValidateNumberOfStepsIsEven_returTrue() {
        matrixService = new MatrixService(testMatrixEvenSteps);
        assertTrue("Debería ser par (4 pasos)", matrixService.validateNumberOfStepsIsEven());
    }
    
    @Test
    public void testValidateNumberOfStepsIsEven_returnFalse() {
        matrixService = new MatrixService(testMatrixOddSteps);
        assertFalse("Debería ser impar (3 pasos)", matrixService.validateNumberOfStepsIsEven());
    }

    @Test
    public void testGetNumberOfSteps() {
        matrixService = new MatrixService(testMatrixEvenSteps);
        int steps = matrixService.getNumberOfSteps();
        assertEquals("Número de pasos incorrecto para 2x3", 4, steps);
    }

    @Test
    public void testRunAlgorithmNoSolution() {
        int[][] noSolutionMatrix = {
            {1, 1},
            {1, 1}
        };
        matrixService = new MatrixService(noSolutionMatrix);
        ResultDto result = matrixService.runAlgorithm();
        assertFalse("No debería encontrar camino", result.pathFound);
    }

    @Test
    public void testRunAlgorithmWithSolution() {
        int[][] solutionMatrix = {
            {1, -1},
            {-1, 1}
        };
        matrixService = new MatrixService(solutionMatrix);
        ResultDto result = matrixService.runAlgorithm();

        assertTrue("Debería encontrar camino", result.pathFound);
        assertNotNull("Camino no debería ser nulo", result.validPath);
        assertFalse("Camino no debería estar vacío", result.validPath.isEmpty());
    }

    @Test
    public void testBacktrackCalls() {
        matrixService = new MatrixService(testMatrixEvenSteps);
        ResultDto result = matrixService.runAlgorithm();
        assertTrue("Debería haber al menos una llamada sin poda", result.callsWithoutPrune > 0);
        assertTrue("Debería haber al menos una llamada con poda", result.callsWithPrune > 0);
    }
}

