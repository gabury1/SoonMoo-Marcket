package code.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import code.domain.flee.ItemEntity;



public record ItemDto (
    String title,
    String content,
    String category,
    Integer price,
    List<MultipartFile> files
)
{
    public ItemEntity toEntity()
    {
        Integer fileCnt = 0;
        if(files != null) fileCnt = files.size();
        return ItemEntity.builder()
                                    .itemNo(null)
                                    .title(title)
                                    .content(content)
                                    .price(price)
                                    .category(category)
                                    .photoCount(fileCnt)
                                    .date(LocalDateTime.now())
                                    .interestCount(0)
                                    .chatCount(0)
                                    .views(0)
                                    .build();
    }

    public String validation() throws Exception
    {
        Integer fileCnt = 0;
        if(files != null) fileCnt = files.size();
        
        if(title == "") throw new Exception("제목을 입력해주세요.");
        if(price == null) throw new Exception("가격을 입력해주세요.");
        if(content == "") throw new Exception("내용을 입력해주세요.");
        if(category == "") throw new Exception("카테고리를 입력해주세요.");
        if(fileCnt > 5) throw new Exception("사진이 너무 많습니다.");

        return "success";
    }

}
