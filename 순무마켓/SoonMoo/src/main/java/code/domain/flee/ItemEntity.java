package code.domain.flee;


import java.time.LocalDateTime;

import org.json.simple.JSONObject;

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

    public JSONObject toJsonForList()
    {
        JSONObject object = new JSONObject();
        object.put("itemNo", itemNo);

        object.put("title", title);
        object.put("content", content);
        object.put("price", price);
        object.put("views", views);
        object.put("category", category);
        object.put("chatCount", chatCount);
        object.put("interestCount", interestCount);
        object.put("photoCount", photoCount);

        return object;
    }


}
