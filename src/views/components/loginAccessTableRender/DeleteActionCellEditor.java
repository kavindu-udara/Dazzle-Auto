/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components.loginAccessTableRender;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class DeleteActionCellEditor extends DefaultCellEditor{

    private DeleteActionEvent event;

    public DeleteActionCellEditor(DeleteActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        DeletePanelAction action = new DeletePanelAction();
        action.initEvent(event, row);

        return action;
    }
    
    
}
