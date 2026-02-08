package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsCorrectlyTest() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientToListTest() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientByIndexTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientCorrectlyTest() {
        Ingredient ingredient2 = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getReceipt_returnsCorrectReceipt() {
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(10.0f);

        when(ingredient.getName()).thenReturn("Hot Sauce");
        when(ingredient.getPrice()).thenReturn(5.0f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        receipt = receipt.replace(',', '.');

        String nl = System.lineSeparator();
        String expected = "(==== Black Bun ====)" + nl +
                "= sauce Hot Sauce =" + nl +
                "(==== Black Bun ====)" + nl +
                nl +
                "Price: 25.000000" + nl;

        assertEquals(expected, receipt);
    }

}
