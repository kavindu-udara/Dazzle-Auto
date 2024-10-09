/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.vehicle.vehicleTableRender;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class HistoryActionCellEditor extends DefaultCellEditor{

    private HistoryActionEvent event;

    public HistoryActionCellEditor(HistoryActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        HistoryPanelAction action = new HistoryPanelAction();
        action.initEvent(event, row);

        return action;
    }
    
    
}
