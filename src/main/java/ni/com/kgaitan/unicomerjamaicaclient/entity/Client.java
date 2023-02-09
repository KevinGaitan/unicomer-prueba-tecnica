package ni.com.kgaitan.unicomerjamaicaclient.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "cell_phone", nullable = false)
    private String cellPhone;
    @Column(name = "home_phone", nullable = true)
    private String homePhone;
    @Column(name = "address_home")
    private String addressHome;
    @Column(nullable = false, length = 25)
    private String profession;
    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal incomes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
