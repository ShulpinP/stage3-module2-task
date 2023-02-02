package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aspect.OnDelete;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.interfaces.AuthorMapper;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.validators.NewsValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final NewsValidator authorValidator;
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository, NewsValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorMapper.listOfModelsToListOfResponses(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        authorValidator.validateAuthorId(id);
        Optional<AuthorModel> maybeNullModel = authorRepository.readById(id);
        if (maybeNullModel.isEmpty()) {
            throw new ServiceException(String.format(ErrorCode.AUTHOR_DOES_NOT_EXIST.toString(), id));
        }
        return authorMapper.modelToDTOResponse(maybeNullModel.get());
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        authorValidator.validateAuthorRequest(createRequest);
        AuthorModel model = authorMapper.DtoRequestToModel(createRequest);
        LocalDateTime creationDate = LocalDateTime.now();
        model.setCreationDate(creationDate);
        model.setLastUpdateDate(creationDate);
        model = authorRepository.create(model);
        return authorMapper.modelToDTOResponse(model);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        authorValidator.validateAuthorRequest(updateRequest);
        authorValidator.validateAuthorId(updateRequest.getId());
        AuthorModel requestModel = authorMapper.DtoRequestToModel(updateRequest);
        requestModel.setLastUpdateDate(LocalDateTime.now());
        AuthorModel updateModel = authorRepository.update(requestModel);
        if (updateModel == null) {
            throw new ServiceException(String.format(ErrorCode.AUTHOR_DOES_NOT_EXIST.toString(), updateRequest.getId()));
        }
        return authorMapper.modelToDTOResponse(updateModel);
    }

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        authorValidator.validateNewsId(id);
        if (!authorRepository.deleteById(id)) {
            throw new ServiceException(String.format(ErrorCode.AUTHOR_DOES_NOT_EXIST.toString(), id));
        }
        return true;
    }
}



