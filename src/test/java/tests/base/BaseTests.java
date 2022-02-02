//package tests.base;
//
//import framework.managers.DriverManager;
//import framework.managers.InitManager;
//import framework.managers.PageManager;
//import framework.managers.TestPropManager;
//import framework.utils.MyAllureListener;
//import framework.utils.PropsConst;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//@ExtendWith(MyAllureListener.class)
//public class BaseTests {
//    private final DriverManager driverManager = DriverManager.getInstance();
//    private final TestPropManager testPropManager = TestPropManager.getInstance();
//    protected PageManager pageManager = PageManager.getPageManager();
//
//    @BeforeAll
//    public static void beforeAll() {
//        InitManager.initFramework();
//    }
//
//    @BeforeEach
//    public void before() {
//        driverManager.getWebDriver().get(testPropManager.getProperty(PropsConst.BASE_URL));
//    }
//
//    @AfterAll
//    public static void after() {
//        InitManager.quitFramework();
//    }
//}