package com.pluralsight.bookstore.util;

import java.util.Random;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public String generateNumber() {
        return "13-84356-" + Math.abs(new Random().nextInt());
    }
}
