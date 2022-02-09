package netcracker.danilavlebedev.di;

import netcracker.danilavlebedev.utils.customList.CustomList;
import netcracker.danilavlebedev.utils.validator.Validator;

import java.lang.reflect.Field;
import java.util.Set;

public class AutowiredValidatorsAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutowiredValidators.class) && CustomList.class.isAssignableFrom(field.getType())) {
                Set<Class<? extends Validator>> implCommandsClasses = context
                        .getConfig()
                        .getScanner()
                        .getSubTypesOf(Validator.class);
                CustomList<Validator> validators = implCommandsClasses
                        .stream()
                        .map(context::getObject)
                        .collect(CustomList::new, CustomList::add, CustomList::addAll);
                field.setAccessible(true);
                try {
                    field.set(t, validators);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error with injecting to map");
                }
            }
        }
    }
}

