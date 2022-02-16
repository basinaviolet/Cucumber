package api.pojo;

public class UserRootData {
    private String id;
    private String fullName;
    private String url;
    private String username;
    private String status;
    private String email;

    public UserRootData(String id, String fullName, String url, String username, String status, String email) {
        this.id = id;
        this.fullName = fullName;
        this.url = url;
        this.username = username;
        this.status = status;
        this.email = email;
    }

    public UserRootData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
