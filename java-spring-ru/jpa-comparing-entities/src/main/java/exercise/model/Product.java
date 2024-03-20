package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "products")
@Setter
@Getter
@EqualsAndHashCode(of = {"price", "title"})
public class Product {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private long id;

    private String title;

    private int price;
}
// END
