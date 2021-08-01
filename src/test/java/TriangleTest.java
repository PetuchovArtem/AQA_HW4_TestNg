import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import triangle.Triangle;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TriangleTest {
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{2, 2, 3}, {4, 5, 6}, {0, 5, 6}};
    }

    @DataProvider(name = "data-provider2")
    public Object[][] dpMethod2() {
        String value1 = "12313132123";
        String value2 = "12313112332123";
        String value3 = "12313131232123";
        return new Object[][]{{value1, value2, value3}};
    }

    @DataProvider(name = "data-provider3")
    public Object[][] dpMethod3() {
        return new Object[][]{{2, 2, 2}, {100, 100, 100}, {6.5, 6.5, 6.5}, {10, 10, 6.5}, {6.5, 10, 10}, {10, 6.5, 10}};
    }

    @DataProvider(name = "data-provider4")
    public Object[][] dpMethod4() {
        return new Object[][]{{10, 10, 6.5}, {6.5, 10, 10}, {10, 6.5, 10}};
    }

    @DataProvider(name = "data-provider5")
    public Object[][] dpMethod5() {
        return new Object[][]{{3, 4, 5}, {5, 4, 3}, {4, 5, 3}, {6, 8, 10}};
    }

    @DataProvider(name = "data-provider6")
    public Object[][] dpMethod6() {
        return new Object[][]{{1, 1, Math.sqrt(2)}, {4, 4, (4 * Math.sqrt(2))}, {4, (4 * Math.sqrt(2)), 4}};
    }

    @DataProvider(name = "data-provider7")
    public Object[][] dpMethod7() {
        return new Object[][]{{1, 2, 3}, {9, 6, 4}, {17, 22, 23}};
    }


    @Test(dataProvider = "data-provider", testName = "Сумма двух сторон треугольника, стороны больше нуля")
    public void testCheckTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        Assert.assertTrue(triangle.checkTriangle());
    }


    @Test(expectedExceptions = NumberFormatException.class, testName = "Тип входных параметров")
    public void testCheckTypeTriangle() {
        Scanner sc = new Scanner("text.txt");
        Triangle triangle = new Triangle(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
        Assert.assertTrue(triangle.checkTriangle());
    }


    @Test(dataProvider = "data-provider2", expectedExceptions = NumberFormatException.class, testName = "Переполнение")
    public void testCheckPerepolenieTriangle(String arg0, String arg1, String arg2) {
        int arg11 = Integer.parseInt(arg0);
        int arg12 = Integer.parseInt(arg1);
        int arg13 = Integer.parseInt(arg2);
        Triangle triangle = new Triangle(arg11, arg12, arg13);
    }


    @Test(dataProvider = "data-provider3", testName = "Проверка треугольника на равносторонний")
    public void testRavnostoronniyTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        Assert.assertEquals(triangle.detectTriangle(), 1);
    }

    @Test(dataProvider = "data-provider4", testName = "Проверка равнобедренного треугольника")
    public void testRavnoBedTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        Assert.assertEquals(triangle.detectTriangle(), 2);
    }

    @Test(dataProvider = "data-provider5", testName = "Проверка прямоугольного треугольника")
    public void testPriamougTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        Assert.assertEquals(triangle.detectTriangle(), 8);
    }

    @Test(dataProvider = "data-provider6", testName = "Проверка равнобедренного прямоугольного треугольника")
    public void testRavnPriamouTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        SoftAssert check = new SoftAssert();
        check.assertEquals(triangle.detectTriangle(), 2);
        check.assertEquals(triangle.detectTriangle(), 8);
        check.assertAll();
    }

    @Test(dataProvider = "data-provider7", testName = "Проверка обычного треугольника")
    public void testRegularTriangle(double arg0, double arg1, double arg2) {
        Triangle triangle = new Triangle(arg0, arg1, arg2);
        Assert.assertEquals(triangle.detectTriangle(), 4);
    }
}
