package com.mjc.school.controller.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;

public class Update<T, R, K> implements Command {
    private final BaseController<T, R, K> controller;
    private final T request;

    public Update(BaseController<T, R, K> controller, T request) {
        this.controller = controller;
        this.request = request;
    }

    @Override
    public Object execute() {
        return controller.update(request);
    }

}
