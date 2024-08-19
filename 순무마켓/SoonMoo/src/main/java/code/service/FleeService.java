package code.service;

import org.springframework.stereotype.Service;

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



    
}
