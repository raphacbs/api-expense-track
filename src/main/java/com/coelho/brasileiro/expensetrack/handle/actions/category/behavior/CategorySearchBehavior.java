package com.coelho.brasileiro.expensetrack.handle.actions.category.behavior;

import com.coelho.brasileiro.expensetrack.constants.Params;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public interface CategorySearchBehavior {
    default Pageable getPageable(Map<String, String> params){
        String sortDir = Optional.ofNullable(params.get(Params.SORT_DIR)).orElse("desc").toString();
        String sortBy = Optional.ofNullable(params.get(Params.SORT_BY)).orElse(Params.NAME).toString();
        int pageNo = Integer.parseInt(Optional.ofNullable(params.get(Params.NO_PAGE)).orElse("0"));
        int pageSize = Integer.parseInt(Optional.ofNullable(params.get(Params.PAGE_SIZE)).orElse("10"));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of((pageNo == 0 ? 0 : pageNo -1 ), pageSize, sort);
    }

    Page<Category> searchPageUnit(CategoryRepository categoryRepository, Map<String, String> params);
}
