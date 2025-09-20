package com.yguira;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.yguira.RegisterAppApplication; // Ajoute cet import

@SpringBootTest(classes = RegisterAppApplication.class)
class RegisterAppApplicationTest {

    @Test
    void contextLoads() {
        assertThat(true).isTrue(); // Test simple pour vérifier le chargement
    }
}
