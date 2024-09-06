package it.its.springdemo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {

        public String store(MultipartFile file) throws IOException {
            String retMessage = "";

            if (file.isEmpty()) {
                retMessage = "Failed to store empty file";
            }
            else{
                String timestamp = "SAMPLEFILES_";
                //save file in custom location instead temp file
                File temp = File.createTempFile(timestamp, file.getOriginalFilename());
                file.transferTo(temp);
                retMessage = temp.getAbsolutePath();
            }
            return retMessage;
        }

        public List<String> loadAll() throws Exception{
            List<String> retMessages = new ArrayList<String>();
            File tmp = new File("C:\\Users\\FEDERI~2\\AppData\\Local\\Temp\\");
            File[] files = tmp.listFiles();
            if(files != null){
                for(File file : files){
                    if(file.getName().startsWith("SAMPLEFILES_")){
                        retMessages.add(file.getName());
                    }
                }
            }
            return retMessages;
        }

}
