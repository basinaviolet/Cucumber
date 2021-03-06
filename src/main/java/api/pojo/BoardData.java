package api.pojo;

public class BoardData {
    private String id;
    private String name;
    private String url;

    public BoardData(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public BoardData() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "BoardData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
