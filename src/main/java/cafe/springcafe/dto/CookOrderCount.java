package cafe.springcafe.dto;

public class CookOrderCount {
    Long cook_id;
    Long count;

    public CookOrderCount(Long cook_id, Long count) {
        this.cook_id = cook_id;
        this.count = count;
    }

    public Long getCook_id() {
        return cook_id;
    }

    public Long getCount() {
        return count;
    }
}
