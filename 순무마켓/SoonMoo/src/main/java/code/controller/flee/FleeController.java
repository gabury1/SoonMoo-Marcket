package code.controller.flee;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import code.dto.ItemDto;
import code.service.FileService;
import code.service.FleeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Controller @RequestMapping("/flee")
@RequiredArgsConstructor @Slf4j
public class FleeController {
    
    private final FleeService fleeService;
    private final FileService fileService;

    @RequestMapping(path="", method=RequestMethod.GET)
    public String flee() {
        return "/flee/fleeList";
    }

    @RequestMapping(path = "/write")
    public String write() 
    {

        return "/flee/fleeCreate";
    }



    @PostMapping(path = "/create") @ResponseBody
    public String createPost(ItemDto dto)
    {
        try{
            dto.validation();
            Long itemNo = fleeService.createItem(dto);
            fileService.saveFiles(0, itemNo, dto.files());

        }
        catch(Exception e)
        {
            return e.getMessage();
        }

        return "success";
    }
    

    

 
}
