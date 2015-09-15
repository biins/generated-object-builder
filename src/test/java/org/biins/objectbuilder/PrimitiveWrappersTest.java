package org.biins.objectbuilder;

import org.biins.objectbuilder.builder.strategy.PrimitiveGeneratorStrategy;
import org.biins.objectbuilder.builder.strategy.WrapperGeneratorStrategy;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.biins.objectbuilder.ConstantPool.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Martin Janys
 */
public class PrimitiveWrappersTest {

    @DataProvider(name = "buildStrategy")
    public static Object[][] buildStrategy() {
        return new Object[][] {
                {WrapperGeneratorStrategy.MIN},
                {WrapperGeneratorStrategy.DEFAULT},
                {WrapperGeneratorStrategy.MAX},
                {WrapperGeneratorStrategy.NULL},
                {WrapperGeneratorStrategy.RANDOM}
        };
    }

    @Test(dataProvider = "buildStrategy")
    public void wrapperObject(WrapperGeneratorStrategy buildStrategy) {
        Byte b = GenerateObject.forType(Byte.class)
                .onPrimitiveProperty().setGeneratorStrategy(PrimitiveGeneratorStrategy.DEFAULT)
                .onWrapper().setGeneratorStrategy(buildStrategy)
                .build();
        Short s = GenerateObject.forType(Short.class)
                .onPrimitiveProperty().setGeneratorStrategy(PrimitiveGeneratorStrategy.DEFAULT)
                .onWrapper().setGeneratorStrategy(buildStrategy)
                .build();
        Integer i = GenerateObject.forType(Integer.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();
        Long l = GenerateObject.forType(Long.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();
        Float f = GenerateObject.forType(Float.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();
        Double d = GenerateObject.forType(Double.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();
        Character c = GenerateObject.forType(Character.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();
        Boolean bool = GenerateObject.forType(Boolean.class)
                .onWrapperProperty().setGeneratorStrategy(buildStrategy)
                .build();

        switch (buildStrategy) {
            case DEFAULT:
                assertEquals(b, BYTE_WRAPPER_DEFAULT);
                assertEquals(s, SHORT_WRAPPER_DEFAULT);
                assertEquals(i, INTEGER_WRAPPER_DEFAULT);
                assertEquals(l, LONG_WRAPPER_DEFAULT);
                assertEquals(f, FLOAT_WRAPPER_DEFAULT);
                assertEquals(d, DOUBLE_WRAPPER_DEFAULT);
                assertEquals(c, CHARACTER_WRAPPER_DEFAULT);
                assertEquals(bool, BOOLEAN_WRAPPER_DEFAULT);
                break;
            case MIN:
                assertEquals(b, (Byte) Byte.MIN_VALUE);
                assertEquals(s, (Short) Short.MIN_VALUE);
                assertEquals(i, (Integer) Integer.MIN_VALUE);
                assertEquals(l, (Long) Long.MIN_VALUE);
                assertEquals(f, (Float) Float.MIN_VALUE);
                assertEquals(d, (Double) Double.MIN_VALUE);
                assertEquals(c, (Character) Character.MIN_VALUE);
                assertEquals(bool, (Boolean) Boolean.FALSE);
                break;
            case MAX:
                assertEquals(b, (Byte) Byte.MAX_VALUE);
                assertEquals(s, (Short) Short.MAX_VALUE);
                assertEquals(i, (Integer) Integer.MAX_VALUE);
                assertEquals(l, (Long) Long.MAX_VALUE);
                assertEquals(f, (Float) Float.MAX_VALUE);
                assertEquals(d, (Double) Double.MAX_VALUE);
                assertEquals(c, (Character) Character.MAX_VALUE);
                assertEquals(bool, (Boolean) Boolean.TRUE);
                break;
            case NULL:
                assertNull(b);
                assertNull(s);
                assertNull(i);
                assertNull(l);
                assertNull(f);
                assertNull(d);
                assertNull(c);
                assertNull(bool);
                break;
            case RANDOM:
                break;
        }

    }
}
