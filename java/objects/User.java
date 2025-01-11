package objects;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String photo;
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }
    public String getPhoto(){
        return photo;
    }
}
