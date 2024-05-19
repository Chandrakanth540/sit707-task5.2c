package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static MockClock mockClock;
    private static double minTemperature;
    private static double maxTemperature;
    private static double averageTemperature;

    @Test
    public void testStudentIdentity() {
        String studentId = "s223798216";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "chandrakanth";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @BeforeClass
    public static void setUp() {
        // Arrange: Initialise mock clock and controller
        mockClock = new MockClock();
        wController = WeatherController.getInstance(mockClock);
        // Arrange: Retrieve and cache temperature statistics
        minTemperature = wController.getTemperatureMinFromCache();
        maxTemperature = wController.getTemperatureMaxFromCache();
        averageTemperature = wController.getTemperatureAverageFromCache();
    }

    @AfterClass
    public static void tearDown() {
        // After: Shutdown controller
        wController.close();
    }

    @Test
    public void testTemperatureMin() {
        // Act: Retrieve minimum temperature
        double actualMinTemperature = wController.getTemperatureMinFromCache();
        // Assert: Verify the minimum temperature
        Assert.assertEquals(minTemperature, actualMinTemperature, 0.0);
    }

    @Test
    public void testTemperatureMax() {
        // Act: Retrieve maximum temperature
        double actualMaxTemperature = wController.getTemperatureMaxFromCache();
        // Assert: Verify the maximum temperature
        Assert.assertEquals(maxTemperature, actualMaxTemperature, 0.0);
    }

    @Test
    public void testTemperatureAverage() {
        // Act: Retrieve average temperature
        double actualAverageTemperature = wController.getTemperatureAverageFromCache();
        // Assert: Verify the average temperature
        Assert.assertEquals(averageTemperature, actualAverageTemperature, 0.0);
    }

    @Test
    public void testTemperaturePersist() {
        System.out.println("+++ testTemperaturePersist +++");

        // Arrange: Set the mock clock to a fixed time
        String expectedTime = "12:00:00";
        mockClock.setCurrentTime(expectedTime);

        // Act: Persist temperature
        String persistTime = wController.persistTemperature(10, 19.5);

        System.out.println("Persist time: " + persistTime + ", expected: " + expectedTime);

        // Assert: Verify that the persist time matches the expected time
        Assert.assertEquals("Persist time does not match the expected time", expectedTime, persistTime);

        // Close the controller instance
        wController.close();
    }
}
