package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/basic/items")
@Controller
public class basicItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public basicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String save() {
        return "ss";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 100000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }



}
