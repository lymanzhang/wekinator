/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditPanel.java
 *
 * Created on Dec 6, 2009, 9:55:51 PM
 */
package wekinator;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultComboBoxModel;
import wekinator.LearningSystem.EvaluationState;
import wekinator.LearningSystem.LearningAlgorithmsInitializationState;
        //TODO TODO TODO: If learning algorithm changed, or re-trained, clear records of previous evaluations


/**
 *
 * @author rebecca
 */
public class EditPanel extends javax.swing.JPanel {

    protected LearningSystem learningSystem = null;
    protected int numParams = 0;
    protected LearnerEditPanel[] learnerPanels = null;
    protected PropertyChangeListener lsPropListener = new PropertyChangeListener() {

        public void propertyChange(PropertyChangeEvent evt) {
            learningSystemPropertyChanged(evt);
        }
    };

    // protected allAccurac
    /** Creates new form EditPanel */
    public EditPanel() {
        initComponents();
    }

    void setLearningSystem(LearningSystem ls) {
        if (learningSystem == ls) {
            return;
        }

        if (learningSystem != null) {
            //TODO: remove listeners, learnerEditPanels
            learningSystem.removePropertyChangeListener(lsPropListener);
        }

        learningSystem = ls;
        numParams = ls.getNumParams();
        learningSystem.addPropertyChangeListener(lsPropListener);

        panelDrilldown.removeAll();
        learnerPanels = new LearnerEditPanel[numParams];
        CardLayout c = (CardLayout) panelDrilldown.getLayout();
        panelAllAccuracy = new AllAccuracy();
        panelAllAccuracy.setLearningSystem(ls);

       // c.addLayoutComponent(panelAllAccuracy, "all");
        panelDrilldown.add(panelAllAccuracy, "all");

        comboEditSelection.removeAllItems();
        Object[] items = new Object[numParams + 1];
       items[0] = makeObj("All models");
         

       // comboEditSelection.addI

        SimpleDataset d = learningSystem.getDataset();

        for (int i = 0; i < numParams; i++) {
            learnerPanels[i] = new LearnerEditPanel(ls, i);
           // c.addLayoutComponent(learnerPanels[i], Integer.toString(i));
            panelDrilldown.add(learnerPanels[i], Integer.toString(i));
            items[i + 1] = makeObj(d.getParameterName(i));
        }
        comboEditSelection.setModel(new DefaultComboBoxModel(items));
        comboEditSelection.setModel(new DefaultComboBoxModel(items));
        c.show(panelDrilldown, "all");

        panelDrilldown.repaint();

        setButtonsEnabled();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboEditSelection = new javax.swing.JComboBox();
        panelDrilldown = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        allAccuracy1 = new wekinator.AllAccuracy();
        panelAllAccuracy = new wekinator.AllAccuracy();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        comboEditSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All models", "Model for Parameter 0 - Bow position", "Model for Parameter 1 - Pow tension", "Modle for Parameter 2 - Panning" }));
        comboEditSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEditSelectionActionPerformed(evt);
            }
        });

        panelDrilldown.setLayout(new java.awt.CardLayout());

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, allAccuracy1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(allAccuracy1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        panelDrilldown.add(jPanel1, "card4");
        panelDrilldown.add(panelAllAccuracy, "card3");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Model settings"));

        jLabel7.setText("Using kNN, 3 features");

        jButton15.setText("Change model type or load...");

        jTextField1.setText("10");

        jLabel9.setText("neighbors");

        jButton18.setText("Retrain using these settings");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Save to file...");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(jButton18)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton1))
                    .add(jLabel7)
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9))
                    .add(jButton19)
                    .add(jButton15))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jLabel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton19)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9))
                .add(34, 34, 34)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton18)
                    .add(jButton1)))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(comboEditSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(panelDrilldown, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 513, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(comboEditSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(panelDrilldown, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 484, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton19ActionPerformed

    private void comboEditSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEditSelectionActionPerformed
        CardLayout c = (CardLayout) panelDrilldown.getLayout();
        int i = comboEditSelection.getSelectedIndex();
        if (i == 0) {
            c.show(panelDrilldown, "all");
        } else {
            c.show(panelDrilldown, Integer.toString(i-1));
        }
}//GEN-LAST:event_comboEditSelectionActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private wekinator.AllAccuracy allAccuracy1;
    private javax.swing.JComboBox comboEditSelection;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private wekinator.AllAccuracy panelAllAccuracy;
    private javax.swing.JPanel panelDrilldown;
    // End of variables declaration//GEN-END:variables

    private Object makeObj(final String item) {
        return new Object() {

            @Override
            public String toString() {
                return item;
            }
        };
    }

    private void learningSystemPropertyChanged(PropertyChangeEvent evt) {
        String s = evt.getPropertyName();
        if (s.equals(LearningSystem.PROP_CVRESULTS)) {
            double[] results = learningSystem.getCvResults();
            panelAllAccuracy.updateResults(results, true);
            for (int i = 0; i < numParams; i++) {
                learnerPanels[i].getAccuracyPanel().updateResults(results, true);
            }
        } else if (s.equals(LearningSystem.PROP_TRAINRESULTS)) {
            double[] results = learningSystem.getCvResults();
            panelAllAccuracy.updateResults(results, false);
            for (int i = 0; i < numParams; i++) {
                learnerPanels[i].getAccuracyPanel().updateResults(results, false);
            }
        } else if (s.equals(LearningSystem.PROP_EVALUATIONSTATE)) {
            EvaluationState es = learningSystem.getEvaluationState();
            if (es == EvaluationState.DONE_EVALUATING) {
                panelAllAccuracy.evaluationFinished();
                for (int i = 0; i < numParams; i++) {
                    learnerPanels[i].getAccuracyPanel().evaluationFinished();
                }
            }

            panelAllAccuracy.setEvaluationEnabled(es != EvaluationState.EVALUTATING);
            for (int i = 0; i < numParams; i++) {
                learnerPanels[i].getAccuracyPanel().setEvaluationEnabled(es != EvaluationState.EVALUTATING);
            }
        }
        setButtonsEnabled();
    }

    protected void setButtonsEnabled() {
        LearningSystem.LearningSystemTrainingState lsts = learningSystem.getSystemTrainingState();
        LearningSystem.DatasetState dbs = learningSystem.getDatasetState();
        LearningSystem.EvaluationState es = learningSystem.getEvaluationState();
        LearningSystem.LearningAlgorithmsInitializationState lais = learningSystem.getInitializationState();
        boolean enable = false;
        if (dbs == learningSystem.datasetState.HAS_DATA 
                && es != LearningSystem.EvaluationState.EVALUTATING
                && lais == LearningAlgorithmsInitializationState.ALL_INITIALIZED
                && lsts != LearningSystem.LearningSystemTrainingState.TRAINING) {
            

                    enable = true;


        } 
        panelAllAccuracy.setEvaluationEnabled(enable);
            for (int i = 0; i < numParams; i++) {
                learnerPanels[i].getAccuracyPanel().setEvaluationEnabled(enable);
            }

    }
}