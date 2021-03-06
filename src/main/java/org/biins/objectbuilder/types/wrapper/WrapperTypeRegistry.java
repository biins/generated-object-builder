package org.biins.objectbuilder.types.wrapper;

import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * @author Martin Janys
 */
public class WrapperTypeRegistry {

    private static final Map<Class<?>, WrapperType<?>> REGISTRY = new HashMap<Class<?>, WrapperType<?>>();
    private static final WrapperType<?>[] PRIMITIVE_TYPES = new WrapperType[]{
            new BooleanWrapperType(),
            new ByteWrapperType(),
            new CharacterWrapperType(),
            new DoubleWrapperType(),
            new FloatWrapperType(),
            new IntegerWrapperType(),
            new LongWrapperType(),
            new ShortWrapperType(),
            new VoidWrapperType()
    };
    public static final Set<Class<?>> WRAPPER_CLASSES = new HashSet<Class<?>>(Arrays.asList(
            Boolean.class,
            Integer.class,
            Character.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Void.class)
    );

    static {
        for (WrapperType<?> primitiveType : PRIMITIVE_TYPES) {
            REGISTRY.put(primitiveType.getType(), primitiveType);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> WrapperType<T> get(Class<T> cls) {
        WrapperType<?> value = REGISTRY.get(cls);
        Validate.notNull(value, "Unknown type " + cls);
        return (WrapperType<T>) value;
    }

}
