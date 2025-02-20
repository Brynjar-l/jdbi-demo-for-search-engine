package iceland.hi.throunhugbo.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;



public class JdbiManager {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/hotels.sqlite";
    private static volatile Jdbi jdbiInstance;

    private JdbiManager() {}

    public static Jdbi getInstance() {
        if (jdbiInstance == null) {
            synchronized (JdbiManager.class) {
                if (jdbiInstance == null) {
                    jdbiInstance = Jdbi.create(URL);
                    jdbiInstance.installPlugin(new SqlObjectPlugin());
                    DatabaseInitializer.init(jdbiInstance);
                }
            }
        }
        return jdbiInstance;
    }
}

