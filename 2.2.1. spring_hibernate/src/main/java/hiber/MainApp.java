package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.transform.CacheableResultTransformer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("Вася1", "Петров1", "user1@mail.ru");
      User user2 = new User("Вася2", "Петров2", "user1@mail.ru");
      User user3 = new User("Вася3", "Петров3", "user1@mail.ru");
      User user4 = new User("Вася4", "Петров4", "user1@mail.ru");


      Car car1 = new Car("Лада", 12345);
      Car car2 = new Car("Нива", 54321);
      Car car3 = new Car("Субару", 67890);
      Car car4 = new Car("Ford", 9876);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.addUserWithCar(user1, car1);
      userService.addUserWithCar(user2, car2);
      userService.addUserWithCar(user3, car3);
      userService.addUserWithCar(user4, car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      System.out.println("Ищем машину: Лада, номер 12345");

      List<User> usersByCar = userService.getUserCar("Лада", 12345);
      for (User user : usersByCar) {
         System.out.println(user);
         System.out.println();
      }

      context.close();
   }
}
