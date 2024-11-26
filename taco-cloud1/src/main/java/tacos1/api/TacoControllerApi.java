package tacos1.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos1.Repository.TacoRepository;
import tacos1.entity.Taco;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoControllerApi {
    private TacoRepository tacoRes;
    public TacoControllerApi(TacoRepository tacoRes){
        this.tacoRes = tacoRes;
    }
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return tacoRes.findAll(page);
    }
    /*
    @GetMapping("/{id}")
    public Optional<Taco> tacoById(@PathVariable ("id") long id){
        return tacoRes.findById(id);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") long id){
        Optional<Taco> opTac = tacoRes.findById(id);
        if(opTac.isPresent()){
            return new ResponseEntity<>(opTac.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRes.save(taco);
    }

}
