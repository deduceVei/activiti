package activi.com.model;

public class TestActivi {
    private Integer id;

    private String examp;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamp() {
        return examp;
    }

    public void setExamp(String examp) {
        this.examp = examp == null ? null : examp.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}