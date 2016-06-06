/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.gui;

import cz.muni.fi.pb138.odssearch.OdsSearch;
import cz.muni.fi.pb138.odssearch.QueryItem;
import org.odftoolkit.simple.SpreadsheetDocument;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ladislav Otoupal (422520)
 * @author Tomáš Ševců (422519)
 */
public class MainJFrameForm extends javax.swing.JFrame {

    private static final String BORDER_TITLE = "Search expression in ODS file: ";

    private String mFilePath;
    private String mFileName;
    private SpreadsheetDocument mDocument;
    private boolean mSearching = false;
    
    private boolean caseBox = true;
    private boolean exactBox = true;
    private boolean regexBox = true;      

    private List<QueryItem> mItems = new ArrayList<>();

    private class ItemFinder extends SwingWorker<Boolean, Object> {

        @Override
        public Boolean doInBackground() {
            final OdsSearch odsSearch = new OdsSearch(mDocument);
            odsSearch.setExactMatch(exactMatchCheckBox.isSelected());
            odsSearch.setCaseSensitive(caseSensitiveCheckBox.isSelected());
            odsSearch.setRegexMatch(regexMatchCheckBox.isSelected());
            mItems = odsSearch.search(searchTextField.getText());
            return true;
        }

        @Override
        protected void done() {
            mSearching = false;
            searchButton.setText("Search");

            DefaultTableModel model = (DefaultTableModel) foundDataTable.getModel();

            for (QueryItem item : mItems) {
                Object[] row = {
                        item.getTableName(),
                        item.getCellValue(),
                        item.getColumnName(),
                        item.getCol() + 1,
                        item.getRow() + 1};

                model.addRow(row);
            }
            enableBoxes(caseBox, exactBox, regexBox);
            model.fireTableDataChanged();
        }
    }

    /**
     * Creates new form MainJFrameForm
     */
    public MainJFrameForm() {
        initComponents();
        mFilePath = null;
        mFileName = "No file selected";

        setBorderTitle();
    }

    /**
     * Method set title in first panel
     */
    private void setBorderTitle() {
        String titleString = BORDER_TITLE + mFileName;
        TitledBorder title = BorderFactory.createTitledBorder(titleString);
        searchStringPanel.setBorder(title);
    }

