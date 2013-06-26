package db.migration;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration;

public class V1_1__DefaultBlogData implements JdbcMigration {

    @Override
    public void migrate(Connection connection) throws Exception {
        PreparedStatement insertIntoPost = connection.prepareStatement(
                "insert into Post (content, title) values ('Hello World', 'Some Content...')");
        try {
            insertIntoPost.execute();
        } finally {
            insertIntoPost.close();
        }
    }

}
