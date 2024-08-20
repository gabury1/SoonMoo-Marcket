package code.service;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import code.domain.flee.ItemEntity;
import code.domain.flee.ItemRepository;
import code.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
@RequiredArgsConstructor
public class FleeService 
{
    private final ItemRepository itemRepository;

    public Long createItem(ItemDto itemDto) throws Exception
    {
        var entity = itemRepository.save(itemDto.toEntity());
        

        return entity.getItemNo();
    }

    // 모든 물품 조회
    public JSONObject getList(Integer pageNum)
    {
        JSONObject json = new JSONObject();
        Pageable pageable = PageRequest.of(pageNum, 15);
        
        // 물품 페이지 불러오기
        Page<ItemEntity> page = itemRepository.pageOf(pageable);

        // JSON 배열에 물품 정보를 담아준다.
        JSONArray array = new JSONArray();
        for(ItemEntity e : page)array.put(e.toJsonForList());
        
        // 페이지 번호를 정해준다.
        int pageStart = page.getNumber()-2;
        if(pageStart < 0) pageStart = 0;
        int pageLast = page.getNumber()+2;
        if(page.getTotalPages() <= pageLast) pageLast = page.getTotalPages()-1;

        // JSON에 담기
        json.put("length", array.length());
        json.put("startNum", pageStart);
        json.put("lastNum", pageLast);
        json.put("nowNum", pageNum);
        json.put("list", array);

        return json;
    }



    
}
