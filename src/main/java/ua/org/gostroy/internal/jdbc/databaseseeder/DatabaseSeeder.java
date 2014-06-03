package ua.org.gostroy.internal.jdbc.databaseseeder;

import java.io.IOException;

/**
 * Created by panser on 6/1/2014.
 */

public class DatabaseSeeder {
    public DatabaseSeeder(JdbcTemplate jdbcTemplate) throws IOException {
        String sql = new String(
                FileCopyUtils.copyToByteArray(
                        new ClassPathResource("db/tplSpringDaoData.sql").getInputStream()
                )
        );
        jdbcTemplate.execute(sql);
    }
}
