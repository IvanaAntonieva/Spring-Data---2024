import entities.Order;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        MyConnector.createConnection("root", "12345", "mini_orm");
        Connection connection = MyConnector.getConnection();
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        User petrov = userEntityManager.findFirst(User.class);
        userEntityManager.delete(petrov);
    }
}
