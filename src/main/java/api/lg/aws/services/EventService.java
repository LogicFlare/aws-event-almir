package api.lg.aws.services;

import api.lg.aws.domain.Event;
import api.lg.aws.dto.EventRequestDTO;
import api.lg.aws.repositories.EventRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    AmazonS3 amazonS3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = null;

        if(data.file() != null){
            imgUrl = this.uploadImg(data.file());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setDate(new Date(data.date()));
        newEvent.setEventUrl(imgUrl);

        return newEvent;
    }

    public String uploadImg(MultipartFile multipartFile){
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultiPartToFile(multipartFile);
            amazonS3Client.putObject(bucketName, imgName, file);
            file.delete();
            return amazonS3Client.getUrl(bucketName, imgName).toString();
        } catch (Exception e){
            throw new RuntimeException("Error to convert multipart file to file");
        }
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
