import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Human {
    private long id;
    private String name;
    private LocalDate dob;
    private LocalDate dod;
    private Gender gender;
    private List<Human> children;
    private Human mother;
    private Human father;
    private Human spouse;

    public Human(String name, Gender gender, LocalDate dob, LocalDate dod, Human mother, Human father) {
        id = -1;
        this.name = name;
        this.dob = dob;
        this.dod = dod;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        children = new ArrayList<>();
    }

    public Human(String name, Gender gender, LocalDate dob) {
        this(name, gender, dob, null, null, null);
    } 

    public Human(String name, Gender gender, LocalDate dob, Human mother, Human father) {
        this(name, gender, dob, null, mother, father);
    }

    public boolean addChild(Human child) {
        if (!children.contains(child)) {
            children.add(child);
            return true;
        }
        return false;
    }

    public boolean addParent(Human parent) {
        if (parent.getGender().equals(gender.Male)) {
            setFather(parent);
        } else if (parent.getGender().equals(gender.Female)) {
            setMother(parent);
        }
        return true;
    }

    public void setMother(Human mother) { this.mother = mother;}

    public void setFather(Human father) { this.father = father;}

    public void setGender(Gender gender) { this.gender = gender;}

    public void setName(String name) { this.name = name;}

    public Human getMother() { return mother; }

    public Human getFather() { return father; }

    public List<Human> getParents() {
        List<Human> list = new ArrayList<>(2);
        if (father!= null) {
            list.add(father);
        }
        if (mother!= null) {
            list.add(mother);
        }
        return list;
    }

    public int getAge() { 
        if (dob != null) {
            return LocalDate.now().getYear() - dob.getYear();
        } else {
            return dob.getYear() - dod.getYear();
        }
    }

    public void setSpouse(Human spouse) { this.spouse = spouse;}

    public Human getSpouse() { return spouse; }

    public String getName() { return name; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id;}

    public LocalDate getDob() { return dob; }

    public LocalDate getDod() { return dod; }

    public List<Human> getChildren() { return children; }

    public void setDob(LocalDate dob) { this.dob = dob;}

    public void setDod(LocalDate dod) { this.dod = dod;}

    public Gender getGender() { return gender; }

    @Override 
    public String toString() {
        return getInfo();
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append(", имя: ").append(name);
        sb.append(", пол: ").append(getGender());
        sb.append(", возраст: ").append(getAge());
        sb.append(", супруг(а): ");
        sb.append(getSpouseInfo());
        sb.append(", ");
        sb.append(getFatherInfo());
        sb.append(", ");
        sb.append(getMotherInfo());
        sb.append(", ");
        sb.append(getChildrenInfo());
        return sb.toString();
    }

    public String getSpouseInfo() {
        if (this.spouse != null) {
            return this.spouse.getName();
        } else {
            return "Супруг(а) не указан(а)";
        }
    }

    public String getMotherInfo() {
        String res = "мать: ";
        Human mother = getMother();
        if (mother!= null) {
            res += mother.getName();
        } else {
            res += "неизвестна";
        }
        return res;
    }

    public String getFatherInfo() {
        String res = "отец: ";
        Human mother = getFather();
        if (father!= null) {
            res += father.getName();
        } else {
            res += "неизвестен";
        }
        return res;
    }

    public String getChildrenInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("дети: ");
        if (!children.isEmpty()) {
            sb.append(children.stream().map(Human::getName).collect(Collectors.joining(", ")));
        } else {
            sb.append("нет детей");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Human human) {
            return human.getId() == getId();
        }
        return false;
    }
}
