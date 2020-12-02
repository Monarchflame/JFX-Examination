package fx.component.factory;

import javafx.scene.Node;
import fx.component.CrudGridTable;
import fx.component.model.GridTableModel;
import fx.util.JsonParse;

/**
 * Created by ldh on 2017/4/14.
 */
public class ContentPaneFactory {

    public Node create(String name) {
        GridTableModel tableModel = null;
        try {
            tableModel = JsonParse.parseTableModel("/data/table/" + name + ".json");
            return new CrudGridTable(tableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
