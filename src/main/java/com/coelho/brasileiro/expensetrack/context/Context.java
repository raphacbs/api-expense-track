package com.coelho.brasileiro.expensetrack.context;

import com.coelho.brasileiro.expensetrack.dto.request.InputRequest;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.model.IEntity;


import java.util.List;

public interface Context {
    <T extends InputRequest> T getInput(String key, Class<T> clazz);

    void setInput(String key, InputRequest input);

    <T extends Dto> T getDto(String key, Class<T> clazz);

    void setDto(String key, Dto dto);

    <T extends IEntity> T getEntity(String key, Class<T> clazz);

    void setEntity(String key, IEntity entity);

    <T extends Dto> List<T> getDtos(String key, Class<T> clazz);

    void setDtos(String key, List<? extends Dto> dtos);

    <T extends IEntity> List<T> getEntities(String key, Class<T> clazz);

    void setEntities(String key, List<? extends IEntity> entities);
}
