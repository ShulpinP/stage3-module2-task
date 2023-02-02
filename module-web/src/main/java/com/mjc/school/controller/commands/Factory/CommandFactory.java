package com.mjc.school.controller.commands.Factory;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.commands.*;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public CommandFactory(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                          BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    public Command create(int commandCode, Object... params) {
        switch (commandCode) {
            case CommandConstants.CREATE_NEWS:
                return new Create<>(newsController, new NewsDtoRequest(null, (String) params[0], (String) params[1], (Long) params[2]));
            case CommandConstants.GET_ALL_NEWS:
                return new ReadAll<>(newsController);
            case CommandConstants.GET_NEWS_BY_ID:
                return new GetById<>(newsController, (Long) params[0]);
            case CommandConstants.UPDATE_NEWS:
                return new Update<>(newsController, new NewsDtoRequest((Long) params[0], (String) params[1], (String) params[2], (Long) params[3]));
            case CommandConstants.DELETE_NEWS:
                return new Delete<>(newsController, (Long) params[0]);

            case CommandConstants.CREATE_AUTHOR:
                return new Create<>(authorController, new AuthorDtoRequest(null, (String) params[0]));
            case CommandConstants.GET_ALL_AUTHORS:
                return new ReadAll<>(authorController);
            case CommandConstants.GET_AUTHOR_BY_ID:
                return new GetById<>(authorController, (Long) params[0]);
            case CommandConstants.UPDATE_AUTHOR:
                return new Update<>(authorController, new AuthorDtoRequest((Long) params[0], (String) params[1]));
            case CommandConstants.DELETE_AUTHOR:
                return new Delete<>(authorController, (Long) params[0]);
            default:
                return null;
        }

    }

}