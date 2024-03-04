package domain;

public class Gene {
    private String name;
    private String function;
    private String organism;
    private String sequence;

    public Gene(String name, String function, String organism, String sequence) {
        this.name = name;
        this.function = function;
        this.organism = organism;
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", organism='" + organism + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
