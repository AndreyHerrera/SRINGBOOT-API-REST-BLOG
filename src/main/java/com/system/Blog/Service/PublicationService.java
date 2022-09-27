package com.system.Blog.Service;

import com.system.Blog.Dto.PublicationDTO;

import java.util.List;

public interface PublicationService {

    PublicationDTO createPublication(PublicationDTO publicationDTO);
    List<PublicationDTO> listPublication();
    PublicationDTO listPublicationId(Long id);
    PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);
    PublicationDTO deletePublication(Long id);
}
