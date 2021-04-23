
package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * JDBCMealServiceTest.
 *
 * @author ADivaev
 */
@ActiveProfiles(Profiles.JDBC)
public class JDBCMealServiceTest extends MealServiceTest {
}