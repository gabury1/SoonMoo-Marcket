package code.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;

@Service @Slf4j
public class FileService 
{
    private final String pathStr;
    private final List<String> folderName;

    public FileService()
    {
        pathStr = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
        folderName = new ArrayList<String>();
        folderName.add("items");
    }

    public String saveFiles(Integer folderNo, Long No, List<MultipartFile> files) throws Exception
    {
        String directory = pathStr + "\\" + folderName.get(folderNo) + "\\" + No;
        
        Files.createDirectory(Paths.get(directory));

        for(int i = 0; i < files.size(); i++)
        {
            MultipartFile f = files.get(i);
            f.transferTo(new File(directory + "\\" + i + "." + StringUtils.getFilenameExtension(f.getOriginalFilename())));
        }

        return "success";
    }
        
}
