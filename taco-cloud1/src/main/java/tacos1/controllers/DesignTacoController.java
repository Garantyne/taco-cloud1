package tacos1.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;

import tacos1.Repository.IngredientRepository;
import tacos1.entity.Ingredient;
import tacos1.entity.Ingredient.Type;
import tacos1.entity.Taco;
import tacos1.entity.TacoOrder;




@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(
                type.toString().toLowerCase(),
                filterByType(ingredients,type)
            );
        }
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    //то что мы отправим через пост метод будет обработано тут и после обработки перенаправит нас по указанному адресу
    //и эта аннотация сообщает Реквест маппингу который на уровне класса, что будет пост методы обрабатывать по тмоу же пути
    // что и в реквест маппинге
    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder){
        //System.out.println("ОШИБКА!!!!!" + taco.toString()) ;
        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        //log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingr,
            Type type){
        List<Ingredient> ingredients;
        ingredients = StreamSupport.stream(ingr.spliterator(), false)
                .collect(Collectors.toList());
        return ingredients.stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }
}