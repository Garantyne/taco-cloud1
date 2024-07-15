package tacos1.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos1.repository.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConvert implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;
    @Autowired
    public IngredientByIdConvert(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id){
        return ingredientRepository.findById(id).orElse(null);
    }
}

