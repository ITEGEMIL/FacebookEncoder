package pl.kurs.java;

import lombok.*;
//@Getter robi gettery dla wszysktich  pol
//@Setter robi settery dla wszysktich pol
//@EqualsAndHashCode robi automatyczna implementacje dla equals i hashcode
@ToString // robi implemntacje toStringa
@Data // @Getter, @Setter, @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
//    @Setter(AccessLevel.NONE) wyklucza setter
    private String lastName;
    private int age;
}
