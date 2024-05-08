package Controller;

import BBridge.Entity;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.BBModel;

public class EntityController
{
	BBModel model;

    @FXML
    private ListView<Entity> Entitylistview;
	public void setModel(BBModel newModel)
	 {
	    model = newModel;
	    Entitylistview.setItems(model.getEntlist());
	 //   System.exit(1);
	 }

}
