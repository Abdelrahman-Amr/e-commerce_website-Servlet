package gov.iti.jets.dto;

public abstract class BaseDTO {
    private Long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
