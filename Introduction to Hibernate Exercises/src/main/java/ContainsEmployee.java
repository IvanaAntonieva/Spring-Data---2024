import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.stream.Stream;

public class ContainsEmployee {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final String[] name = new Scanner(System.in).nextLine().split(" ");
        String firstName = name[0];
        String lastName = name[1];
        final Long countOfMatches = entityManager.createQuery(
                        "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();
        if(countOfMatches == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
