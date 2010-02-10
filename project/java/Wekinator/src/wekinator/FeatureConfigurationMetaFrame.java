/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FeatureConfigurationMetaFrame.java
 *
 * Created on Dec 14, 2009, 12:17:25 AM
 */
package wekinator;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import wekinator.FeatureConfiguration.Feature;

/**
 *
 * @author rebecca
 */
public class FeatureConfigurationMetaFrame extends javax.swing.JFrame {

    FeatureConfiguration fc = null;
    protected FeatureConfigurationPanel parent = null;

    String[] baseFeatureClassNames = null;
    // JCheckBox[][] parentBoxArray; //index by feat #, meta #
    JCheckBox[][][] childBoxArray; //index by feat #, dimension #, meta #
    LinkedList<Feature> featuresInOrder = null;
    // List<JCheckBox

    /** Creates new form FeatureConfigurationMetaFrame */
    public FeatureConfigurationMetaFrame() {
        initComponents();
    }

    public FeatureConfigurationMetaFrame(FeatureConfiguration fc, FeatureConfigurationPanel p) {
        initComponents();
        this.fc = fc;
        parent = p;
        featuresInOrder = fc.featuresInOrder;
        baseFeatureClassNames = fc.getEnabledBaseFeatureClassNames();
        int numBaseEnabled = fc.getNumBaseFeaturesEnabled();
        int numMeta = MetaFeature.Type.values().length;
        String[] names = fc.getBaseEnabledFeatureNames();

        //    parentBoxArray = new JCheckBox[numBaseEnabled][numMeta];
        childBoxArray = new JCheckBox[numBaseEnabled][][]; //indexed by feature#, dim#, meta#

        JPanel test1 = new JPanel();
        test1.setLayout(new GridLayout(numBaseEnabled, 1 + numMeta));
        //  int fNum = 0;
        int nameNum = 0;
        int featNum = 0;
        MetaFeature.Type[] mfTypes = MetaFeature.Type.values();

        for (int h = 0; h < featuresInOrder.size(); h++) {
            Feature f = featuresInOrder.get(h);
            if (f.enabled) {
                ArrayList<LinkedList<MetaFeature>> fExistingMeta = f.metaFeatures;

                //Add parent row
                childBoxArray[featNum] = new JCheckBox[f.getDimensionality()][numMeta];
                for (int i = 0; i < f.getDimensionality(); i++) {
                    test1.add(new JLabel(names[nameNum++]));
                    for (int j = 0; j < mfTypes.length; j++) {
                        JCheckBox b = new JCheckBox(MetaFeature.nameForType(mfTypes[j]));
                        childBoxArray[featNum][i][j] = b;
                        //Is checked?
                        //TODO: this causes error: i may be out of bounds (custom chuck features?)
                        //f.metaFeatures is not initialized yet!!
                        for (MetaFeature mf : f.metaFeatures.get(i)) { //causes error for hid device
                            if (mf.myName.equals(MetaFeature.nameForType(mfTypes[j]))) {
                                b.setSelected(true);
                                break;
                            }
                        }
                        test1.add(b);
                    }
                }
                featNum++;
            }
        }

        panelFeatures.add(test1);
        panelFeatures.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollFeaturePanel = new javax.swing.JScrollPane();
        panelFeatures = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setForeground(java.awt.Color.white);

        panelFeatures.setLayout(new javax.swing.BoxLayout(panelFeatures, javax.swing.BoxLayout.Y_AXIS));
        scrollFeaturePanel.setViewportView(panelFeatures);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollFeaturePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 239, Short.MAX_VALUE)
                .add(jButton2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(scrollFeaturePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 260, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        commit();
        this.dispose();     
    }//GEN-LAST:event_jButton1ActionPerformed

    protected void commit() {
        HashMap<String, ArrayList<LinkedList<MetaFeature>>> allMetaFeatures = new HashMap<String, ArrayList<LinkedList<MetaFeature>>>();
        MetaFeature.Type[] mfTypes = MetaFeature.Type.values();
        int i = 0;
        for (Feature f : featuresInOrder) {
            if (f.enabled) {
                System.out.println("i = " + i);
                ArrayList<LinkedList<MetaFeature>> flist = new ArrayList<LinkedList<MetaFeature>>(childBoxArray[i].length);
                allMetaFeatures.put(baseFeatureClassNames[i], flist);
                for (int j = 0; j < childBoxArray[i].length; j++) {
                    LinkedList<MetaFeature> mflist = new LinkedList<MetaFeature>();
                    flist.add(mflist);
                    for (int k = 0; k < childBoxArray[i][j].length; k++) {
                        if (childBoxArray[i][j][k].isSelected()) {
                            mflist.add(MetaFeature.createForType(mfTypes[k], f));
                        }
                    }
                }
                f.metaFeatures = flist;
                i++;
            }
          
        }

      //  fc.setMetaFeaturesFromMatrix(allMetaFeatures);
        parent.setMetaFeatureMatrix(allMetaFeatures);
    }

    private void addTestFeatures() {
        /* JPanel test1 = new JPanel();
        test1.setLayout(new BoxLayout(test1, BoxLayout.X_AXIS));
        test1.add(new JLabel("My name 1"));
        test1.add(new JCheckBox("d0"));
        test1.add(new JCheckBox("d1"));

        JPanel test2 = new JPanel();
        test2.setLayout(new BoxLayout(test2, BoxLayout.X_AXIS));
        test2.add(new JLabel("My nameasdfadfadsf 1"));
        test2.add(new JCheckBox("d0"));
        test2.add(new JCheckBox("d1")); */
        JPanel test1 = new JPanel();
        test1.setLayout(new GridLayout(2, 3));
        test1.add(new JLabel("My name 1"));
        test1.add(new JCheckBox("d0"));
        test1.add(new JCheckBox("d1"));

        test1.add(new JLabel("My name 22"));
        test1.add(new JCheckBox("d0"));
        test1.add(new JCheckBox("d1"));


        panelFeatures.add(test1);
        // panelFeatures.add(test2);
        panelFeatures.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FeatureConfiguration fc = new FeatureConfiguration();
                fc.setUseMotionSensor(true);
             //   fc.setUseFFT(true);
                fc.addMetaFeature(FeatureConfiguration.MOTION, MetaFeature.Type.DELTA_1s, 0);
                try {
                    fc.validate();
                    FeatureConfigurationMetaFrame f = new FeatureConfigurationMetaFrame(fc, null);
                    f.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FeatureConfigurationMetaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }


            // f.addTestFeatures();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel panelFeatures;
    private javax.swing.JScrollPane scrollFeaturePanel;
    // End of variables declaration//GEN-END:variables
}
