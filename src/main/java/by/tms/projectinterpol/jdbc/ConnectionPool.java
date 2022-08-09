package by.tms.projectinterpol.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.tms.projectinterpol.util.LoggerUtil.*;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static Queue<ProxyConnection> availableConnections = null;
    public static final String DB_URL_KEY = "db.url";
    public static final String DB_USER_KEY = "db.user";
    public static final String DB_PASS_KEY = "db.password";
    public static final String DB_SIZE_KEY = "db.pool.size";
    public static final String DB_DRIVER = "db.postgres.driver";
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean flag = new AtomicBoolean();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    static {
        loadDriver();
        initConnectionPool();
    }

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (!flag.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    flag.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private static void initConnectionPool() {
        try {
            int poolSize = Integer.parseInt(PropertiesManager.getPropertyByKey(DB_SIZE_KEY));
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                availableConnections.offer(createConnection());
            }
        } catch (SQLException e) {
            LOGGER.error(INIT_CONNECTION_POOL_ERROR_MESSAGE, e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesManager.getPropertyByKey(DB_DRIVER));
        } catch (ClassNotFoundException e) {
            LOGGER.error(ERROR_DURING_LOAD_DRIVER_MESSAGE, e);
        }
    }

    private static ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(
                DriverManager.getConnection(PropertiesManager.getPropertyByKey(DB_URL_KEY),
                        PropertiesManager.getPropertyByKey(DB_USER_KEY),
                        PropertiesManager.getPropertyByKey(DB_PASS_KEY)));
    }

    public ProxyConnection getConnection() {
        LOGGER.info("Connection poll");
        return availableConnections.poll();
    }

    public void releaseConnection(ProxyConnection connection) throws SQLException {
        LOGGER.info(ENTER_METHOD_MESSAGE);
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        LOGGER.info("Connection release");
        availableConnections.offer(connection);
    }

    public void destroy() throws SQLException {
        for (ProxyConnection connection : availableConnections) {
            connection.closeConnection();
        }
    }

    public int countUnusedConnection() {
        return availableConnections.size();
    }
}


