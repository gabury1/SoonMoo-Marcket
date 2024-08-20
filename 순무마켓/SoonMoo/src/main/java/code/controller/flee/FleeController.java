package code.controller.flee;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import code.dto.ItemDto;
import code.service.FileService;
import code.service.FleeService;
import jakarta.servlet.http.HttpServletResponse;
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

    // 중고거래 물품 등록
    @PostMapping(path = "/create") @ResponseBody
    public String createPost(ItemDto dto)
    {
        try{
            dto.validation();
            Long itemNo = fleeService.createItem(dto);
            if(dto.files() != null) fileService.saveFiles(0, itemNo, dto.files());

        }
        catch(Exception e)
        {
            return e.getMessage();
        }

        return "success";
    }
    
    // 중고거래 물품 목록 조회
    @GetMapping(path = "/getList") @ResponseBody
    public void getList(Integer pageNum, HttpServletResponse response)
    {

        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(fleeService.getList(pageNum));

        }catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

    }

    

 
}
