package com.coelho.brasileiro.expensetrack.util;


import com.coelho.brasileiro.expensetrack.model.FrequencyEnum;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class FrequencyUtils {

    public LocalDateTime calculateNextDate(LocalDateTime currentDate, FrequencyEnum frequency) {

        return switch (frequency) {
            case MONTHLY -> currentDate.plusMonths(1);
            case ANNUAL -> currentDate.plusYears(1);
            case BIWEEKLY -> currentDate.plusWeeks(2);
            case WEEKLY -> currentDate.plusWeeks(1);
            case DAILY -> currentDate.plusDays(1);
        };
    }

    public LocalDateTime calculateEndDate(LocalDateTime startDate, FrequencyEnum frequency) {
        return switch (frequency) {
            case MONTHLY -> startDate.minusDays(1).plusMonths(1).withHour(23).withMinute(59).withSecond(59);
            case ANNUAL -> startDate.minusDays(1).plusYears(1).withHour(23).withMinute(59).withSecond(59);
            case BIWEEKLY -> startDate.minusDays(1).plusWeeks(2).withHour(23).withMinute(59).withSecond(59);
            case WEEKLY -> startDate.minusDays(1).plusWeeks(1).withHour(23).withMinute(59).withSecond(59);
            case DAILY -> startDate.withHour(23).withMinute(59).withSecond(59);
        };
    }
}
