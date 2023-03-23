package web.pp311.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$| +",message = "Имя не должно содержать иные символы")
    @NotEmpty(message = "Заполните поле...")
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$| +",message = "Фамилия не должна содержать иные символы")
    @NotEmpty(message = "Заполните поле...")
    @Column(name = "surname")
    private String surname;
    @NotNull(message = "Заполните поле...")
    @Min(value = 1,message = "Возраст должен быть больше 0")
    @Max(value = 120,message = "Возраст должен быть меньше 120")
    @Column(name = "age")
    private int age;

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}