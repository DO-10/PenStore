package objects;

public class Order {
    private String id;
    private String cratedAt;

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return cratedAt;
    }
    public void setCreatedAt(String cratedAt) {
        this.cratedAt = cratedAt;
    }
}

