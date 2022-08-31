package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA",1000,2);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 1000, 1);
        itemRepository.save(item1);
        Item item2 = new Item("item2", 2000, 2);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        //give
        Item item = new Item("item", 1000, 1);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 2000, 2);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}