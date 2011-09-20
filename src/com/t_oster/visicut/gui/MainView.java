/**
 * This file is part of VisiCut.
 * 
 *     VisiCut is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *    VisiCut is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 * 
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with VisiCut.  If not, see <http://www.gnu.org/licenses/>.
 **/

/*
 * MainView.java
 *
 * Created on 18.08.2011, 01:42:04
 */
package com.t_oster.visicut.gui;

import com.t_oster.liblasercut.IllegalJobException;
import com.t_oster.visicut.misc.ExtensionFilter;
import com.t_oster.visicut.misc.Helper;
import com.t_oster.visicut.managers.PreferencesManager;
import com.t_oster.visicut.VisicutModel;
import com.t_oster.visicut.gui.beans.EditRectangle;
import com.t_oster.visicut.gui.beans.EditRectangle.Button;
import com.t_oster.visicut.gui.beans.ImageComboBox;
import com.t_oster.visicut.gui.mappingdialog.MappingDialog;
import com.t_oster.visicut.gui.mappingwizzard.MappingWizzard;
import com.t_oster.visicut.misc.MultiFilter;
import com.t_oster.visicut.model.LaserDevice;
import com.t_oster.visicut.model.mapping.Mapping;
import com.t_oster.visicut.model.MaterialProfile;
import com.t_oster.visicut.model.graphicelements.GraphicSet;
import com.t_oster.visicut.model.mapping.MappingSet;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.application.Action;

/**
 *
 * @author thommy
 */
public class MainView extends javax.swing.JFrame
{

  /** Creates new form MainView */
  public MainView()
  {
    initComponents();
    this.visicutModel1.setMaterial(null);
    this.visicutModel1.setMappings(null);
    fillComboBoxes();
    refreshComboBoxes();
    this.visicutModel1.setPreferences(PreferencesManager.getInstance().getPreferences());

    int def = this.visicutModel1.getPreferences().getDefaultLaserDevice();
    if (def + 1 < this.laserCutterComboBox.getItemCount())
    {
      this.laserCutterComboBox.setSelectedIndex(def + 1);
    }
    if (this.visicutModel1.getSelectedLaserDevice() != null && this.visicutModel1.getSelectedLaserDevice().getCameraURL() != null)
    {
      this.captureImage();
    }
    String[] args = VisicutApp.getApplication().getProgramArguments();
    for (String s : args)
    {
      File f = new File(s);
      if (f.exists())
      {
        this.loadFile(f);
      }
    }

  }

