package com.t_oster.visicut.gui.parameterpanel;

import com.t_oster.uicomponents.Parameter;
import com.t_oster.visicut.VisicutModel;
import com.t_oster.visicut.gui.MainView;
import com.t_oster.visicut.model.PlfPart;
import com.t_oster.visicut.model.graphicelements.ImportException;
import com.t_oster.visicut.model.graphicelements.psvgsupport.PSVGImporter;
import com.t_oster.uicomponents.ParameterTableModel;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Thomas Oster <thomas.oster@rwth-aachen.de>
 */
public class ParameterPanel extends javax.swing.JPanel
{

  private Map<PlfPart, Map<String, Parameter>> parameters = new LinkedHashMap<PlfPart, Map<String, Parameter>>();
  private ParameterTableModel model = new ParameterTableModel();
  private PSVGImporter pi = new PSVGImporter();

  /**
   * Creates new form ParameterPanel
   */
  public ParameterPanel()
  {
    initComponents();
    this.betterJTable1.setModel(model);
    VisicutModel.getInstance().addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent pce)
      {
        if (!ignoreChanges && VisicutModel.PROP_SELECTEDPART.equals(pce.getPropertyName()))
        {
          refresh();
        }
        else if (VisicutModel.PROP_PLF_PART_REMOVED.equals(pce.getPropertyName())|| VisicutModel.PROP_PLF_PART_UPDATED.equals(pce.getPropertyName()))
        {
          parameters.remove((PlfPart) pce.getOldValue());
          refresh();
        }
      }
    });
    this.model.addTableModelListener(new TableModelListener()
    {
      public void tableChanged(TableModelEvent tme)
      {
        if (!ignoreChanges)
        {
          applyParameters();
        }
      }
    });
  }
  private boolean ignoreChanges = false;

  private void applyParameters()
  {
    ignoreChanges = true;
    PlfPart p = VisicutModel.getInstance().getSelectedPart();
    if (p != null && PSVGImporter.FILTER.accept(p.getSourceFile()))
    {
      try
      {
        Map<String, Parameter> parms = this.getParameters(p);
        AffineTransform t = p.getGraphicObjects().getTransform();
        p.setGraphicObjects(pi.importFile(p.getSourceFile(), new LinkedList<String>(), parms));
        p.getGraphicObjects().setTransform(t);
        VisicutModel.getInstance().firePartUpdated(p);
      }
      catch (Exception e)
      {
        MainView.getInstance().getDialog().showErrorMessage(e);
      }
    }
    ignoreChanges = false;
  }

  private Map<String, Parameter> getParameters(PlfPart p)
  {
    if (!this.parameters.containsKey(p))
    {
      try
      {
        this.parameters.put(p, pi.parseParameters(p.getSourceFile(), new LinkedList<String>()));
      }
      catch (ParserConfigurationException ex)
      {
        Logger.getLogger(ParameterPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (SAXException ex)
      {
        Logger.getLogger(ParameterPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex)
      {
        Logger.getLogger(ParameterPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return this.parameters.get(p);
  }

  public void refresh()
  {
    PlfPart p = VisicutModel.getInstance().getSelectedPart();
    if (p != null && PSVGImporter.FILTER.accept(p.getSourceFile()))
    {
      Map<String, Parameter> params = this.getParameters(p);
      model.setParameterMap(params);
    }
    else
    {
      model.setParameterMap(null);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    jScrollPane1 = new javax.swing.JScrollPane();
    betterJTable1 = new com.t_oster.uicomponents.BetterJTable();

    betterJTable1.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String []
      {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(betterJTable1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 12, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private com.t_oster.uicomponents.BetterJTable betterJTable1;
  private javax.swing.JScrollPane jScrollPane1;
  // End of variables declaration//GEN-END:variables
}
