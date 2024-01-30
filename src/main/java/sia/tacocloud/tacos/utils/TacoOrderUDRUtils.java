package sia.tacocloud.tacos.utils;

import sia.tacocloud.tacos.entities.Taco;
import sia.tacocloud.tacos.entities.udt.TacoUDT;

public class TacoOrderUDRUtils {
    public static TacoUDT toTacoUDT(Taco taco){
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }
}
