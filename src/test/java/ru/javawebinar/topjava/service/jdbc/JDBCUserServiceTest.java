
package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * JDBCUserTestServiceTest.
 *
 * @author ADivaev
 */
@ActiveProfiles(Profiles.JDBC)
public class JDBCUserServiceTest extends UserServiceTest {
    @Override
    public void get() {
        super.get();
    }
}