  /*
   * Initially fills LaserCutter,Material and Mapping ComboBox with all possible Elements
   */
  private void fillComboBoxes()
  {
    LaserDevice sld = this.visicutModel1.getSelectedLaserDevice();
    this.laserCutterComboBox.removeAllItems();
    this.laserCutterComboBox.addItem(null);
    this.laserCutterComboBox.setSelectedIndex(0);
    for (LaserDevice ld : PreferencesManager.getInstance().getPreferences().getLaserDevices())
    {
      this.laserCutterComboBox.addItem(ld);
      if (ld.equals(sld))
      {
        this.laserCutterComboBox.setSelectedItem(ld);
      }
    }
    MaterialProfile sp = this.visicutModel1.getMaterial();
    this.materialComboBox.removeAllItems();
    this.materialComboBox.addItem(null);
    this.materialComboBox.setSelectedIndex(0);
    for (MaterialProfile mp : getAllMaterials())
    {
      this.materialComboBox.addItem(mp);
      if (sp != null && sp.getName().equals(mp.getName()) && sp.getDepth() == mp.getDepth())
      {
        this.materialComboBox.setSelectedItem(mp);
      }
    }
    MappingSet ss = this.visicutModel1.getMappings();
    this.mappingComboBox.removeAllItems();
    this.mappingComboBox.addItem(null);
    this.mappingComboBox.setSelectedIndex(0);
    for (MappingSet m : this.mappingManager1.getMappingSets())
    {
      this.mappingComboBox.addItem(m);
      if (m.equals(ss))
      {
        this.mappingComboBox.setSelectedItem(m);
      }
    }
    if (this.custom != null)
    {
      this.mappingComboBox.addItem(custom);
      if (custom.equals(ss))
      {
        this.mappingComboBox.setSelectedItem(custom);
      }
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        visicutModel1 = new com.t_oster.visicut.VisicutModel();
        profileManager1 = new com.t_oster.visicut.managers.ProfileManager();
        filesDropSupport1 = new com.t_oster.visicut.gui.beans.FilesDropSupport();
        mappingManager1 = new com.t_oster.visicut.managers.MappingManager();
        saveFileChooser = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dimensionWidthTextField = new javax.swing.JTextField();
        dimesnionsHeightTextfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        materialHeightTextField = new javax.swing.JTextField();
        materialComboBox = new com.t_oster.visicut.gui.beans.ImageComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        laserCutterComboBox = new com.t_oster.visicut.gui.beans.ImageComboBox();
        mappingComboBox = new com.t_oster.visicut.gui.beans.ImageComboBox();
        customMappingButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        previewPanel = new com.t_oster.visicut.gui.beans.PreviewPanel();
        executeJobButton = new javax.swing.JButton();
        captureImageButton = new javax.swing.JButton();
        togglePreviewButton = new javax.swing.JToggleButton();
        toggleCutLinesButton = new javax.swing.JToggleButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        calibrateCameraMenuItem = new javax.swing.JMenuItem();
        executeJobMenuItem = new javax.swing.JMenuItem();
        editMappingMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        materialMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        showGridMenuItem = new javax.swing.JCheckBoxMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        visicutModel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                visicutModel1PropertyChange(evt);
            }
        });

        filesDropSupport1.setComponent(previewPanel);
        filesDropSupport1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                filesDropSupport1PropertyChange(evt);
            }
        });

        saveFileChooser.setAcceptAllFileFilterUsed(false);
        saveFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveFileChooser.setFileFilter(new ExtensionFilter(".plf", "VisiCut Portable Laser File"));
        saveFileChooser.setName("saveFileChooser"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${loadedFile} - VisiCut"), this, org.jdesktop.beansbinding.BeanProperty.create("title"), "Filename to Title");
        binding.setSourceNullValue("VisiCut");
        bindingGroup.addBinding(binding);

        jPanel2.setName("jPanel2"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.t_oster.visicut.gui.VisicutApp.class).getContext().getResourceMap(MainView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        dimensionWidthTextField.setName("dimensionWidthTextField"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${material.width}"), dimensionWidthTextField, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "tfw"); // NOI18N
        bindingGroup.addBinding(binding);

        dimesnionsHeightTextfield.setName("dimesnionsHeightTextfield"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${material.height}"), dimesnionsHeightTextfield, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "tfh");
        bindingGroup.addBinding(binding);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        materialHeightTextField.setToolTipText(resourceMap.getString("materialHeightTextField.toolTipText")); // NOI18N
        materialHeightTextField.setName("materialHeightTextField"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${material.depth}"), materialHeightTextField, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "tfd");
        bindingGroup.addBinding(binding);

        materialComboBox.setName("materialComboBox"); // NOI18N
        materialComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        laserCutterComboBox.setName("laserCutterComboBox"); // NOI18N
        laserCutterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserCutterComboBoxActionPerformed(evt);
            }
        });

        mappingComboBox.setName("mappingComboBox"); // NOI18N
        mappingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mappingComboBoxActionPerformed(evt);
            }
        });

        customMappingButton.setText(resourceMap.getString("customMappingButton.text")); // NOI18N
        customMappingButton.setEnabled(false);
        customMappingButton.setName("customMappingButton"); // NOI18N
        customMappingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customMappingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(laserCutterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(materialComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(materialHeightTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(dimensionWidthTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dimesnionsHeightTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6))
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(mappingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customMappingButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laserCutterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dimensionWidthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(dimesnionsHeightTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customMappingButton)
                    .addComponent(mappingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        previewPanel.setAutoCenter(true);
        previewPanel.setName("previewPanel"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${backgroundImage}"), previewPanel, org.jdesktop.beansbinding.BeanProperty.create("backgroundImage"), "BackImageFromModel");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${graphicObjects}"), previewPanel, org.jdesktop.beansbinding.BeanProperty.create("graphicObjects"), "ModelToPreviewObjects");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${mappings}"), previewPanel, org.jdesktop.beansbinding.BeanProperty.create("mappings"), "MappingsFromModelToPreviewPanel");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${material}"), previewPanel, org.jdesktop.beansbinding.BeanProperty.create("material"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, visicutModel1, org.jdesktop.beansbinding.ELProperty.create("${selectedLaserDevice.cameraCalibration}"), previewPanel, org.jdesktop.beansbinding.BeanProperty.create("previewTransformation"), "TransformFromModel");
        bindingGroup.addBinding(binding);

        previewPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                previewPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                previewPanelMouseReleased(evt);
            }
        });
        previewPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                previewPanelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                previewPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        executeJobButton.setText(resourceMap.getString("executeJobButton.text")); // NOI18N
        executeJobButton.setName("executeJobButton"); // NOI18N
        executeJobButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeJobButtonActionPerformed(evt);
            }
        });

        captureImageButton.setText(resourceMap.getString("captureImageButton.text")); // NOI18N
        captureImageButton.setName("captureImageButton"); // NOI18N
        captureImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureImageButtonActionPerformed(evt);
            }
        });

        togglePreviewButton.setSelected(true);
        togglePreviewButton.setText(resourceMap.getString("togglePreviewButton.text")); // NOI18N
        togglePreviewButton.setEnabled(false);
        togglePreviewButton.setName("togglePreviewButton"); // NOI18N
        togglePreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePreviewButtonActionPerformed(evt);
            }
        });

        toggleCutLinesButton.setText(resourceMap.getString("toggleCutLinesButton.text")); // NOI18N
        toggleCutLinesButton.setName("toggleCutLinesButton"); // NOI18N
        toggleCutLinesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleCutLinesButtonActionPerformed(evt);
            }
        });

        jSpinner1.setName("jSpinner1"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, previewPanel, org.jdesktop.beansbinding.ELProperty.create("${zoom}"), jSpinner1, org.jdesktop.beansbinding.BeanProperty.create("value"), "ZommSpinner");
        bindingGroup.addBinding(binding);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('f');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newMenuItem.setText(resourceMap.getString("newMenuItem.text")); // NOI18N
        newMenuItem.setName("newMenuItem"); // NOI18N
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText(resourceMap.getString("saveAsMenuItem.text")); // NOI18N
        saveAsMenuItem.setName("saveAsMenuItem"); // NOI18N
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText(resourceMap.getString("editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        calibrateCameraMenuItem.setText(resourceMap.getString("calibrateCameraMenuItem.text")); // NOI18N
        calibrateCameraMenuItem.setName("calibrateCameraMenuItem"); // NOI18N
        calibrateCameraMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calibrateCameraMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(calibrateCameraMenuItem);

        executeJobMenuItem.setText(resourceMap.getString("executeJobMenuItem.text")); // NOI18N
        executeJobMenuItem.setName("executeJobMenuItem"); // NOI18N
        executeJobMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeJobMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(executeJobMenuItem);

        editMappingMenuItem.setText(resourceMap.getString("editMappingMenuItem.text")); // NOI18N
        editMappingMenuItem.setName("editMappingMenuItem"); // NOI18N
        editMappingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMappingMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editMappingMenuItem);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        materialMenuItem.setText(resourceMap.getString("materialMenuItem.text")); // NOI18N
        materialMenuItem.setName("materialMenuItem"); // NOI18N
        materialMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(materialMenuItem);

        editMenu.add(jMenu1);

        menuBar.add(editMenu);

        viewMenu.setText(resourceMap.getString("viewMenu.text")); // NOI18N
        viewMenu.setName("viewMenu"); // NOI18N

        showGridMenuItem.setText(resourceMap.getString("showGridMenuItem.text")); // NOI18N
        showGridMenuItem.setName("showGridMenuItem"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, previewPanel, org.jdesktop.beansbinding.ELProperty.create("${showGrid}"), showGridMenuItem, org.jdesktop.beansbinding.BeanProperty.create("selected"), "ShowGrid");
        bindingGroup.addBinding(binding);

        viewMenu.add(showGridMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.t_oster.visicut.gui.VisicutApp.class).getContext().getActionMap(MainView.class, this);
        jMenuItem1.setAction(actionMap.get("zoomIn")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        viewMenu.add(jMenuItem1);

        jMenuItem3.setAction(actionMap.get("zoomOut")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        viewMenu.add(jMenuItem3);

        menuBar.add(viewMenu);

        helpMenu.setAction(actionMap.get("showAboutDialog")); // NOI18N
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutDialog")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(togglePreviewButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toggleCutLinesButton)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel8)
                        .addGap(1, 1, 1)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(captureImageButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(executeJobButton)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                        .addComponent(executeJobButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(togglePreviewButton)
                                .addComponent(toggleCutLinesButton))
                            .addComponent(captureImageButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
      System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

  public void loadFile(File file)
  {
    final File fileToLoad = file;
    new Thread()
    {

      @Override
      public void run()
      {
        MainView.this.loadFileReal(fileToLoad);
      }
    }.start();
  }

  public void loadFileReal(File file)
  {
    try
    {
      this.progressBar.setIndeterminate(true);
      if (VisicutModel.PLFFilter.accept(file))
      {
        this.visicutModel1.loadFromFile(this.mappingManager1, file);
        if (this.custom == null)
        {
          custom = this.visicutModel1.getMappings();
          custom.setName("Loaded Mapping");
          this.mappingComboBox.addItem(custom);
        }
        else
        {
          custom.clear();
          custom.setName("Loaded Mapping");
          custom.addAll(this.visicutModel1.getMappings());
        }
        this.mappingComboBox.setSelectedItem(custom);
      }
      else
      {
        this.visicutModel1.loadGraphicFile(file);
      }
      this.selectedSet = this.visicutModel1.getGraphicObjects();
      if (visicutModel1.getGraphicObjects().size() > 0)
      {
        Rectangle2D bb = this.visicutModel1.getGraphicObjects().getBoundingBox();
        if (bb != null && (bb.getX() < 0 || bb.getY() < 0))
        {//Move Object to the top left corner
          AffineTransform trans = AffineTransform.getTranslateInstance(-bb.getX(), -bb.getY());
          trans.concatenate(visicutModel1.getGraphicObjects().getTransform());
          visicutModel1.getGraphicObjects().setTransform(trans);
        }
      }
      this.editRect = selectedSet.size() == 0 ? null : new EditRectangle(this.selectedSet.getBoundingBox());
      this.previewPanel.setEditRectangle(editRect);
      this.progressBar.setIndeterminate(false);
      this.refreshButtonStates();
    }
    catch (Exception e)
    {
      this.progressBar.setIndeterminate(false);
      JOptionPane.showMessageDialog(this, "Error while opening '" + file.getName() + "':\n" + e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Sets all Buttons to their correct state (disabled/enabled)
   */
  private void refreshButtonStates()
  {
    this.customMappingButton.setEnabled(
      this.visicutModel1.getMaterial() != null
      && this.visicutModel1.getGraphicObjects() != null
      && this.visicutModel1.getGraphicObjects().size() > 0
      );
  }
  
private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
  JFileChooser openFileChooser = new JFileChooser();
  openFileChooser.setAcceptAllFileFilterUsed(false);
  openFileChooser.addChoosableFileFilter(VisicutModel.PLFFilter);
  for (FileFilter f : this.visicutModel1.getGraphicFileImporter().getFileFilters())
  {
    openFileChooser.addChoosableFileFilter(f);
  }
  FileFilter allFilter = 
    new MultiFilter(
    new FileFilter[]
    {
      this.visicutModel1.getGraphicFileImporter().getFileFilter(),
      VisicutModel.PLFFilter
    }, "All supported files");
  openFileChooser.addChoosableFileFilter(allFilter);
  openFileChooser.setFileFilter(allFilter);
  int returnVal = openFileChooser.showOpenDialog(this);
  if (returnVal == JFileChooser.APPROVE_OPTION)
  {
    File file = openFileChooser.getSelectedFile();
    loadFile(file);
  }
}//GEN-LAST:event_openMenuItemActionPerformed

  private void editMappings()
  {
    List<MappingSet> mappingsets = new LinkedList<MappingSet>();
    for (MappingSet m : this.mappingManager1.getMappingSets())
    {
      mappingsets.add(m.clone());
    }
    EditMappingsDialog d = new EditMappingsDialog(this, true);
    d.setGraphicElements(this.visicutModel1.getGraphicObjects());
    d.setMappingSets(mappingsets);
    d.setMaterial(this.visicutModel1.getMaterial());
    d.setVisible(true);
    mappingsets = d.getMappingSets();
    if (mappingsets != null)
    {
      this.mappingManager1.setMappingSets(mappingsets);
      this.mappingManager1.saveAllMappings();
      this.fillComboBoxes();
      this.refreshComboBoxes();
      this.previewPanel.repaint();
    }
  }

private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
  VisicutAboutBox box = new VisicutAboutBox(this);
  box.setModal(true);
  box.setVisible(true);
}//GEN-LAST:event_aboutMenuItemActionPerformed

  private enum MouseAction
  {

    movingBackground,
    movingSet,
    resizingSet,};
  private Point lastMousePosition = null;
  private MouseAction currentAction = null;
  private Button currentButton = null;
  private GraphicSet selectedSet = null;
  private EditRectangle editRect = null;
private void previewPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPanelMousePressed
  lastMousePosition = evt.getPoint();
  currentAction = MouseAction.movingBackground;
  if (editRect != null)
  {
    Rectangle2D curRect = Helper.transform(editRect, this.previewPanel.getLastDrawnTransform());
    Button b = editRect.getButtonByPoint(lastMousePosition, this.previewPanel.getLastDrawnTransform());
    if (b != null)
    {
      currentButton = b;
      currentAction = MouseAction.resizingSet;
    }
    else
    {
      if (curRect.contains(lastMousePosition))
      {
        currentAction = MouseAction.movingSet;
      }
    }
  }
  setCursor(evt.getPoint());
}//GEN-LAST:event_previewPanelMousePressed

private void previewPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPanelMouseReleased

  if (currentAction == MouseAction.resizingSet)
  {
    //Apply changes to the EditRectangle to the selectedSet
    Rectangle2D src = selectedSet.getOriginalBoundingBox();
    selectedSet.setTransform(Helper.getTransform(src, editRect));
    this.previewPanel.repaint();
  }
  else
  {
    if (this.selectedSet != null)
    {
      if (this.currentAction != MouseAction.movingSet)
      {
        editRect = null;
        this.previewPanel.setEditRectangle(null);
        selectedSet = null;
      }
      else
      {
        Rectangle2D bb = selectedSet.getBoundingBox();
        editRect = new EditRectangle(bb);
        this.previewPanel.setEditRectangle(editRect);
      }
    }
    else
    {
      if (this.visicutModel1.getGraphicObjects() != null)
      {
        Rectangle2D bb = this.visicutModel1.getGraphicObjects().getBoundingBox();
        Rectangle2D e = Helper.transform(bb, this.previewPanel.getLastDrawnTransform());
        if (e.contains(evt.getPoint()))
        {
          selectedSet = this.visicutModel1.getGraphicObjects();
          editRect = new EditRectangle(bb);
          this.previewPanel.setEditRectangle(editRect);
        }
        else
        {
          selectedSet = null;
          editRect = null;
          this.previewPanel.setEditRectangle(null);
        }
      }
    }
  }
  lastMousePosition = evt.getPoint();
  setCursor(evt.getPoint());
}//GEN-LAST:event_previewPanelMouseReleased
private void previewPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPanelMouseDragged
  if (lastMousePosition != null)
  {
    Point diff = new Point(evt.getPoint().x - lastMousePosition.x, evt.getPoint().y - lastMousePosition.y);
    try
    {
      switch (currentAction)
      {
        case resizingSet:
        {
          this.previewPanel.getLastDrawnTransform().createInverse().deltaTransform(diff, diff);
          switch (currentButton)
          {
            case BOTTOM_RIGHT:
            {
              int offset = Math.abs(diff.x) > Math.abs(diff.y) ? diff.x : diff.y;
              editRect.height += (offset * editRect.height / editRect.width);
              editRect.width += offset;
              break;
            }
            case BOTTOM_LEFT:
            {
              int offset = Math.abs(diff.x) > Math.abs(diff.y) ? diff.x : diff.y;
              editRect.height -= (offset * editRect.height / editRect.width);
              editRect.x += offset;
              editRect.width -= offset;
              break;
            }
            case TOP_RIGHT:
            {
              int offset = Math.abs(diff.x) > Math.abs(diff.y) ? diff.x : -diff.y;
              editRect.y -= (offset * editRect.height / editRect.width);
              editRect.height += (offset * editRect.height / editRect.width);
              editRect.width += offset;
              break;
            }
            case TOP_LEFT:
            {
              int offset = Math.abs(diff.x) > Math.abs(diff.y) ? diff.x : diff.y;
              editRect.y += (offset * editRect.height / editRect.width);
              editRect.height -= (offset * editRect.height / editRect.width);
              editRect.x += offset;
              editRect.width -= offset;
              break;
            }
            case CENTER_RIGHT:
            {
              this.editRect.width += diff.x;
              break;
            }
            case TOP_CENTER:
            {
              this.editRect.y += diff.y;
              this.editRect.height -= diff.y;
              break;
            }
            case BOTTOM_CENTER:
            {
              this.editRect.height += diff.y;
              break;
            }
            case CENTER_LEFT:
            {
              this.editRect.x += diff.x;
              this.editRect.width -= diff.x;
              break;
            }
          }
          this.previewPanel.setEditRectangle(editRect);
          break;
        }
        case movingSet:
        {
          this.previewPanel.getLastDrawnTransform().createInverse().deltaTransform(diff, diff);
          if (selectedSet.getTransform() != null)
          {
            AffineTransform tr = AffineTransform.getTranslateInstance(diff.x, diff.y);
            tr.concatenate(selectedSet.getTransform());
            selectedSet.setTransform(tr);
          }
          else
          {
            selectedSet.setTransform(AffineTransform.getTranslateInstance(diff.x, diff.y));
          }
          Rectangle2D bb = selectedSet.getBoundingBox();
          editRect = new EditRectangle(bb);
          this.previewPanel.setEditRectangle(editRect);
          break;
        }
        case movingBackground:
        {
          Point center = this.previewPanel.getCenter();
          center.translate(-diff.x * 1000 / this.previewPanel.getZoom(), -diff.y * 1000 / this.previewPanel.getZoom());
          this.previewPanel.setCenter(center);
          break;
        }
      }
      this.repaint();
    }
    catch (NoninvertibleTransformException ex)
    {
      Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
    }
    lastMousePosition = evt.getPoint();
  }
}//GEN-LAST:event_previewPanelMouseDragged
  private int jobnumber = 0;

  private void executeJob()
  {
    new Thread()
    {

      @Override
      public void run()
      {
        MainView.this.progressBar.setIndeterminate(true);
        MainView.this.executeJobButton.setEnabled(false);
        MainView.this.executeJobMenuItem.setEnabled(false);
        try
        {
          jobnumber++;
          MainView.this.visicutModel1.sendJob("VisiCut " + jobnumber);
          MainView.this.progressBar.setIndeterminate(false);
          JOptionPane.showMessageDialog(MainView.this, "Job was sent as 'VisiCut " + jobnumber + "'\nPlease press START on the Lasercutter:\n" + MainView.this.visicutModel1.getSelectedLaserDevice().getName(), "Job sent", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ex)
        {
          if (ex instanceof IllegalJobException && ex.getMessage().startsWith("Illegal Focus value"))
          {
            JOptionPane.showMessageDialog(MainView.this, "You Material is too high for automatic Focussing.\nPlease focus manually and set the total height to 0.", "Error", JOptionPane.ERROR_MESSAGE);
          }
          else
          {
            JOptionPane.showMessageDialog(MainView.this, "Error: " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
        MainView.this.executeJobButton.setEnabled(true);
        MainView.this.executeJobMenuItem.setEnabled(true);
      }
    }.start();
  }

private void executeJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeJobButtonActionPerformed
  this.executeJob();
}//GEN-LAST:event_executeJobButtonActionPerformed

private void filesDropSupport1PropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_filesDropSupport1PropertyChange
{//GEN-HEADEREND:event_filesDropSupport1PropertyChange
  if (this.filesDropSupport1.getDroppedFiles() != null && this.filesDropSupport1.getDroppedFiles().size() > 0)
  {
    for (File f : this.filesDropSupport1.getDroppedFiles())
    {
      this.loadFile(f);
    }
  }
}//GEN-LAST:event_filesDropSupport1PropertyChange

  /**
   * Returns a list of MaterialProfiles
   * for all available lasercutters. Aggregated by name and Depth
   * @return 
   */
  private List<MaterialProfile> getAllMaterials()
  {
    List<MaterialProfile> result = new LinkedList<MaterialProfile>();
    for (LaserDevice ld : PreferencesManager.getInstance().getPreferences().getLaserDevices())
    {
      for (MaterialProfile mp : this.profileManager1.getMaterials(ld))
      {
        boolean found = false;
        for (MaterialProfile pp : result)
        {
          if (pp.getName().equals(mp.getName()) && pp.getDepth() == mp.getDepth())
          {
            found = true;
            break;
          }
        }
        if (!found)
        {
          result.add(mp);
        }
      }
    }
    return result;
  }

  /**
   * returns true iff the combination is supported
   * @param ld
   * @param mp
   * @param ms
   * @return 
   */
  private boolean supported(LaserDevice ld, MaterialProfile mp, MappingSet ms)
  {
    if (ld == null && mp == null)
    {
      return true;
    }
    for (MaterialProfile m : ld != null ? this.profileManager1.getMaterials(ld) : getAllMaterials())
    {
      if (mp != null && m.getName().equals(mp.getName()) && m.getDepth() == mp.getDepth())
      {
        if (ms == null)
        {
          return true;
        }
        else
        {
          boolean mappingOK = true;
          for (Mapping map : ms)
          {
            if (m.getLaserProfile(map.getProfileName()) == null)
            {
              mappingOK = false;
              break;
            }
          }
          if (mappingOK)
          {
            return true;
          }
        }
      }
      else
      {
        if (mp == null)
        {
          boolean mappingOK = true;
          if (ms != null)
          {
            for (Mapping map : ms)
            {
              if (m.getLaserProfile(map.getProfileName()) == null)
              {
                mappingOK = false;
                break;
              }
            }
          }
          if (mappingOK)
          {
            return true;
          }
        }
      }
    }
    return false;
  }

  private void save()
  {
    int returnVal = saveFileChooser.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      File file = saveFileChooser.getSelectedFile();
      if (!file.getName().endsWith("plf"))
      {
        file = new File(file.getAbsolutePath() + ".plf");
      }
      try
      {
        this.visicutModel1.saveToFile(this.profileManager1, this.mappingManager1, file);
      }
      catch (Exception ex)
      {
        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error saving File: " + ex.getLocalizedMessage());
      }
    }
    else
    {
      System.out.println("File access cancelled by user.");
    }
  }

private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
  this.save();
}//GEN-LAST:event_saveAsMenuItemActionPerformed

private void visicutModel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_visicutModel1PropertyChange
  if (evt.getPropertyName().equals(VisicutModel.PROP_LOADEDFILE))
  {
    this.saveMenuItem.setEnabled(this.visicutModel1.getLoadedFile() != null);
  }
  else
  {
    if (evt.getPropertyName().equals(VisicutModel.PROP_SELECTEDLASERDEVICE))
    {
      boolean cam = this.visicutModel1.getSelectedLaserDevice() != null && this.visicutModel1.getSelectedLaserDevice().getCameraURL() != null;
      this.calibrateCameraMenuItem.setEnabled(cam);
      this.captureImageButton.setEnabled(cam);
      if (cam)
      {
        this.captureImage();
      }
    }
  }
}//GEN-LAST:event_visicutModel1PropertyChange

private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
  if (this.visicutModel1.getLoadedFile() == null || !VisicutModel.PLFFilter.accept(this.visicutModel1.getLoadedFile()))
  {//File is not PLF or no file loaded yet
    this.saveAsMenuItemActionPerformed(evt);
    return;
  }
  try
  {
    this.visicutModel1.saveToFile(this.profileManager1, this.mappingManager1, this.visicutModel1.getLoadedFile());
  }
  catch (Exception ex)
  {
    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error saving File:\n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
}//GEN-LAST:event_saveMenuItemActionPerformed

private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
  this.editRect = null;
  this.previewPanel.setEditRectangle(null);
  this.visicutModel1.setGraphicObjects(new GraphicSet());
}//GEN-LAST:event_newMenuItemActionPerformed

private void calibrateCameraMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calibrateCameraMenuItemActionPerformed
  if (this.visicutModel1.getBackgroundImage() == null)
  {
    JOptionPane.showMessageDialog(this, "The Camera doesn't seem to be working. Please check the URL in the Lasercutter Settings");
    return;
  }
  CamCalibrationDialog ccd = new CamCalibrationDialog();
  ccd.setBackgroundImage(this.visicutModel1.getBackgroundImage());
  ccd.setImageURL(this.visicutModel1.getSelectedLaserDevice().getCameraURL());
  ccd.setLaserCutter(this.visicutModel1.getSelectedLaserDevice().getLaserCutter());
  ccd.setResultingTransformation(this.visicutModel1.getSelectedLaserDevice().getCameraCalibration());
  ccd.setVisible(true);
  this.visicutModel1.getSelectedLaserDevice().setCameraCalibration(ccd.getResultingTransformation());
  try
  {
    PreferencesManager.getInstance().savePreferences();
  }
  catch (FileNotFoundException ex)
  {
    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error while saving Settings: " + ex.getLocalizedMessage());
  }
}//GEN-LAST:event_calibrateCameraMenuItemActionPerformed

private void executeJobMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeJobMenuItemActionPerformed
  this.executeJob();
}//GEN-LAST:event_executeJobMenuItemActionPerformed
  private boolean capturing = false;

  private void captureImage()
  {
    if (!capturing)
    {
      capturing = true;
      new Thread()
      {

        @Override
        public void run()
        {
          MainView.this.captureImageButton.setEnabled(false);
          try
          {
            URL src = new URL(MainView.this.visicutModel1.getSelectedLaserDevice().getCameraURL());
            if (src != null)
            {
              BufferedImage back = ImageIO.read(src);
              if (back != null && MainView.this.visicutModel1.getBackgroundImage() == null)
              {//First Time Image is Captured => resize View
                MainView.this.previewPanel.setZoom(100);
              }
              MainView.this.visicutModel1.setBackgroundImage(back);
            }
          }
          catch (Exception ex)
          {
            JOptionPane.showMessageDialog(MainView.this, "Error loading Image:" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
          MainView.this.captureImageButton.setEnabled(true);
          MainView.this.capturing = false;
        }
      }.start();
    }
    else
    {
      System.out.println("Capturing already in progress");
    }
  }

  @Action
  public void zoomIn()
  {
    previewPanel.setZoom(previewPanel.getZoom() - (-2 * previewPanel.getZoom() / 32));
  }

  @Action
  public void zoomOut()
  {
    previewPanel.setZoom(previewPanel.getZoom() - (2 * previewPanel.getZoom() / 32));
  }

private void captureImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureImageButtonActionPerformed
  captureImage();
}//GEN-LAST:event_captureImageButtonActionPerformed

private void editMappingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMappingMenuItemActionPerformed
  this.editMappings();
}//GEN-LAST:event_editMappingMenuItemActionPerformed

