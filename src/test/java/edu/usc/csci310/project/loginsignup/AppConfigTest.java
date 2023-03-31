//package edu.usc.csci310.project.loginsignup;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//
//class AppConfigTest {
//
//    @Test
//    void dataSource() {
//        AppConfig ac = new AppConfig();
//        ac.datasourceUrl = "temp";
//        ac.driverClassName = "com.mysql.cj.jdbc.Driver";
//        ac.password = "123";
//        ac.username = "user";
//        DriverManagerDataSource ds = mock(DriverManagerDataSource.class);
//        doNothing().when(ds).setDriverClassName(anyString());
//        doNothing().when(ds).setPassword(anyString());
//        doNothing().when(ds).setUrl(anyString());
//        doNothing().when(ds).setUsername(anyString());
//        assertNotEquals(null, ac.dataSource());
//    }
//}