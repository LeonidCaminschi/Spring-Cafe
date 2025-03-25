package cafe.springcafe.projection;

public class CookOrderCountResponse {
    Long cookId;
    Long count;

    public CookOrderCountResponse(Long cook_id, Long count) {
        this.cookId = cook_id;
        this.count = count;
    }

    public Long getCookId() {
        return cookId;
    }

    public Long getCount() {
        return count;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
