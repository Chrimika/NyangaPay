package com.example.nyangapay.service.auth;

import com.example.nyangapay.model.IAuthenticationStrategy;
import com.example.nyangapay.service.AuthenticationFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationSystemTest {

    @Autowired
    private AuthenticationFactory authFactory;

    @Test
    public void testDynamicAuthenticationSelection() {
        Optional<IAuthenticationStrategy> passwordStrategy = authFactory.getStrategy("PASSWORD");
        assertTrue(passwordStrategy.isPresent());
        assertTrue(passwordStrategy.get().authenticate("user1", "root"));
        assertFalse(passwordStrategy.get().authenticate("user1", "wrong"));

        Optional<IAuthenticationStrategy> fingerprintStrategy = authFactory.getStrategy("FINGERPRINT");
        assertTrue(fingerprintStrategy.isPresent());
        assertTrue(fingerprintStrategy.get().authenticate("user1", "any_hash"));
    }

    @Test
    public void testExtensionAugmentedReality() {
        Optional<IAuthenticationStrategy> arStrategy = authFactory.getStrategy("AUGMENTED_REALITY");
        assertTrue(arStrategy.isPresent());
        assertEquals("AUGMENTED_REALITY", arStrategy.get().getMethodName());
        assertTrue(arStrategy.get().authenticate("user2", "eyes_scan"));
    }

    @Test
    public void testUnknownStrategy() {
        Optional<IAuthenticationStrategy> unknown = authFactory.getStrategy("IRIS_SCAN");
        assertFalse(unknown.isPresent());
    }
}
