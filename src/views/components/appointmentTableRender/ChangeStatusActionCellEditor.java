/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components.appointmentTableRender;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ChangeStatusActionCellEditor extends DefaultCellEditor{

    private ChangeStatusActionEvent event;

    public ChangeStatusActionCellEditor(ChangeStatusActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        ChangeStatusPanelAction action = new ChangeStatusPanelAction();
        action.initEvent(event, row);

        return action;
    }
    
    
}
