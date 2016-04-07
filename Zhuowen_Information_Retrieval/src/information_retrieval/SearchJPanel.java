/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mengqingwang
 */
public class SearchJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SearchJPanel
     */
    private JPanel userProcessContainer;
    private Read read;
    private IntersectionAndUnion iau;
    private InformationMap intersectMap;
    private InformationMap unionMap;
    private Map<String, Information> intersectioninfomap;
    private Information_Retrieval.ValueComparator intersectbvc;
    private TreeMap<String, Information> intersectsortedMap;
    private Map<String, Information> unioninfomap;
    private Information_Retrieval.ValueComparator unionbvc;
    private TreeMap<String, Information> unionsortedMap;
    
    public SearchJPanel(JPanel userProcessContaier, Read read) {
        initComponents();
        populateAndor();
        this.userProcessContainer = userProcessContaier;
        this.read = read;
        this.iau = new IntersectionAndUnion();
        this.intersectMap = new InformationMap();
        this.unionMap = new InformationMap();
        this.intersectioninfomap = intersectMap.getInfoMap();
        this.intersectbvc = new Information_Retrieval.ValueComparator(intersectioninfomap);
        //this.intersectsortedMap = new TreeMap<String, Information>(intersectbvc);
        this.unioninfomap = unionMap.getInfoMap();
        this.unionbvc = new Information_Retrieval.ValueComparator(unioninfomap);
        //this.unionsortedMap = new TreeMap<String, Information>(unionbvc);
//        Read read = new Read();
//        read.read();
//        for (Map.Entry<String, InformationMap> entry : read.map.entrySet()) {
//            String key = entry.getKey();
//            InformationMap value = entry.getValue();
//            System.out.println("key:" + "\t" + key);
//  
//            Map<String, Information> infomap = value.getInfoMap();
//            Information_Retrieval.ValueComparator bvc =  new Information_Retrieval.ValueComparator(infomap);
//            TreeMap<String,Information> sortedMap = new TreeMap<String,Information>(bvc);
//            
//            sortedMap.putAll(infomap);
//            
//            for (Map.Entry<String, Information> infoentry : sortedMap.entrySet()) {
//                String infokey = infoentry.getKey();
//                Information infovalue = infoentry.getValue();
//                System.out.println("file:" + "\t" + infokey + "\t" + "frequency:" + "\t" + infovalue.getFrequency() + "\t" + "hitrate:" + infovalue.getHitRate() + "\n");
//            }
//        }
    }

    public void populateAndor() {
        andor.removeAllItems();
        andor.addItem("or");
        andor.addItem("and");
    }
    
