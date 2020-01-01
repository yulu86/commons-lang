package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EqualsTransitivityTest {
    static class C {
        private int a;

        C(final int a) {
            this.a = a;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (o == this) {
                return true;
            }
            if (o.getClass() != getClass()) {
                return false;
            }

            final C rhs = (C) o;
            return a == rhs.a;
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    static class D extends C {
        D(int a) {
            super(a);
        }
    }

    static class E extends C {
        E(int a) {
            super(a);
        }
    }

    @Test
    public void testEqualsTransitivity() {
        C c = new C(1);
        D d = new D(1);
        E e = new E(1);
        assertTrue(EqualsBuilder.reflectionEquals(c, d));
        assertTrue(EqualsBuilder.reflectionEquals(c, e));
        assertTrue(EqualsBuilder.reflectionEquals(d, e));
    }
}