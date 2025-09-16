package com.yguira;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterAppApplicationTest {
    private final RegisterAppApplication app = new RegisterAppApplication();

    @Test
    void testHomeEndpoint() {
        assertEquals("Hello from Register App! This is working!", app.home());
    }

    @Test
    void testRegisterEndpoint() {
        assertEquals("User registration form coming soon!", app.register());
    }
}
