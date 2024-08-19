package code.domain.flee;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name="item")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
@ToString @EqualsAndHashCode
public class ItemEntity {
    
    @Id @Column(name="item_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemNo;

    String title;
    String content;
    Integer price;
    
    Integer views;
    LocalDateTime date;
    String category;
    Integer chatCount;
    Integer interestCount;
    Integer photoCount; // 5개까지 .

}