private void materialComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialComboBoxActionPerformed
  //Check if Material supports all Mappings
  MaterialProfile newMaterial = this.materialComboBox.getSelectedItem() instanceof MaterialProfile ? (MaterialProfile) this.materialComboBox.getSelectedItem() : null;
  if (newMaterial != null)
  {
    if (this.materialComboBox.isDisabled(newMaterial))
    {
      this.materialComboBox.setSelectedItem(this.visicutModel1.getMaterial());
      return;
    }
  }
  this.visicutModel1.setMaterial(newMaterial);
  this.refreshComboBoxes();
  this.refreshButtonStates();
}//GEN-LAST:event_materialComboBoxActionPerformed

  private void togglePreviewMode()
  {
    if (this.togglePreviewButton.isEnabled())
    {
      this.previewPanel.setDrawPreview(true);
      this.previewPanel.setHighlightCutLines(false);
      this.togglePreviewButton.setEnabled(false);
      this.togglePreviewButton.setSelected(true);
      this.toggleCutLinesButton.setEnabled(true);
      this.toggleCutLinesButton.setSelected(false);
    }
    else
    {
      this.previewPanel.setDrawPreview(false);
      this.previewPanel.setHighlightCutLines(true);
      this.togglePreviewButton.setEnabled(true);
      this.togglePreviewButton.setSelected(false);
      this.toggleCutLinesButton.setEnabled(false);
      this.toggleCutLinesButton.setSelected(true);
    }
  }

