package service;

import domain.Gene;
import repository.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }
    public ArrayList<Gene> getGenes(){
        return repo.getGenes();
    }

    public ArrayList<Gene> getGeneSortedByOrganism() {
        return (ArrayList<Gene>) repo.getGenes().stream()
                .sorted(Comparator.comparing(Gene::getOrganism))
                .collect(Collectors.toList());
    }
    public List<String> getAllOrganisms() {
        List<Gene> genes = repo.getGenes();

        // Extract organisms from Gene objects
        List<String> organisms = new ArrayList<>();
        for (Gene gene : genes) {
            organisms.add(gene.getOrganism());
        }
        return organisms;
    }
    public ArrayList<Gene> getFilteredByNameOrFunction(String source) {
        return (ArrayList<Gene>) repo.getGenes().stream()
                .filter(r -> Objects.equals(r.getName(), source) || Objects.equals(r.getFunction(), source))
                //.filter(r -> r.getOrganism()==source2)
                .collect(Collectors.toList());
    }
    public ArrayList<Gene> getFilteredByOrganism(String source) {
        return (ArrayList<Gene>) repo.getGenes().stream()
                .filter(r -> Objects.equals(r.getOrganism(),source))
                .collect(Collectors.toList());
    }

    public void updateGene(Gene gene, String function, String sequence){
        gene.setFunction(function);
        gene.setSequence(sequence);
        try {
            this.repo.update(gene);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
