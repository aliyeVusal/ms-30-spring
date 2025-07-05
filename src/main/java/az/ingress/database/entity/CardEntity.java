package az.ingress.database.entity;

import az.ingress.database.enums.CardType;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String cardNumber;

    private Integer cvv;

    @Column(name = "date")
    private LocalDate expireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CardType cardType;

}
