package com.system.Blog.Controller;

import com.system.Blog.Dto.PublicationDTO;
import com.system.Blog.Service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    public PublicationService publicationService;

    @GetMapping
    public List<PublicationDTO> listPublication(){
        return publicationService.listPublication();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> listPublicationId(@PathVariable(name = "id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(publicationService.listPublicationId(id));
    }

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.createPublication(publicationDTO));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PublicationDTO> updatePublication(@RequestBody PublicationDTO publicationDTO, @PathVariable(name = "id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(publicationService.updatePublication(publicationDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublication (@PathVariable(name = "id") long id){
        publicationService.deletePublication(id);
        return ResponseEntity.status(HttpStatus.OK).body("Publication Deleted");
    }
}
