
package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * JPAMealServiceTest.
 *
 * @author ADivaev
 */
@ActiveProfiles(Profiles.JPA)
public class JPAMealServiceTest extends MealServiceTest {
}