package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] temperatures;
    private static int nHours;

    @BeforeClass
    public static void setUp() {
        System.out.println("+++ setUp +++");

        // Arrange: create controller once
        wController = WeatherController.getInstance();

        // Arrange: retrieve slow temperature values once and reuse them
        nHours = wController.getTotalHours();
        temperatures = new double[nHours];

        for (int i = 0; i < nHours; i++) {
            temperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("+++ tearDown +++");

        if (wController != null) {
            wController.close();
        }
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "s224752512";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Arpit Mantri";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");

        // Arrange
        double minTemperature = temperatures[0];
        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] < minTemperature) {
                minTemperature = temperatures[i];
            }
        }

        // Act
        double cachedMin = wController.getTemperatureMinFromCache();

        // Assert
        Assert.assertEquals(minTemperature, cachedMin, 0.0001);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");

        // Arrange
        double maxTemperature = temperatures[0];
        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] > maxTemperature) {
                maxTemperature = temperatures[i];
            }
        }

        // Act
        double cachedMax = wController.getTemperatureMaxFromCache();

        // Assert
        Assert.assertEquals(maxTemperature, cachedMax, 0.0001);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");

        // Arrange
        double sumTemperature = 0;
        for (int i = 0; i < nHours; i++) {
            sumTemperature += temperatures[i];
        }
        double averageTemperature = sumTemperature / nHours;

        // Act
        double cachedAverage = wController.getTemperatureAverageFromCache();

        // Assert
        Assert.assertEquals(averageTemperature, cachedAverage, 0.0001);
    }

    @Test
    public void testTemperaturePersist() {
        /*
         * Remove below comments ONLY for 5.3C task.
         */
//      System.out.println("+++ testTemperaturePersist +++");
//
//      String persistTime = wController.persistTemperature(10, 19.5);
//      String now = new SimpleDateFormat("H:m:s").format(new Date());
//      System.out.println("Persist time: " + persistTime + ", now: " + now);
//
//      Assert.assertTrue(persistTime.equals(now));
    }
}