    /**
     * Class for filtering ods files in file chooser
     */
    private class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".ods");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Open documents (*.ods)";
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        searchStringPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        caseSensitiveCheckBox = new javax.swing.JCheckBox();
        exactMatchCheckBox = new javax.swing.JCheckBox();
        regexMatchCheckBox = new javax.swing.JCheckBox();
        foundDataPanel = new javax.swing.JPanel();
        foundDataScrollPane = new javax.swing.JScrollPane();
        foundDataTable = new javax.swing.JTable();
        MenuBar = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        chooseFileMenuItem = new javax.swing.JMenuItem();
        exitProgramMenuItem = new javax.swing.JMenuItem();

        fileChooser.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ODS Search");

        searchStringPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search string"));

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyPressed(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchLabel.setText("Search: ");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        caseSensitiveCheckBox.setText("Case sensitive");
        caseSensitiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseSensitiveCheckBoxActionPerformed(evt);
            }
        });

        exactMatchCheckBox.setText("Exact match");
        exactMatchCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exactMatchCheckBoxActionPerformed(evt);
            }
        });

        regexMatchCheckBox.setText("Regex match");
        regexMatchCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regexMatchCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchStringPanelLayout = new javax.swing.GroupLayout(searchStringPanel);
        searchStringPanel.setLayout(searchStringPanelLayout);
        searchStringPanelLayout.setHorizontalGroup(
                searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchStringPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(searchStringPanelLayout.createSequentialGroup()
                                                .addComponent(searchLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(caseSensitiveCheckBox)
                                        .addComponent(exactMatchCheckBox)
                                        .addComponent(regexMatchCheckBox))
                                .addContainerGap(36, Short.MAX_VALUE))
        );
        searchStringPanelLayout.setVerticalGroup(
                searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchStringPanelLayout.createSequentialGroup()
                                .addGroup(searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(searchStringPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(searchStringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(searchStringPanelLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(caseSensitiveCheckBox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(exactMatchCheckBox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(regexMatchCheckBox)))
                                .addGap(39, 39, 39))
        );

        foundDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Found fields"));

        foundDataTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null}
                },
                new String[]{
                        "Table", "Cell value", "Column name", "Column number", "Row number"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        foundDataScrollPane.setViewportView(foundDataTable);
        if (foundDataTable.getColumnModel().getColumnCount() > 0) {
            foundDataTable.getColumnModel().getColumn(3).setMinWidth(100);
            foundDataTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            foundDataTable.getColumnModel().getColumn(3).setMaxWidth(100);
            foundDataTable.getColumnModel().getColumn(4).setMinWidth(100);
            foundDataTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            foundDataTable.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        javax.swing.GroupLayout foundDataPanelLayout = new javax.swing.GroupLayout(foundDataPanel);
        foundDataPanel.setLayout(foundDataPanelLayout);
        foundDataPanelLayout.setHorizontalGroup(
                foundDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(foundDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(foundDataScrollPane)
                                .addContainerGap())
        );
        foundDataPanelLayout.setVerticalGroup(
                foundDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(foundDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(foundDataScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                .addContainerGap())
        );

        Menu.setText("File");

        chooseFileMenuItem.setText("Open file");
        chooseFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileMenuItemActionPerformed(evt);
            }
        });
        Menu.add(chooseFileMenuItem);

        exitProgramMenuItem.setText("Exit");
        exitProgramMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitProgramMenuItemActionPerformed(evt);
            }
        });
        Menu.add(exitProgramMenuItem);

        MenuBar.add(Menu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(searchStringPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(foundDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(searchStringPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(foundDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Event for choosing file
     *
     * @param evt event argument
     */
    private void chooseFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileMenuItemActionPerformed
        int retValue = fileChooser.showOpenDialog(this);

        if (retValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                mFilePath = file.getCanonicalPath();
                mFileName = file.getName();
                mDocument = SpreadsheetDocument.loadDocument(mFilePath);
            } catch (Exception ex) {
                mFilePath = null;
                JOptionPane.showMessageDialog(this,
                        String.format("Problem accessing file %s", file.getName()), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            mFilePath = null;
        }
        setBorderTitle();
    }//GEN-LAST:event_chooseFileMenuItemActionPerformed

    /**
     * Event for exiting application
     *
     * @param evt event argument
     */
    private void exitProgramMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitProgramMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitProgramMenuItemActionPerformed

    /**
     * Event for start searching by clicking search button
     *
     * @param evt event argument
     */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             

        if (mDocument == null) {
            JOptionPane.showMessageDialog(this, "No file selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (searchTextField.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Please insert at least onr characters.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        mSearching = !mSearching;

        if (searchTextField.getText().length() >= 1) {

            DefaultTableModel model = (DefaultTableModel) foundDataTable.getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();

            ItemFinder itemFinder = new ItemFinder();

            if (mSearching) {
                searchButton.setText("Stop");
                caseBox = caseSensitiveCheckBox.isEnabled();
                exactBox = exactMatchCheckBox.isEnabled();
                regexBox = regexMatchCheckBox.isEnabled();
                disableBoxes();
                itemFinder.execute();
            } else {
                searchButton.setText("Search");                
                itemFinder.cancel(true);
                enableBoxes(caseBox, exactBox, regexBox);
            }

        }
    }
    
    private void disableBoxes() {
        caseSensitiveCheckBox.setEnabled(false);
        exactMatchCheckBox.setEnabled(false);
        regexMatchCheckBox.setEnabled(false);
        }
 
    private void enableBoxes(boolean caseBox, boolean exactBox, boolean regexBox) {
        caseSensitiveCheckBox.setEnabled(caseBox);
        exactMatchCheckBox.setEnabled(exactBox);
        regexMatchCheckBox.setEnabled(regexBox);
        }
    

    private void exactMatchCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exactMatchCheckBoxActionPerformed
        setRegexButtonState();
    }//GEN-LAST:event_exactMatchCheckBoxActionPerformed

    private void caseSensitiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseSensitiveCheckBoxActionPerformed
        setRegexButtonState();
    }//GEN-LAST:event_caseSensitiveCheckBoxActionPerformed

    private void setRegexButtonState() {
        if (caseSensitiveCheckBox.isSelected() || exactMatchCheckBox.isSelected()) {
            regexMatchCheckBox.setEnabled(false);
            regexMatchCheckBox.setSelected(false);
        } else {
            regexMatchCheckBox.setEnabled(true);
        }
    }

    private void regexMatchCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regexMatchCheckBoxActionPerformed
        if (regexMatchCheckBox.isSelected()) {
            caseSensitiveCheckBox.setEnabled(false);
            caseSensitiveCheckBox.setSelected(false);
            exactMatchCheckBox.setEnabled(false);
            exactMatchCheckBox.setSelected(false);
        } else {
            caseSensitiveCheckBox.setEnabled(true);
            exactMatchCheckBox.setEnabled(true);
        }
    }//GEN-LAST:event_regexMatchCheckBoxActionPerformed


    private void searchTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchButtonActionPerformed(null);
        }
    }//GEN-LAST:event_searchTextFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JCheckBox caseSensitiveCheckBox;
    private javax.swing.JMenuItem chooseFileMenuItem;
    private javax.swing.JCheckBox exactMatchCheckBox;
    private javax.swing.JMenuItem exitProgramMenuItem;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel foundDataPanel;
    private javax.swing.JScrollPane foundDataScrollPane;
    private javax.swing.JTable foundDataTable;
    private javax.swing.JCheckBox instantSearchCheckBox;
    private javax.swing.JCheckBox regexMatchCheckBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchStringPanel;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}


