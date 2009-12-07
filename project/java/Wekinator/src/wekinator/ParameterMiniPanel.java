/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParameterMiniPanel.java
 *
 * Created on Dec 4, 2009, 9:08:43 PM
 */
package wekinator;

import java.awt.CardLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.*;

/**
 *
 * @author rebecca
 */
public class ParameterMiniPanel extends javax.swing.JPanel {

    protected String name = "p";
    protected int max = 2;
    protected boolean isDiscrete = true;
    protected double value = 0.0;
    private ChangeEvent changeEvent = null;

    /** Creates new form ParameterMiniPanel */
    public ParameterMiniPanel() {
        initComponents();
    }

    public ParameterMiniPanel(String name, double value, int max, boolean isDiscrete, boolean use) {
        initComponents();
        this.name = name;
        this.max = max;
        this.isDiscrete = isDiscrete;
        this.value = value;
        setGUI(use);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkUse = new javax.swing.JCheckBox();
        panelValue = new javax.swing.JPanel();
        textValue = new javax.swing.JTextField();
        comboInt = new javax.swing.JComboBox();

        checkUse.setText("asdf");
        checkUse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        checkUse.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        checkUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUseActionPerformed(evt);
            }
        });

        panelValue.setLayout(new java.awt.CardLayout());

        textValue.setText("0.01");
        textValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textValueActionPerformed(evt);
            }
        });
        textValue.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                textValueInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        textValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textValueKeyTyped(evt);
            }
        });
        panelValue.add(textValue, "cardReal");

        comboInt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIntActionPerformed(evt);
            }
        });
        panelValue.add(comboInt, "cardInt");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .add(checkUse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(checkUse)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkUseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUseActionPerformed
        fireStateChanged();
}//GEN-LAST:event_checkUseActionPerformed

    private void textValueInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_textValueInputMethodTextChanged
    }//GEN-LAST:event_textValueInputMethodTextChanged

    private void textValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textValueKeyTyped
        fireStateChanged();
    }//GEN-LAST:event_textValueKeyTyped

    private void textValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textValueActionPerformed

    private void comboIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIntActionPerformed
        fireStateChanged();
}//GEN-LAST:event_comboIntActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkUse;
    private javax.swing.JComboBox comboInt;
    private javax.swing.JPanel panelValue;
    private javax.swing.JTextField textValue;
    // End of variables declaration//GEN-END:variables

    private void setGUI(boolean use) {
        checkUse.setSelected(use);
        checkUse.setText(name);
        CardLayout c = (CardLayout) panelValue.getLayout();

        if (isDiscrete) {
            c.show(panelValue, "cardInt");
          //  ComboBoxModel m = comboInt.getModel();
            Integer[] items = new Integer[max+1];
            for (int i= 0; i <= max; i++) {
                items[i] = new Integer(i);
            }
           comboInt.setModel(new DefaultComboBoxModel(items));
        } else {
            c.show(panelValue, "cardReal");
            textValue.setText(value + "");
        }
    }

    public void setValue(double value) {
        //TODO error checking?
        this.value = value;
        if (isDiscrete) {
            comboInt.setSelectedIndex((int)value); //Test!
        } else {
            textValue.setText(value + "");
        }
    }

    public boolean getUse() {
        return checkUse.isSelected();
    }

    public double getVal() {
        //Try parsing:
        if (isDiscrete) {
            return ((Integer) comboInt.getSelectedItem());
        } else {
            try {
                value = Double.parseDouble(textValue.getText());
            } catch (NumberFormatException ex) {
                 textValue.setText(Integer.toString((int) value));
            }
        }
        return value;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        ParameterMiniPanel p = new ParameterMiniPanel("Param1", 0.0, 5, true, true);
        p.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                System.out.println("I see state");
            }
        });
        f.add(p);
        f.setVisible(true);
        p.setValue(3);
    }

         public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -=2 ) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
            }
        }
    }

}
