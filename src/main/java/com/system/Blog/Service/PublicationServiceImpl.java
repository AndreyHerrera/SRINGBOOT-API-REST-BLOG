package com.system.Blog.Service;

import com.system.Blog.Dto.PublicationDTO;
import com.system.Blog.Exception.ResourceNotFoundException;
import com.system.Blog.Repository.PublicationRepository;
import com.system.Blog.Model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {

        Publication publication = mapEntity(publicationDTO);
        Publication newPublication = publicationRepository.save(publication);

        return mapDTO(newPublication);
    }

    @Override
    public List<PublicationDTO> listPublication() {
        List<Publication> listPublications = publicationRepository.findAll();

        return listPublications.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO listPublicationId(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
        return mapDTO(publication);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication updatedPublication = publicationRepository.save(publication);
        return mapDTO(updatedPublication);
    }

    @Override
    public PublicationDTO deletePublication(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));

        publicationRepository.delete(publication);
        return null;
    }

    //Convierte de Entidad a DTO
    private PublicationDTO mapDTO (Publication publication){
        PublicationDTO publicationDTO = new PublicationDTO();

        publicationDTO.setId(publication.getId());
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setContent(publication.getContent());

        return publicationDTO;
    }

    //Convierte de DTO a Entidad
    private Publication mapEntity (PublicationDTO publicationDTO){
        Publication publication = new Publication();

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        return publication;
    }
}