//    private void populateResultTable(){
//        int rowCount = resultjTable.getRowCount();
//        DefaultTableModel dtm = (DefaultTableModel)resultjTable.getModel();
//        for(int i= rowCount-1; i>=0; i--){
//            dtm.removeRow(i);
//        }
//        
//        for(Map.Entry<String, Information> infoentry : sortedMap.entrySet()){
//            Object []row = new Object[7];
//            row[0] = v;
//            row[1] = v.getVaccineName();
//            row[2] = v.getVaccineId();
//            row[3] = v.getSerialNumber();
//            row[4] = v.getAvailability();
//            row[5] = v.getPrice();
//            row[6] = v.getExpirationDate();
//            dtm.addRow(row);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        word1 = new javax.swing.JTextField();
        word2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultjTable = new javax.swing.JTable();
        andor = new javax.swing.JComboBox();
        selectjButton = new javax.swing.JButton();
        searchjButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        synonymjTable = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(word1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 136, 40));
        add(word2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 136, 40));

        resultjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Word", "File Name", "frequency", "hitrate", "entry"
            }
        ));
        jScrollPane1.setViewportView(resultjTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 630, 170));

        andor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        andor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andorActionPerformed(evt);
            }
        });
        add(andor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        selectjButton.setText("Select");
        selectjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectjButtonActionPerformed(evt);
            }
        });
        add(selectjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, -1, -1));

        searchjButton.setText("Search");
        searchjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchjButtonActionPerformed(evt);
            }
        });
        add(searchjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        jLabel1.setText("Synonym words: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        synonymjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Synonym "
            }
        ));
        jScrollPane2.setViewportView(synonymjTable);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 450, 140));
    }// </editor-fold>//GEN-END:initComponents

    private void andorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andorActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_andorActionPerformed

    private void searchjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchjButtonActionPerformed
        String worda;
        String wordb;

        worda = word1.getText();
        wordb = word2.getText();

        String l = andor.getSelectedItem().toString();

        if (wordb.equals("")) {
            InformationMap value = read.map.get(worda);
            if (value == null) {
                JOptionPane.showMessageDialog(null, "No matched word");
                return;
            }
            Map<String, Information> infomap = value.getInfoMap();
            Information_Retrieval.ValueComparator bvc = new Information_Retrieval.ValueComparator(infomap);
            TreeMap<String, Information> sortedMap = new TreeMap<String, Information>(bvc);
            sortedMap.putAll(infomap);

            int rowCount = resultjTable.getRowCount();
            DefaultTableModel dtm = (DefaultTableModel) resultjTable.getModel();
            for (int i = rowCount - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }
            

            for (Map.Entry<String, Information> infoentry : sortedMap.entrySet()) {
                Object[] row = new Object[5];
                row[0] = worda + wordb;
                row[1] = infoentry.getKey();
                row[2] = infoentry.getValue().getFrequency();
                row[3] = infoentry.getValue().getHitRate() + " views";
                row[4] = infoentry;
                dtm.addRow(row);
            }
            Set<String> synonymList = new HashSet<String>();
            System.setProperty("wordnet.database.dir", "/Users/mumu/Downloads/WordNet-3.0/dict/");
            WordNetDatabase database = WordNetDatabase.getFileInstance();
            Synset[] synsets = database.getSynsets(worda, SynsetType.NOUN);
            NounSynset nounSynset;
            NounSynset[] hyponyms;
            for (int i = 0; i < synsets.length; i++) {
                nounSynset = (NounSynset)(synsets[i]); 
                hyponyms = nounSynset.getHyponyms();
                for(int j = 0; j < nounSynset.getWordForms().length; j++){
                    if(read.map.containsKey(nounSynset.getWordForms()[j])){
                        synonymList.add(nounSynset.getWordForms()[j]);
                    }
                    System.out.println(nounSynset.getWordForms()[j]);
                }
            }
                int rowcount = synonymjTable.getRowCount();
                    DefaultTableModel dtM = (DefaultTableModel) synonymjTable.getModel();
                    for (int i = rowcount - 1; i >= 0; i--) {
                        dtM.removeRow(i);
                    }

                    for (String str : synonymList) {
                        Object[] Row = new Object[1];
                        Row[0] = str;
                        dtM.addRow(Row);
                    }
            
        } else {
            if (l.equals("and")) {
                InformationMap valueA = read.map.get(worda);
                InformationMap valueB = read.map.get(wordb);

                if ((valueA == null) && (valueB == null)) {
                    JOptionPane.showMessageDialog(null, "No matched word");
                    return;
                }

                Map<String, Information> infomapA = valueA.getInfoMap();
                Map<String, Information> infomapB = valueB.getInfoMap();
                Set<String> intersectSet = iau.mapIntersection(infomapA, infomapB);

                    //Map<String, Information> intersectioninfomap = intersectMap.getInfoMap();
                //Information_Retrieval.ValueComparator bvc = new Information_Retrieval.ValueComparator(intersectioninfomap);
                //TreeMap<String, Information> sortedMap = new TreeMap<String, Information>(bvc);
                //intersectsortedMap = new TreeMap<String, Information>(intersectbvc);
                if (intersectioninfomap.isEmpty()) {
                    for (String str : intersectSet) {
                        Integer totalFrequency = infomapA.get(str).getFrequency() + infomapB.get(str).getFrequency();
                        //Integer totalHitrate = infomapA.get(str).getHitRate() + infomapB.get(str).getHitRate();
                        intersectMap.addInformation(str, totalFrequency, 0);
                    }
                    intersectsortedMap = new TreeMap<String, Information>(intersectbvc);    
                    intersectsortedMap.putAll(intersectioninfomap);

                    int rowCount = resultjTable.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) resultjTable.getModel();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }

                    for (Map.Entry<String, Information> infoentry : intersectsortedMap.entrySet()) {
                        Object[] row = new Object[5];
                        row[0] = worda + " and " + wordb;
                        row[1] = infoentry.getKey();
                        row[2] = infoentry.getValue().getFrequency();
                        row[3] = infoentry.getValue().getHitRate() + " views";
                        row[4] = infoentry;
                        dtm.addRow(row);
                    }
                } else {

                    intersectsortedMap = new TreeMap<String, Information>(intersectbvc);
                    intersectsortedMap.putAll(intersectioninfomap);

                    int rowCount = resultjTable.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) resultjTable.getModel();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }

                    for (Map.Entry<String, Information> infoentry : intersectsortedMap.entrySet()) {
                        Object[] row = new Object[5];
                        row[0] = worda + " and " + wordb;
                        row[1] = infoentry.getKey();
                        row[2] = infoentry.getValue().getFrequency();
                        row[3] = infoentry.getValue().getHitRate() + " views";
                        row[4] = infoentry;
                        dtm.addRow(row);
                    }
                }
            } else if (l.equals("or")) {

                InformationMap valueA = read.map.get(worda);
                InformationMap valueB = read.map.get(wordb);

                if ((valueA == null) && (valueB == null)) {
                    JOptionPane.showMessageDialog(null, "No matched word");
                    return;
                }

                Map<String, Information> infomapA = valueA.getInfoMap();
                Map<String, Information> infomapB = valueB.getInfoMap();
                Set<String> unionSet = iau.mapUnion(infomapA, infomapB);

//                    Map<String, Information> unioninfomap = unionMap.getInfoMap();
//                    Information_Retrieval.ValueComparator bvc = new Information_Retrieval.ValueComparator(unioninfomap);
//                    TreeMap<String, Information> sortedMap = new TreeMap<String, Information>(bvc);
                if (unioninfomap.isEmpty()) {

                    for (String str : unionSet) {
                        Information infoA = infomapA.get(str);
                        Information infoB = infomapB.get(str);

                        if ((infoA != null) && (infoB != null)) {
                            Integer totalFrequency = infomapA.get(str).getFrequency() + infomapB.get(str).getFrequency();
                            unionMap.addInformation(str, totalFrequency, 0);
                        } else if (infoA == null) {
                            unionMap.addInformation(str, infomapB.get(str).getFrequency(), 0);
                        } else if (infoB == null) {
                            unionMap.addInformation(str, infomapA.get(str).getFrequency(), 0);
                        }
                    }
                    unionsortedMap = new TreeMap<String, Information>(unionbvc);
                    unionsortedMap.putAll(unioninfomap);

                    int rowCount = resultjTable.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) resultjTable.getModel();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }

                    for (Map.Entry<String, Information> infoentry : unionsortedMap.entrySet()) {
                        Object[] row = new Object[5];
                        row[0] = worda + " or " + wordb;
                        row[1] = infoentry.getKey();
                        row[2] = infoentry.getValue().getFrequency();
                        row[3] = infoentry.getValue().getHitRate() + " views";
                        row[4] = infoentry;
                        dtm.addRow(row);
                    }
                } else {
                    unionsortedMap = new TreeMap<String, Information>(unionbvc);
                    unionsortedMap.putAll(unioninfomap);
                    
                    int rowCount = resultjTable.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) resultjTable.getModel();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }

                    for (Map.Entry<String, Information> infoentry : unionsortedMap.entrySet()) {
                        Object[] row = new Object[5];
                        row[0] = worda + " and " + wordb;
                        row[1] = infoentry.getKey();
                        row[2] = infoentry.getValue().getFrequency();
                        row[3] = infoentry.getValue().getHitRate() + " views";
                        row[4] = infoentry;
                        dtm.addRow(row);
                    }
                }
            }
        }
    }//GEN-LAST:event_searchjButtonActionPerformed

    private void selectjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectjButtonActionPerformed
        int selectedrow = resultjTable.getSelectedRow();
        if(selectedrow >= 0){
            Map.Entry<String, Information> infoentry = (Map.Entry<String, Information>)resultjTable.getValueAt(selectedrow, 4);
            String file = (String)resultjTable.getValueAt(selectedrow, 1);
            infoentry.getValue().addHitRate();
            System.out.println(infoentry.getKey());
            System.out.println(infoentry.getValue().getFrequency());
            System.out.println(infoentry.getValue().getHitRate()); 
            
            Reader reader = null;
            String txt = null;
            
            try {
                int character;
                File f = new File(file);
                reader = new InputStreamReader(new FileInputStream(f));
                StringBuffer s = new StringBuffer("");
                
                while ((character = reader.read()) != -1) {
                    char temp = (char) character;
                    
                        s = s.append(temp);
                    }
                txt=s.toString();
                } catch (IOException ex) {
                Logger.getLogger(SearchJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(SearchJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
         catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        
            txtJPanel txtpanel = new txtJPanel(userProcessContainer, txt);
            userProcessContainer.add("txtpanel",txtpanel);
            CardLayout layout = (CardLayout)userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        
        }   
    }//GEN-LAST:event_selectjButtonActionPerformed


//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SearchJPanel(userProcessContaier).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox andor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable resultjTable;
    private javax.swing.JButton searchjButton;
    private javax.swing.JButton selectjButton;
    private javax.swing.JTable synonymjTable;
    private javax.swing.JTextField word1;
    private javax.swing.JTextField word2;
    // End of variables declaration//GEN-END:variables
    public static class ValueComparator implements Comparator<String> {

        Map<String, Information> base;
        

        public ValueComparator(Map<String, Information> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(String a, String b) {
            if (base.get(a).getHitRate() >= base.get(b).getHitRate()) {
                if (base.get(a).getFrequency() >= base.get(b).getFrequency()) {
                    return -1;
                } else {
                    return 1;
                }
            }else{
                return 1;
            }
              // returning 0 would merge keys
        }
        
    }

}


