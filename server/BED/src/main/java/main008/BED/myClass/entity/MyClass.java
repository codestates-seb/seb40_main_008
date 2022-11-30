package main008.BED.myClass.entity;

import lombok.*;
import main008.BED.payment.entity.Payment;
import main008.BED.users.entity.Users;
import main008.BED.wish.entity.Wish;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myClassId;

    @OneToMany(mappedBy = "myClass", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToMany(mappedBy = "myClass", cascade = CascadeType.ALL)
    private List<Wish> wishes;

    public void addWish(Wish wish) {
        this.wishes.add(wish);
        if (wish.getMyClass() != this) {
            wish.addMyClass(this);
        }
    }

}
