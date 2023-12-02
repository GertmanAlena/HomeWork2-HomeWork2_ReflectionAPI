package task3Seminar2;

import java.util.UUID;

@Table(name = "users")        //отождествляем класс с таблицей
public class Employee {

   @Column(name = "id", primaryKey = true)   //есть колонка id и она primaryKey
   private UUID id;

   @Column(name = "username")
   private String username;

   @Column(name = "email")
   private String email;

   //region Constructor

   public Employee(String username, String email) {
      this.username = username;
      this.email = email;
   }
   //endregion

   //region Geter Seter

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   //endregion
}
