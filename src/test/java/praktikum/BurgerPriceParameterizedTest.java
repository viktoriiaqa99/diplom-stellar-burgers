package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    public BurgerPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {10.0f, 5.0f, 25.0f},
                {15.5f, 4.5f, 35.5f},
                {20.0f, 0.0f, 40.0f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    @Test
    public void returnCorrectPriceTest() {
        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
    }
}
