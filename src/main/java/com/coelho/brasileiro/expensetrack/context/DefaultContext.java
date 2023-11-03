package com.coelho.brasileiro.expensetrack.context;

import com.coelho.brasileiro.expensetrack.dto.BudgetDto;
import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.dto.UserDto;
import com.coelho.brasileiro.expensetrack.input.*;
import com.coelho.brasileiro.expensetrack.util.Constants;
import lombok.Builder;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_DTO;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY_DTO;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY_INPUT;


@Builder
public class DefaultContext extends BaseContext{
    public com.coelho.brasileiro.expensetrack.model.User getUser(){
        return this.getEntity(Constants.User.USER, com.coelho.brasileiro.expensetrack.model.User.class );
    }

    public void setUser(com.coelho.brasileiro.expensetrack.model.User user){
        this.setEntity(Constants.User.USER, user);
    }

    public UserDto getUserDto(){
        return this.getDto(Constants.User.USER_DTO, UserDto.class );
    }

    public void setUserDto(UserDto userDTO){
        this.setDto(Constants.User.USER_DTO, userDTO);
    }

    public LoginInput getUserInput(){
        return this.getInput(Constants.User.USER_INPUT, LoginInput.class );
    }

    public void setLoginInput(LoginInput loginInputRequest){
        this.setInput(Constants.User.USER_INPUT, loginInputRequest);
    }

    public void setUserInput(UserInput userInput){
        this.setInput(Constants.User.USER_INPUT, userInput);
    }
    public UserInput getUserRequest(){
        return this.getInput(Constants.User.USER_INPUT, UserInput.class);
    }

    public void setUserUpdate(UserUpdate userRequest){
        this.setInput(Constants.User.USER_INPUT, userRequest);
    }

    public UserUpdate getUserUpdate(){
        return this.getInput(Constants.User.USER_INPUT, UserUpdate.class);
    }

    public void setCategoryInput(CategoryInput input){
        this.setInput(CATEGORY_INPUT, input);
    }
    public CategoryInput getCategoryInput(){
        return this.getInput(CATEGORY_INPUT, CategoryInput.class);
    }

    public CategoryDto  getCategoryDto(){
        return this.getDto(CATEGORY_DTO, CategoryDto.class);
    }


    public void setBudgetInput(BudgetInput budgetInput) {
        this.setInput(BUDGET_INPUT, budgetInput);
    }

    public BudgetDto getBudgetDto() {
        return this.getDto(BUDGET_DTO, BudgetDto.class);
    }
}
