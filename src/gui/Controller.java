package gui;

import domain.Gene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }
    @FXML
    private ListView<Gene> listView;
    @FXML
    private ComboBox<String> organismCBox;

    @FXML
    private TextField textField;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea sequenceTextArea;
    @FXML
    private TextField functionTextField;
    @FXML
    private Button updateButton;

    @FXML
    void onComboBoxAction(ActionEvent event) {
        String organism=organismCBox.getValue();
    }

    public void initialize(){
        this.listView.setItems(FXCollections.observableArrayList(this.service.getGeneSortedByOrganism()));
        this.organismCBox.setItems(FXCollections.observableArrayList(this.service.getAllOrganisms()));
    }


    void populateList(){
        ObservableList<Gene> geneList = FXCollections.observableArrayList(service.getGenes());
        listView.setItems(geneList);
    }
    @FXML
    void search(ActionEvent event) {
        String searchButton = textField.getText();
        String organismGetCBox=organismCBox.getValue();
        if (searchButton.equals(""))
            populateList();
        else {
            ObservableList<Gene>   filteredGenes= FXCollections.observableArrayList(service.getFilteredByOrganism(organismGetCBox));
            filteredGenes = FXCollections.observableArrayList(service.getFilteredByNameOrFunction(textField.getText()));

            listView.setItems(filteredGenes);
        }
    }
    @FXML
    void  mouseClick(MouseEvent event) {
        Gene selectedValue = listView.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            functionTextField.setText(selectedValue.getFunction());
            sequenceTextArea.setText(selectedValue.getSequence());
        } else {
            functionTextField.clear();
            sequenceTextArea.clear();
        }
    }

    @FXML
    void update(ActionEvent event) {
        String function= functionTextField.getText();
        String sequence=sequenceTextArea.getText();

        Gene selectedValue = listView.getSelectionModel().getSelectedItem();
        service.updateGene(selectedValue,function,sequence);
        populateList();
    }


}