private void togglePreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePreviewButtonActionPerformed
  togglePreviewMode();
}//GEN-LAST:event_togglePreviewButtonActionPerformed

private void toggleCutLinesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleCutLinesButtonActionPerformed
  togglePreviewMode();
}//GEN-LAST:event_toggleCutLinesButtonActionPerformed

  private void materialMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_materialMenuItemActionPerformed
  {//GEN-HEADEREND:event_materialMenuItemActionPerformed
    ImageComboBox laserDevs = new ImageComboBox();
    for (LaserDevice ld : this.visicutModel1.getPreferences().getLaserDevices())
    {
      laserDevs.addItem(ld);
      if (ld.equals(this.visicutModel1.getSelectedLaserDevice()))
      {
        laserDevs.setSelectedItem(ld);
      }
    }
    if (laserDevs.getItemCount() == 0)
    {
      JOptionPane.showMessageDialog(this, "You have to add at least one Lasercutter first.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (JOptionPane.showConfirmDialog(this, laserDevs, "Please choose a Lasercutter", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
    {
      LaserDevice ld = (LaserDevice) laserDevs.getSelectedItem();
      EditMaterialsDialog d = new EditMaterialsDialog(this, true);
      d.setMaterials(this.profileManager1.getMaterials(ld));
      d.setDefaultDirecoty(new File(ld.getMaterialsPath()));
      d.setVisible(true);
      List<MaterialProfile> result = d.getMaterials();
      if (result != null)
      {
        try
        {
          for (MaterialProfile mp : this.profileManager1.getMaterials(ld))
          {
            this.profileManager1.deleteProfile(mp, ld);
          }
          for (MaterialProfile mp : result)
          {
            this.profileManager1.saveProfile(mp, ld);
          }
          if (ld.equals(this.visicutModel1.getSelectedLaserDevice()))
          {
            this.profileManager1.loadMaterials(ld);
          }
          this.fillComboBoxes();
          this.refreshComboBoxes();
        }
        catch (FileNotFoundException ex)
        {
          JOptionPane.showMessageDialog(this, "Error saving Profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }//GEN-LAST:event_materialMenuItemActionPerformed

  /**
   * Disables all impossible combinations
   */
  private void refreshComboBoxes()
  {
    LaserDevice ld = this.visicutModel1.getSelectedLaserDevice();
    MaterialProfile mp = this.visicutModel1.getMaterial();
    MappingSet mappings = this.visicutModel1.getMappings();
    if (ld == null || mp == null || mappings == null)
    {
      this.executeJobButton.setEnabled(false);
      this.executeJobMenuItem.setEnabled(false);
    }
    else
    {
      this.executeJobButton.setEnabled(true);
      this.executeJobMenuItem.setEnabled(true);
    }
    for (int i = 1; i < this.laserCutterComboBox.getItemCount(); i++)
    {
      LaserDevice cld = (LaserDevice) this.laserCutterComboBox.getItemAt(i);
      if (supported(cld, mp, mappings))
      {
        this.laserCutterComboBox.setDisabled(cld, false);
      }
      else
      {
        this.laserCutterComboBox.setDisabled(cld, true, mappings == null ? "Material not supported" : "Mapping not supported");
      }
    }
    for (int i = 1; i < this.materialComboBox.getItemCount(); i++)
    {
      MaterialProfile m = (MaterialProfile) this.materialComboBox.getItemAt(i);
      if (supported(ld, m, mappings))
      {
        this.materialComboBox.setDisabled(m, false);
      }
      else
      {
        this.materialComboBox.setDisabled(m, true, mappings == null ? "Lasercutter not supported" : "Mapping not supported");
      }
    }
    for (int i = 1; i < this.mappingComboBox.getItemCount(); i++)
    {
      MappingSet m = (MappingSet) this.mappingComboBox.getItemAt(i);
      if (supported(ld, mp, m))
      {
        this.mappingComboBox.setDisabled(m, false);
      }
      else
      {
        this.mappingComboBox.setDisabled(m, true, mp == null ? "Lasercutter not supported" : "Material not supported");
      }
    }
  }

  private void laserCutterComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_laserCutterComboBoxActionPerformed
  {//GEN-HEADEREND:event_laserCutterComboBoxActionPerformed
    LaserDevice newDev = laserCutterComboBox.getSelectedItem() instanceof LaserDevice ? (LaserDevice) laserCutterComboBox.getSelectedItem() : null;
    if (newDev != null)
    {
      if (this.laserCutterComboBox.isDisabled(newDev))
      {
        this.laserCutterComboBox.setSelectedItem(this.visicutModel1.getSelectedLaserDevice());
        return;
      }
      this.profileManager1.loadMaterials(newDev);
      if (this.visicutModel1.getMaterial() != null)
      {
        for (MaterialProfile mp : this.profileManager1.getMaterials())
        {
          if (mp.getName().equals(this.visicutModel1.getMaterial().getName()) && mp.getDepth() == this.visicutModel1.getMaterial().getDepth())
          {
            this.visicutModel1.setMaterial(mp);
            break;
          }
        }
      }
    }
    this.visicutModel1.setSelectedLaserDevice(newDev);
    refreshComboBoxes();
  }//GEN-LAST:event_laserCutterComboBoxActionPerformed

  private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
  {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
    ManageLasercuttersDialog d = new ManageLasercuttersDialog(this, true);
    d.setLaserCutters(this.visicutModel1.getPreferences().getLaserDevices());
    d.setDefaultLaserCutter(this.visicutModel1.getPreferences().getDefaultLaserDevice());
    d.setVisible(true);
    List<LaserDevice> result = d.getLaserCutters();
    if (result != null)
    {
      this.visicutModel1.getPreferences().setLaserDevices(result);
      this.visicutModel1.getPreferences().setDefaultLaserDevice(d.getDefaultLaserCutter());
      try
      {
        PreferencesManager.getInstance().savePreferences();
      }
      catch (FileNotFoundException ex)
      {
        JOptionPane.showMessageDialog(this, "Error saving preferences: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      this.fillComboBoxes();
      this.refreshComboBoxes();
    }
  }//GEN-LAST:event_jMenuItem2ActionPerformed

private void mappingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mappingComboBoxActionPerformed
  MappingSet ms = this.mappingComboBox.getSelectedItem() instanceof MappingSet ? (MappingSet) this.mappingComboBox.getSelectedItem() : null;
  if (ms != null)
  {
    if (this.mappingComboBox.isDisabled(ms))
    {
      this.mappingComboBox.setSelectedItem(this.visicutModel1.getMappings());
      return;
    }
  }
  this.visicutModel1.setMappings(ms);
  this.refreshComboBoxes();
}//GEN-LAST:event_mappingComboBoxActionPerformed
  private MappingSet custom = null;
private void customMappingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customMappingButtonActionPerformed
  this.customMappingButton.setEnabled(false);
  if (custom == null)
  {
    custom = new MappingSet();
    custom.setName("Custom Mapping");
    this.mappingComboBox.addItem(custom);
  }
  MappingWizzard mw = new MappingWizzard(this.visicutModel1.getGraphicObjects(), this.visicutModel1.getMaterial());
  mw.showDialog(this);
  MappingSet result = mw.getMappingSet();
  if (result != null)
  {
    custom.clear();
    custom.addAll(result);
    this.mappingComboBox.setSelectedItem(custom);
    this.previewPanel.repaint();
  }
  this.customMappingButton.setEnabled(true);
}//GEN-LAST:event_customMappingButtonActionPerformed

  private void setCursor(Point p)
  {
    int cursor = Cursor.DEFAULT_CURSOR;
    cursorcheck:
    {
      if (this.visicutModel1.getGraphicObjects() != null)
      {
        if (editRect != null)
        {
          Button b = editRect.getButtonByPoint(p, this.previewPanel.getLastDrawnTransform());
          if (b != null)
          {
            switch (b)
            {
              case TOP_RIGHT:
                cursor = Cursor.NE_RESIZE_CURSOR;
                break cursorcheck;
              case CENTER_RIGHT:
                cursor = Cursor.E_RESIZE_CURSOR;
                break cursorcheck;
              case BOTTOM_RIGHT:
                cursor = Cursor.SE_RESIZE_CURSOR;
                break cursorcheck;
              case BOTTOM_CENTER:
                cursor = Cursor.S_RESIZE_CURSOR;
                break cursorcheck;
              case BOTTOM_LEFT:
                cursor = Cursor.SW_RESIZE_CURSOR;
                break cursorcheck;
              case CENTER_LEFT:
                cursor = Cursor.W_RESIZE_CURSOR;
                break cursorcheck;
              case TOP_LEFT:
                cursor = Cursor.NW_RESIZE_CURSOR;
                break cursorcheck;
              case TOP_CENTER:
                cursor = Cursor.N_RESIZE_CURSOR;
                break cursorcheck;
            }
          }
        }
        Rectangle2D bb = this.visicutModel1.getGraphicObjects().getBoundingBox();
        if (bb != null)
        {
          Rectangle2D e = Helper.transform(bb, this.previewPanel.getLastDrawnTransform());
          if (e.contains(p))
          {
            cursor = this.editRect == null ? Cursor.HAND_CURSOR : Cursor.MOVE_CURSOR;
            break cursorcheck;
          }
        }
      }
    }
    this.previewPanel.setCursor(Cursor.getPredefinedCursor(cursor));
  }

private void previewPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPanelMouseMoved
  setCursor(evt.getPoint());
}//GEN-LAST:event_previewPanelMouseMoved
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem calibrateCameraMenuItem;
    private javax.swing.JButton captureImageButton;
    private javax.swing.JButton customMappingButton;
    private javax.swing.JTextField dimensionWidthTextField;
    private javax.swing.JTextField dimesnionsHeightTextfield;
    private javax.swing.JMenuItem editMappingMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JButton executeJobButton;
    private javax.swing.JMenuItem executeJobMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private com.t_oster.visicut.gui.beans.FilesDropSupport filesDropSupport1;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private com.t_oster.visicut.gui.beans.ImageComboBox laserCutterComboBox;
    private com.t_oster.visicut.gui.beans.ImageComboBox mappingComboBox;
    private com.t_oster.visicut.managers.MappingManager mappingManager1;
    private com.t_oster.visicut.gui.beans.ImageComboBox materialComboBox;
    private javax.swing.JTextField materialHeightTextField;
    private javax.swing.JMenuItem materialMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private com.t_oster.visicut.gui.beans.PreviewPanel previewPanel;
    private com.t_oster.visicut.managers.ProfileManager profileManager1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JFileChooser saveFileChooser;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JCheckBoxMenuItem showGridMenuItem;
    private javax.swing.JToggleButton toggleCutLinesButton;
    private javax.swing.JToggleButton togglePreviewButton;
    private javax.swing.JMenu viewMenu;
    private com.t_oster.visicut.VisicutModel visicutModel1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
