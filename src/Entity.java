public class Entity {
    public String Name;
    public Entity(String name) {
        Name = name;
    }
    @Override
    public String toString() {
        return "Name : " + Name;
    }
}
