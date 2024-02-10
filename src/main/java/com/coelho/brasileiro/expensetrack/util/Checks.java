package com.coelho.brasileiro.expensetrack.util;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;

import java.util.function.Predicate;

public class Checks {

    /**
     * The {@code isNull} method checks if the provided object is null.
     *
     * @param object The object to be checked for null.
     * @return {@code true} if the object is null, {@code false} otherwise.
     *
     * @example
     * <pre>
     * {@code
     * Object myObject = // ... initialize the object as needed ...
     * if (isNull(myObject)) {
     *     // The object is null, handle accordingly...
     * } else {
     *     // The object is not null, proceed with further actions...
     * }
     * }
     * </pre>
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * The {@code check} method is a utility function that allows checking a condition represented by a {@code Predicate} against a specific context.
     * If the condition is not met, a {@code BusinessException} of the provided code is thrown.
     *
     * @param predicate A predicate defining the condition to be checked.
     * @param context   The context on which the condition will be verified.
     * @param code      The code to be used in the {@code BusinessException} if the condition is not met.
     *
     * @throws BusinessException Thrown if the condition represented by the {@code predicate} is not met.
     *
     * @example
     * <pre>
     * {@code
     * import java.util.function.Predicate;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *         // Define a context
     *         Context context = // ... initialize the context as needed ...
     *
     *         // Define a predicate to check the desired condition
     *         Predicate<Context> myPredicate = // ... define the predicate as needed ...
     *
     *         // Call the check method to verify the condition
     *         try {
     *             check(myPredicate, context, 1001);
     *             // The condition was met, continue with the code here...
     *         } catch (BusinessException e) {
     *             // The condition was not met, handle the exception as needed...
     *             System.err.println("Error: " + e.getMessage());
     *         }
     *     }
     *
     *     // ... other parts of the Example class and definition of the Context class ...
     * }
     * }
     * </pre>
     */
    public static void check(Predicate<Context> predicate, Context context, Integer  code) {
        if (!predicate.test(context)) {
            throw new BusinessException(code);
        }
    }

    /**
     * The {@code isNotNull} method checks if the provided object is not null.
     *
     * @param object The object to be checked for non-nullness.
     * @return {@code true} if the object is not null, {@code false} otherwise.
     *
     * @example
     * <pre>
     * {@code
     * Object myObject = // ... initialize the object as needed ...
     * if (isNotNull(myObject)) {
     *     // The object is not null, proceed with further actions...
     * } else {
     *     // The object is null, handle accordingly...
     * }
     * }
     * </pre>
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

    /**
     * The {@code isEmptyOrNull} method checks if the provided string is either null or empty.
     *
     * @param string The string to be checked for null or emptiness.
     * @return {@code true} if the string is null or empty, {@code false} otherwise.
     *
     * @example
     * <pre>
     * {@code
     * String myString = // ... initialize the string as needed ...
     * if (isEmptyOrNull(myString)) {
     *     // The string is either null or empty, handle accordingly...
     * } else {
     *     // The string is not null and not empty, proceed with further actions...
     * }
     * }
     * </pre>
     */
    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * The {@code isNotEmptyOrNotNull} method checks if the provided string is not null and not empty.
     *
     * @param string The string to be checked for non-nullness and non-emptiness.
     * @return {@code true} if the string is not null and not empty, {@code false} otherwise.
     *
     * @example
     * <pre>
     * {@code
     * String myString = // ... initialize the string as needed ...
     * if (isNotEmptyOrNotNull(myString)) {
     *     // The string is not null and not empty, proceed with further actions...
     * } else {
     *     // The string is either null or empty, handle accordingly...
     * }
     * }
     * </pre>
     */
    public static boolean isNotEmptyOrNotNull(String string) {
        return !isEmptyOrNull(string);
    }

}
