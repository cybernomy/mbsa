package com.mg.framework.support.dataset.sohlman;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


/**
 * <p>DataSet is common component to handle data in table form. It is possible to use different
 * sources like SQL database, text file etc.</p> <p><b>Usage</b></p> <ol> <li>First create {@link
 * RowInfo RowInfo} which defines ColumnStructure for DataSet<i>(optional) {@link ReadEngine
 * ReadEngine} can create RowInfo automaticly. Example {@link com.sohlman.dataset.sql.SQLReadEngine
 * SQLReadEngine} does so. </i></li> <li>Implement {@link ReadEngine ReadEngine} class
 * <i>(optional)</i></li> <ul> There is no need to create {@link ReadEngine ReadEngine} or some
 * common solution is used like {@link com.sohlman.dataset.sql.SQLReadEngine SQLReadEngine}. </ul>
 * <li>Implement {@link WriteEngine WriteEngine} class <i>(optional)</i></li> <ul> There is no need
 * to create WriteEngine if datasource is readonly(*) or some common solution is used like {@link
 * com.sohlman.dataset.sql.SQLWriteEngine SQLWriteEngine}.<br> <i>(*)Data is still updateable
 * through datamodification methods, but save() method returns -1.</i> </ul> <li>Instantiate
 * DataSet</li> <li>Assign {@link ReadEngine ReadEngine}, {@link WriteEngine WriteEngine} to DataSet
 * <i>(optional)</i></li> <li>Read data, using {@link #read read()} method<i>(optional)</i></li>
 * <li>Modify data using {@link #addRow addRow}, {@link #insertRow insertRow}, {@link
 * #removeRow(int) removeRow}, {@link #setValueAt setValueAt} and {@link #setRowAt setRowAt}
 * methods<i>(optional)</i></li> <li>Save changes, using {@link #save save} method
 * <i>(optional)</i></li> </ol>
 *
 * @author Sampsa Sohlman
 * @version 2003-03-21
 */

public class DataSet {
  public final static int NO_MORE_ROWS = -1;
  /**
   * Describes error situation.
   */
  public final static int ERROR = 0;
  /**
   * Row status new
   */
  public final static int NEW = 1;
  /**
   * Row status modified
   */
  public final static int MODIFIED = 2;
  /**
   * Row status not modified
   */
  public final static int NOTMODIFIED = 4;

  // Write engines
  /**
   * This buffer contains "visible" data.
   */
  private DataSetVector iVe_Data;

  // Read engins
  /**
   * This buffer contains data which is deleted. (has to be check if more efficient solution is
   * available)
   */
  private Vector<RowContainer> iVe_Deleted;
  /**
   * This buffer contains links to new inserted data.
   */
  private Vector<RowContainer> iVe_New;
  /**
   * This buffer contains links to modified data
   */
  private Vector<RowContainer> iVe_Modified;
  /**
   * RowInfo Object containing ColumnInformation
   */
  private RowInfo i_RowInfo;
  /**
   * Write engine which will only write changes back to source.
   */
  private WriteEngine i_WriteEngine = null;
  /**
   * Read engine, which reads data row by row.
   */
  private ReadEngine i_ReadEngine = null;
  private DataSetComparator i_DataSetComparator = null;
  /**
   * Listeners
   */
  private Vector<DataSetListener> iVe_Listeners;

  /**
   * Creates new DataSet
   */

  public DataSet(DataSet impl) {
    this.iVe_Data = new DataSetVector();
    this.iVe_Deleted = new Vector<RowContainer>();
    this.iVe_New = new Vector<RowContainer>();
    this.iVe_Modified = new Vector<RowContainer>();
    this.i_RowInfo = impl.i_RowInfo;
    this.i_WriteEngine = impl.i_WriteEngine;
    this.i_ReadEngine = impl.i_ReadEngine;
    this.i_DataSetComparator = impl.i_DataSetComparator;
    this.iVe_Listeners = impl.iVe_Listeners;

  }

  public DataSet() {

    iVe_Data = new DataSetVector();
    iVe_Deleted = new Vector<RowContainer>();
    iVe_Modified = new Vector<RowContainer>();
    iVe_New = new Vector<RowContainer>();
  }

  /**
   * Adds listener to DataSet
   *
   * @param a_DataSetListener Object implementing DataSetListener inteface
   */
  public void addListener(DataSetListener a_DataSetListener) {
    if (iVe_Listeners == null) {
      iVe_Listeners = new Vector<DataSetListener>();
    }

    iVe_Listeners.add(a_DataSetListener);
  }

  /**
   * Removes listener
   *
   * @param a_DataSetListener Listener what is wanted to remove.
   */
  public void removeListener(DataSetListener a_DataSetListener) {
    if (iVe_Listeners != null) {
      iVe_Listeners.remove(a_DataSetListener);
      if (iVe_Listeners.isEmpty()) {
        iVe_Listeners = null;
      }
    }
  }

  /**
   * Creates new empty row, to wanted position.<br>
   *
   * @param ai_index Number of row where new row is to be inserted. All the other rows are moved
   *                 down.<br> If -1 then act as addRow()
   * @return Position where row was created.<br>-1 if now row created.
   */
  public final int insertRow(int ai_before) {
    int li_return = 0;

    li_return = doInsertRow(ai_before);

    if (li_return > 0 && iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(
            new DataSetEvent(this, DataSetEvent.ROW_INSERTED, li_return, DataSetEvent.NOTVALID));
      }
    }
    return li_return;
  }

  /**
   * Creates new empty row end of DataSet.
   *
   * @return Index where row was created.<br>-1 if now row created.
   */
  public final int addRow() {
    int li_row = 0;
    li_row = insertRow(-1);

    return li_row;
  }

  /**
   * Removes row from DataSet.
   *
   * @param ai_index Row index for row to be removed.
   * @return Old index of removed row<br> -1 if remove failed.
   */
  public final int removeRow(int ai_index) {
    int li_return = 0;
    // doReset never throws exception so we don't need to catch it.

    li_return = doRemoveRow(ai_index);
    //System.out.println("DataSet.removeRow " + ai_index + " " + iVe_Data.get(ai_index).toString());

    if (li_return > 0 && iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.ROW_REMOVED, ai_index, DataSetEvent.ALL));
      }
    }
    return li_return;
  }

  /**
   * Resets all buffers.
   */
  public final void reset() {
    if (iVe_Data != null) {
      iVe_Data.removeAllElements();
    }
    if (iVe_New != null) {
      iVe_New.removeAllElements();
    }
    if (iVe_Modified != null) {
      iVe_Modified.removeAllElements();
    }

    if (iVe_Deleted != null) {
      iVe_Deleted.removeAllElements();
    }
    ;

    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.RESET));
      }
    }
  }

  /**
   * Read data with using ReadEngine
   *
   * @return Number of rows read.
   * @throws DataSetException Throws dataSet exception on error situation.
   */
  public int read() throws DataSetException {
    int li_count;

    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.READ_START));
      }
    }

    li_count = doRead();

    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.READ_END, li_count));
      }
    }
    return li_count;
  }

  /**
   * Writes data with using WriteEngine
   *
   * @return Count of rows that has been written.
   * @throws DataSetException Throws dataSet exception on error situation.
   */
  public int save() throws DataSetException {
    int li_count;

    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.SAVE_START));
      }
    }
    li_count = doSave();
    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.READ_END, li_count));
      }
    }
    return li_count;
  }

  /**
   * Modify whole row object.
   *
   * @param ai_row Row number to be modified.
   * @param a_Row  New row object that will replace current one.
   * @return Number where row is inserted or -1 if error has occurred.
   */
  private final int doModifyRow(int ai_index, Row a_Row) {
    if (ai_index > 0 && ai_index <= iVe_Data.size()) {
      RowContainer l_RowContainer = (RowContainer) iVe_Data.get(ai_index - 1);
      Row l_Row = l_RowContainer.i_Row_Current;

      if (l_RowContainer != null) {
        ModifyAction l_KeyAction = i_RowInfo.getKeyAction();
        if (l_KeyAction != null && l_RowContainer.i_Row_Orig != null && l_KeyAction.isKeyModified(l_Row, a_Row)) {
          doRemoveRow(ai_index);
          doInsertRow(ai_index, new RowContainer(null, a_Row));
          return ai_index;
        } else {
          l_RowContainer.i_Row_Current = a_Row;
          if (iVe_New.indexOf(l_RowContainer) == -1) {
            if (iVe_Modified.indexOf(l_RowContainer) == -1) {
              iVe_Modified.add(l_RowContainer);
            }
          }
          ;
          return ai_index;
        }
      }
    }
    return -1;
  }

  /**
   * This is for quicker programming if programmer wants to add whole row at once. <br><b>Note
   * Object array size has to be equal to getColumnCount()</b>
   *
   * @param a_Objects   Array of objects
   * @param ai_rowIndex Index of row
   * @throws ArrayIndexOutOfBoundsException if Object array size is different than getColumnCount()
   */
  public final void setValue(Object[] a_Objects, int ai_rowIndex) {
    if (a_Objects.length != getColumnCount()) {
      throw new ArrayIndexOutOfBoundsException("Object array size has to be equal to column count");
    }
    for (int li_column = 0; li_column < a_Objects.length; li_column++) {
      setValueAt(a_Objects[li_column], ai_rowIndex, li_column + 1);
    }
  }

  /**
   * Modify one cell in DataSet.
   *
   * @param ai_rowIndex    Index of row
   * @param ai_columnIndex Column index. 0 is first.
   * @param a_Object       Replacing object.<br> <i>Note replacing object has to be same
   *                       datatype.</i>
   * @return index of row which is replaced or<br> -1 if error
   */
  public final boolean setValueAt(Object a_Object, int ai_rowIndex, int ai_columnIndex) {
    if (doSetValueAt(a_Object, ai_rowIndex, ai_columnIndex) > 0) {
      if (iVe_Listeners != null) {
        Enumeration l_Enumeration;
        l_Enumeration = iVe_Listeners.elements();
        while (l_Enumeration.hasMoreElements()) {
          ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(
              new DataSetEvent(this, DataSetEvent.COLUMN_CHANGED, ai_rowIndex, ai_columnIndex));
        }
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get certain value from row or column.<br> {@link #setValueAt(Object, int, int)
   * setValueAt(Object a_Object, int ai_rowIndex, int ai_columnIndex)} is much faster method.
   *
   * @param ai_rowIndex   Row to read value
   * @param aS_ColumnName Name of column.
   * @return Column. This must be casted right object type.
   */
  public final boolean setValueAt(Object a_Object, int ai_rowIndex, String aS_ColumnName) {
    int li_columnIndex;
    try {
      li_columnIndex = i_RowInfo.getIndexByColumnName(aS_ColumnName);
    } catch (DataSetException a_DataSetException) {
      return false;
    }
    return setValueAt(a_Object, ai_rowIndex, li_columnIndex);
  }

  /**
   * Tells if DataSet can save changes and there is something
   *
   * @return boolean this dataset can be saved
   */
  public boolean canSave() {
    if (i_WriteEngine != null
        && ((iVe_Deleted != null && iVe_Deleted.size() > 0) || (iVe_Modified != null && iVe_Modified.size() > 0) || (iVe_New != null && iVe_New.size() > 0))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Do we have write engine
   *
   * @return boolean is dataset can be saved
   */
  public final boolean hasWriteEngine() {
    if (i_WriteEngine != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Do we have read engine
   *
   * @return boolean is dataset can be saved
   */
  public final boolean hasReadEngine() {
    if (i_ReadEngine != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get certain value from row or column.
   *
   * @param ai_rowIndex    Row to read value
   * @param ai_columnIndex Column to read value
   * @return Column. This must be casted right object type.
   * @throws ArrayIndexOutOfBoundsException if row or column is out of range
   */
  public final Object getValueAt(int ai_rowIndex, int ai_columnIndex) {
    Object l_Object = null;
    if (ai_rowIndex > 0 && ai_rowIndex <= iVe_Data.size()) { // Because of multithreading it is not sure that we will get handle
      // for the row.
      RowContainer l_RowContainer;
      try {
        l_RowContainer = (RowContainer) iVe_Data.get(ai_rowIndex - 1);
      } catch (ArrayIndexOutOfBoundsException l_ArrayIndexOutOfBoundsException) {
        throw new ArrayIndexOutOfBoundsException("Row " + ai_rowIndex + " is out of range");
      }
      Row l_Row = l_RowContainer.i_Row_Current;
      l_Object = l_Row.getValueAt(ai_columnIndex);

    }
    return l_Object;
  }

  /**
   * Get certain value from row or column.<br> {@link #getValueAt(int, int) getValueAt(int
   * ai_rowIndex, int ai_columnIndex)} is much faster method.
   *
   * @param ai_rowIndex   Row to read value
   * @param aS_ColumnName Name of column.
   * @return Column. This must be casted right object type.
   */
  public final Object getValueAt(int ai_rowIndex, String aS_ColumnName) {
    int li_columnIndex;
    try {
      li_columnIndex = i_RowInfo.getIndexByColumnName(aS_ColumnName);
    } catch (DataSetException a_DataSetException) {
      return null;
    }
    return getValueAt(ai_rowIndex, li_columnIndex);
  }

  /**
   * Set set ModifyAction object for DataSet<br> With key action is possible deside if we hare doing
   * just 'modify' or 'insert / delete' when setting item.<br> If this method is not all the objects
   * are always considered as modified.
   *
   * @param a_KeyAction Refrence to ModifyAction Object
   */
  public final void setModifyAction(ModifyAction a_ModifyAction) {
    i_RowInfo.setKeyAction(a_ModifyAction);
  }

  /**
   * Removes ReadEngine from DataSet
   */
  public final void removeReadEngine() {
    i_ReadEngine = null;
  }

  /**
   * Removes ModifyAction object from DataSet
   */
  public final void removeModifyAction() {
    setModifyAction(null);
  }

  /**
   * Removes WriteEngine from DataSet
   */
  public final void removeWriteEngine() {
    i_WriteEngine = null;
  }

  /**
   * Return allowed column class.
   *
   * @param ai_columnIndex Index of column that you want to study
   * @return String class name of object that is stored to column.
   */
  public String getColumnClassName(int ai_columnIndex) {
    return i_RowInfo.getColumnClassName(ai_columnIndex);
  }

  /**
   * Returns RowInfo object
   *
   * @return RowInfo
   */
  public RowInfo getRowInfo() {
    return i_RowInfo;
  }

  /**
   * Set's defintion of rows as types of objects. These objects must be cloneable.<br> It has to
   * contain all fields without null values.
   *
   * @param a_ColumnsInfo array of class names that object contains
   */
  public void setRowInfo(RowInfo a_ColumnsInfo) {
    i_RowInfo = a_ColumnsInfo;
  }

  /**
   * Return column count for DataSet
   *
   * @return Number of columns
   */
  public int getColumnCount() {
    if (i_RowInfo == null) {
      return 0;
    } else {
      return i_RowInfo.getColumnCount();
    }
  }

  /**
   * Returns current column name for index.<br> Requires tha column names are set.
   *
   * @param ai_index Column index
   * @return Column name
   * @throws ArrayIndexOutOfBoundsException if index is out of range or no columns exists
   */
  public String getColumnName(int ai_index) {
    if (i_RowInfo == null) {
      throw new ArrayIndexOutOfBoundsException("There is no column information");
    }
    return i_RowInfo.getColumnName(ai_index);
  }

  /**
   * @return int Row count of DataSet 0 if none
   */
  public int getRowCount() {
    return iVe_Data.size();
  }

  /**
   * @see #printBuffers(PrintStream, boolean, boolean, boolean, boolean)
   */
  public void printBuffers(PrintStream a_PrintStream) {
    printBuffers(a_PrintStream, true, true, true, true);
  }

  /**
   * <b>This is for Debugging.</b><br><br> Prints buffrers to PrintStream <b>Future will be
   * removed</b>
   *
   * @param a_PrintStream PrintStream where to write debug information
   */
  public void printBuffers(PrintStream a_PrintStream, boolean ab_data, boolean ab_new, boolean ab_modified, boolean ab_deleted) {
    StringBuffer lSb_Sep = new StringBuffer(18 * getColumnCount());
    lSb_Sep.append("*");
    for (int li_x = 0; li_x < getColumnCount(); li_x++)
      lSb_Sep.append("******************");
    String lS_Sep1 = lSb_Sep.toString();
    lSb_Sep = new StringBuffer(18 * getColumnCount());
    lSb_Sep.append("+");
    for (int li_x = 0; li_x < getColumnCount(); li_x++)
      lSb_Sep.append("-----------------+");
    String lS_Sep2 = lSb_Sep.toString();
    if (ab_data) {
      a_PrintStream.println(lS_Sep1);
      a_PrintStream.println("Databuffer");
      printColumnNames(a_PrintStream);
      a_PrintStream.println(lS_Sep2);
      printBuffer(iVe_Data, a_PrintStream);
      a_PrintStream.println(lS_Sep2);
    }
    if (ab_new) {
      a_PrintStream.println(lS_Sep1);
      if (iVe_New.size() > 0) {
        a_PrintStream.println("NewBuffer");
        printColumnNames(a_PrintStream);
        a_PrintStream.println(lS_Sep2);
        printBuffer(iVe_New, a_PrintStream);
        a_PrintStream.println(lS_Sep2);
      } else {
        a_PrintStream.println("NewBuffer (empty)");
      }
    }
    if (ab_modified) {
      a_PrintStream.println(lS_Sep1);
      if (iVe_Modified.size() > 0) {
        a_PrintStream.println("ModifiedBuffer");
        printColumnNames(a_PrintStream);
        a_PrintStream.println(lS_Sep2);
        printBuffer(iVe_Modified, a_PrintStream);
        a_PrintStream.println(lS_Sep2);
      } else {
        a_PrintStream.println("ModifiedBuffer (empty)");
      }
    }
    if (ab_deleted) {
      a_PrintStream.println(lS_Sep1);
      if (iVe_Deleted.size() > 0) {
        a_PrintStream.println("DeleteeBuffer");
        printColumnNames(a_PrintStream);
        a_PrintStream.println(lS_Sep2);
        printBuffer(iVe_Deleted, a_PrintStream);
        a_PrintStream.println(lS_Sep2);
      } else {
        a_PrintStream.println("DeletedBuffer (empty)");
      }
    }
    a_PrintStream.println(lS_Sep1);
  }

  /**
   * @param aVe_Buffer
   * @param a_PrintStream
   */
  private void printBuffer(Vector aVe_Buffer, PrintStream a_PrintStream) {
    int li_countRows, li_countColumns;
    Row l_Row;
    li_countRows = aVe_Buffer.size();
    li_countColumns = getColumnCount();
    for (int li_c1 = 0; li_c1 < li_countRows; li_c1++) {
      StringBuffer l_StringBuffer = createSpaceFilledStringBuffer(li_countColumns * 18);
      l_Row = ((RowContainer) aVe_Buffer.get(li_c1)).i_Row_Current;
      DataSetService.setStringToStringBuffer(l_StringBuffer, "|", 0);
      for (int li_c2 = 0; li_c2 < li_countColumns; li_c2++) {
        String l_String = "" + l_Row.getValueAt(li_c2 + 1);
        if (l_String.length() > 17) {
          l_String = l_String.substring(0, 17);
        }
        DataSetService.setStringToStringBuffer(l_StringBuffer, l_String, li_c2 * 18 + 1);
        DataSetService.setStringToStringBuffer(l_StringBuffer, "|", li_c2 * 18);
      }

      DataSetService.setStringToStringBuffer(l_StringBuffer, " ", li_countColumns * 18 - 1);
      a_PrintStream.print(l_StringBuffer.toString());
      a_PrintStream.println();
    }
  }

  /**
   * @param a_PrintStream
   */
  private void printColumnNames(PrintStream a_PrintStream) {
    int li_countColumns;
    li_countColumns = getColumnCount();
    StringBuffer l_StringBuffer = createSpaceFilledStringBuffer(li_countColumns * 18);
    for (int li_c2 = 0; li_c2 < li_countColumns; li_c2++) {
      DataSetService.setStringToStringBuffer(l_StringBuffer, " " + i_RowInfo.getColumnName(li_c2 + 1) + " ", li_c2 * 18);
    }
    a_PrintStream.print(l_StringBuffer.toString());
    a_PrintStream.println();
  }

  /**
   * Create String buffer which is filled with space " "
   *
   * @param ai_size Size of new StringBuffer
   * @return StringBuffer Space filled Stringbuffer
   */
  private StringBuffer createSpaceFilledStringBuffer(int ai_size) {
    StringBuffer l_StringBuffer = new StringBuffer(ai_size);
    for (int li_c = 0; li_c < ai_size; li_c++) {
      l_StringBuffer.append(" ");
    }
    return l_StringBuffer;
  }

  /**
   * Returns handle for ReadEngine.
   *
   * @return Reference to current RetrieveEngine.<br> null if it is not defined.
   */
  public final ReadEngine getReadEngine() {
    return i_ReadEngine;
  }

  /**
   * Set setReadEngine for DataSet
   *
   * @param a_ReadEngine Assigned read engine.
   */
  public final void setReadEngine(ReadEngine a_ReadEngine) {
    // future when many types of
    // write engines exists. Do check and set all other nulls.
    i_ReadEngine = a_ReadEngine;
  }

  /**
   * Returns handle for current WriteEngine.
   *
   * @return Reference to current UpdateEngine.<br> null if it is not defined.
   */
  public final WriteEngine getWriteEngine() {
    return i_WriteEngine;
  }

  /**
   * Set WriteEngine for DataSet
   *
   * @param a_WriteEngine Refrence to new WriteEngine.
   */
  public final void setWriteEngine(WriteEngine a_WriteEngine) {
    i_WriteEngine = a_WriteEngine;
  }

  private final int doSave() throws DataSetException {
    if (i_WriteEngine != null) {
      int li_return = 0;
      try {
        i_WriteEngine.writeStart();
                /*

																				*/
        i_WriteEngine.write(this);
        iVe_New.removeAllElements();
        iVe_Modified.removeAllElements();
        iVe_Deleted.removeAllElements();
      } finally {
        li_return = i_WriteEngine.writeEnd();
      }
      return li_return;
    } else {
      throw new DataSetException("save - no WriteEngine defined");
    }
  }

  private final int doRead() throws DataSetException {
    if (i_ReadEngine != null) {
      reset();
      int li_count = 0;
      int li_return = 0;
      try {
        i_RowInfo = i_ReadEngine.readStart(i_RowInfo);
        Row l_Row;
        while ((l_Row = i_ReadEngine.readRow(i_RowInfo)) != Row.NO_MORE_ROWS) {
          iVe_Data.add(new RowContainer(l_Row, l_Row));
          li_count++;
        }
      } finally {
        li_return = i_ReadEngine.readEnd();
      }
      return li_return;
    } else {
      throw new DataSetException("read - No ReadEngines defined");
    }
  }

  private final int doRemoveRow(int ai_index) {
    if (ai_index > 0 && ai_index <= iVe_Data.size()) {
      RowContainer l_RowContainer = (RowContainer) iVe_Data.get(ai_index - 1);
      iVe_Modified.remove(l_RowContainer);
      int li_index = iVe_New.indexOf(l_RowContainer);
      if (li_index >= 0) {
        iVe_New.remove(li_index);
      } else {
        iVe_Deleted.add(l_RowContainer);
      }

      iVe_Data.remove(ai_index - 1);
      return ai_index;
    } else {
      return -1;
    }
  }

  private final int doInsertRow(int ai_before) {
    Object[] l_Objects = new Object[getColumnCount()];
    Row l_Row = new Row(l_Objects, i_RowInfo);
    l_Row.setAllNulls();
    return doInsertRow(ai_before, new RowContainer(null, l_Row));
  }

  private final int doInsertRow(int ai_before, RowContainer a_RowContainer) {
    if (ai_before == -1) {
      iVe_Data.add(a_RowContainer);
      iVe_New.add(a_RowContainer);
      return iVe_Data.size();
    }

    if (ai_before > 0) {
      iVe_Data.add(ai_before - 1, a_RowContainer);
      iVe_New.add(a_RowContainer);
      return ai_before;
    } else {
      return -2;
    }
  }

  private final int doSetValueAt(Object a_Object, int ai_rowIndex, int ai_columnIndex) {
    if (ai_rowIndex > 0 && ai_rowIndex <= iVe_Data.size()) {
      RowContainer l_RowContainer = ((RowContainer) iVe_Data.get(ai_rowIndex - 1));

      // Has to be re-thinked

      Row l_Row;
      if (l_RowContainer.i_Row_Current == l_RowContainer.i_Row_Orig) {
        l_Row = (Row) l_RowContainer.i_Row_Current.clone();
      } else {
        l_Row = (Row) l_RowContainer.i_Row_Current;
      }

      if (l_Row.setValueAt(ai_columnIndex, a_Object) > 0) {
        return doModifyRow(ai_rowIndex, l_Row);
      } else {
        return -2;
      }
    } else {
      return -1;
    }
  }

  /**
   * Seach matching row from dataset.
   *
   * @param a_Row Row which contains data that wanted to be found.
   * @return row number where row has been found 0 if not
   */
  public final int search(Row a_Row) {
    return search(a_Row, null);
  }

  /**
   * Seach matching row from dataset.
   *
   * @param a_Row      Row which contains data that wanted to be found.
   * @param ai_columns array of column numbers that are used to find row.
   * @return row number where row has been found 0 if not found
   */
  public final int search(Row a_Row, int[] ai_columns) {
    RowInfo l_RowInfo = a_Row.getRowInfo();

    if (!l_RowInfo.equals(i_RowInfo)) {
      throw new IllegalArgumentException("Rows has to be same types");
    }

    if (ai_columns != null) {
      for (int li_index = 0; li_index < ai_columns.length; li_index++) {
        if (a_Row.getColumnCount() > ai_columns[li_index]) {
          throw new IllegalArgumentException("Column " + ai_columns[li_index] + " is out of range.");
        }
      }

      for (int li_index = 1; li_index <= getRowCount(); li_index++) {
        if (a_Row.equals(getRow(li_index), ai_columns)) {
          return li_index;
        }
      }
    } else {
      for (int li_index = 1; li_index <= getRowCount(); li_index++) {
        if (a_Row.equals(getRow(li_index))) {
          return li_index;
        }
      }
    }
    return 0;
  }

  /**
   * Seach matching row from dataset.
   *
   * @param a_Object  Object which contains data that wanted to be found.
   * @param ai_column column that are searched.
   * @return row number where row has been found 0 if not
   */
  public final int search(Object a_Object, int ai_column) {
    for (int li_index = 1; li_index <= getRowCount(); li_index++) {
      Object l_Object = getValueAt(li_index, ai_column);
      if (a_Object == null && l_Object == null) {
        return li_index;
      } else if (a_Object != null && l_Object != null && a_Object.equals(l_Object)) {
        return li_index;
      }
    }
    return 0;
  }

  /**
   * Returns Row from ai_index location. Don't return reference to row.
   *
   * @param ai_index Index of row
   * @return Copy of the row object in DataSet
   */
  public final Row getRow(int ai_index) {
    Row l_Row = getReferenceToRow(ai_index);
    if (l_Row != null) {
      return (Row) l_Row.clone();
    } else {
      return null;
    }
  }

  /**
   * Returns Row from ai_index location. Don't return reference to row.
   *
   * @param ai_index Index of row
   * @return Copy of the row object in DataSet
   */
  final Row getReferenceToRow(int ai_index) {
    if (ai_index > 0 && ai_index <= getRowCount()) {
      RowContainer l_RowContainer = (RowContainer) iVe_Data.get(ai_index - 1);
      return (Row) l_RowContainer.i_Row_Current;
    } else {
      return null;
    }
  }

  /**
   * Set Row object to DataSet.
   *
   * @param ai_index Row where to modify row.
   * @param a_Row    Row object which is modifying
   * @return Row number where change has been done.
   */
  public final int setRowAt(int ai_index, Row a_Row) {

    int li_row = doModifyRow(ai_index, a_Row);
    if (iVe_Listeners != null) {
      Enumeration l_Enumeration;
      l_Enumeration = iVe_Listeners.elements();
      while (l_Enumeration.hasMoreElements()) {
        ((DataSetListener) l_Enumeration.nextElement()).dataSetChanged(new DataSetEvent(this, DataSetEvent.COLUMN_CHANGED, li_row, DataSetEvent.ALL));
      }
    }
    return li_row;
  }

  /**
   * Current status of row<br> With key action is possible deside if we hare doing just 'modify' or
   * 'insert / delete' when setting item.<br> If this method is not all the objects are always
   * considered as modified.
   *
   * @param ai_index Index of row which status is returned.
   * @return current status ERROR, NEW, MODIFIED, NOTMODIFIED
   */
  public int getRowStatus(int ai_index) {
    if (ai_index > 0 && iVe_Data.size() <= ai_index) {
      ai_index--;
      RowContainer l_RowContainer = (RowContainer) iVe_Data.get(ai_index);
      if (l_RowContainer.i_Row_Orig == null) {
        return NEW;
      } else if (l_RowContainer.i_Row_Orig != l_RowContainer.i_Row_Current) {
        return MODIFIED;
      } else {
        return NOTMODIFIED;
      }
    }
    return ERROR;
  }

  public void sort(int ai_columnIndex) {
    int[] li_indexes = {ai_columnIndex};
    sort(li_indexes);
  }

  public void sort(int[] ai_columnIndexes) {
    setComparator(new RowComparator(ai_columnIndexes));
    sort();
  }

  /**
   * Sorts data using current comparator.
   */
  public void sort() {
    Arrays.sort(iVe_Data.getObjects(), 0, iVe_Data.size(), i_DataSetComparator);
  }

  /**
   * Get current Comparator object
   *
   * @return current Comparator object
   */
  public Comparator getComparator() {
    if (i_DataSetComparator == null) {
      return null;
    }

    return i_DataSetComparator.getComparator();
  }

  /**
   * Set comparator for sorting.
   *
   * @param a_Comparator Comparator object
   */
  public void setComparator(Comparator<Row> a_Comparator) {
    i_DataSetComparator = new DataSetComparator(a_Comparator);
  }

  /**
   * This method is made for if example data need to be handled like new. <br>In case of MODIFIED
   * new rows are not counted, because there are no original value for them.
   *
   * @param ai_targetBuffer NEW, MODIFIED, OR NOTMODIFIED
   */
  public void setBufferAll(int ai_targetBuffer) {
    if (ai_targetBuffer == NEW) {
      iVe_Modified.removeAllElements();
      iVe_Deleted.removeAllElements();
      iVe_New.removeAllElements();
      for (int li_y = 0; li_y < iVe_Data.size(); li_y++) {
        RowContainer l_RowContainer = (RowContainer) iVe_Data.get(li_y);
        l_RowContainer.i_Row_Orig = null;
        iVe_New.add(l_RowContainer);
      }
    } else if (ai_targetBuffer == MODIFIED) {
      iVe_Modified.removeAllElements();
      iVe_Deleted.removeAllElements();
      iVe_New.removeAllElements();
      for (int li_y = 0; li_y < iVe_Data.size(); li_y++) {
        RowContainer l_RowContainer = (RowContainer) iVe_Data.get(li_y);
        if (l_RowContainer.i_Row_Orig != null) {
          iVe_Modified.add(l_RowContainer);
        }

      }
    } else if (ai_targetBuffer == NOTMODIFIED) {
      for (int li_y = 0; li_y < iVe_Modified.size(); li_y++) {
        RowContainer l_RowContainer = (RowContainer) iVe_Modified.get(li_y);
        l_RowContainer.i_Row_Orig = l_RowContainer.i_Row_Current;
      }
      iVe_Modified.removeAllElements();
      iVe_Deleted.removeAllElements();
      iVe_New.removeAllElements();
    } else {
      throw new IllegalArgumentException("Argument has to be DataSet.NEW, DataSet.MODIFIED, DataSet.NOTMODIFIED ");
    }
  }

  /**
   * Method synchronizeFrom.
   *
   * @param a_DataSet_Source Source DataSet
   * @param ai_key           Key column which is used in comparation
   * @return int[] Index 0 addcount 1 modify 2 remove count
   */
  public int[] synchronizeFrom(DataSet a_DataSet_Source, int ai_key) throws DataSetException {
    int[] li_keys = {ai_key};
    return synchronizeFrom(a_DataSet_Source, li_keys, li_keys, null, null, true, true, true);
  }

  /**
   * Method synchronizeFrom.
   *
   * @param a_DataSet_Source Source DataSet
   * @param ai_keys          Array of indexes of key columns, which are used to comparing rows.
   * @return int[] Index 0 addcount 1 modify 2 remove count
   */
  public int[] synchronizeFrom(DataSet a_DataSet_Source, int[] ai_keys) throws DataSetException {
    return synchronizeFrom(a_DataSet_Source, ai_keys, ai_keys, null, null, true, true, true);
  }

  /**
   * Method synchronizeFrom.
   *
   * @param a_DataSet_Source   Source DataSet
   * @param ai_sourceKeys      Array of indexes of key columns, which are used to comparing rows.
   * @param ai_destinationKeys Array of indexes of key columns, which are used to comparing rows.
   * @param ab_doAdd           if add to operation should be is made to destination
   * @param ab_doUpdate        if update to operation should be is made to destination
   * @param ab_doDelete        if delete to operation should be is made to destination
   * @return int[] Index 0 addcount 1 modify 2 remove count
   */
  public int[] synchronizeFrom(
      DataSet a_DataSet_Source,
      int[] ai_sourceKeys,
      int[] ai_destinationKeys,
      boolean ab_doAdd,
      boolean ab_doUpdate,
      boolean ab_doDelete)
      throws DataSetException {
    return synchronizeFrom(a_DataSet_Source, ai_sourceKeys, ai_destinationKeys, null, null, ab_doAdd, ab_doUpdate, ab_doDelete);
  }

  /**
   * Method syncronizeFrom<br> With this method you can syncronize DataSets. <br><br> Result of
   * syncronization is that this DataSet buffers are changed as operation would have been done
   *
   * @param a_DataSet_Source      Source DataSet
   * @param ai_sourceKeys         Array of indexes of key columns, which are used to comparing
   *                              rows.
   * @param ai_destinationKeys    Array of indexes of key columns, which are used to comparing
   *                              rows.
   * @param ai_sourceColumns      Array of indexes of data columns, where will be copied if
   *                              destination is different.
   * @param ai_destinationColumns Array of indexes of data columns, which are will be updated if
   *                              source is different.
   * @param ab_doAdd              if add to operation should be is made to destination
   * @param ab_doUpdate           if update to operation should be is made to destination
   * @param ab_doDelete           if delete to operation should be is made to destination
   * @return int[] Index 0 addcount 1 modify 2 remove count
   * @throws DataSetException on error
   */
  public int[] synchronizeFrom(
      DataSet a_DataSet_Source,
      int[] ai_sourceKeys,
      int[] ai_destinationKeys,
      int[] ai_sourceColumns,
      int[] ai_destinationColumns,
      boolean ab_doAdd,
      boolean ab_doUpdate,
      boolean ab_doDelete)
      throws DataSetException {
    // If Source columns are not defined
    // then create them	by choosing all

    if (ai_sourceColumns == null) {
      int li_count = 0;
      if (a_DataSet_Source.getColumnCount() > getColumnCount()) {
        li_count = getColumnCount();
      } else {
        li_count = a_DataSet_Source.getColumnCount();
      }
      ai_sourceColumns = new int[li_count];

      for (int li_index = 1; li_index <= li_count; li_index++) {
        ai_sourceColumns[li_index - 1] = li_index;
      }
    }

    // If Destination columns are not defined
    // then create them	by choosing all

    if (ai_destinationColumns == null) {
      int li_count = 0;
      if (a_DataSet_Source.getColumnCount() > getColumnCount()) {
        li_count = getColumnCount();
      } else {
        li_count = a_DataSet_Source.getColumnCount();
      }
      ai_destinationColumns = new int[li_count];

      for (int li_index = 1; li_index <= li_count; li_index++) {
        ai_destinationColumns[li_index - 1] = li_index;
      }
    }

    //
    // Start to check some programming mistakes
    //

    if (ai_sourceKeys.length != ai_destinationKeys.length) {
      throw new IllegalArgumentException("In synchronization source and destination key column count has to be match");
    }
    if (ai_sourceColumns.length != ai_destinationColumns.length) {
      throw new IllegalArgumentException("In synchronization source and destination column count has to be match");
    }

    // Check that columns are match

    RowInfo l_RowInfo_Source = a_DataSet_Source.getRowInfo();
    RowInfo l_RowInfo_Destination = getRowInfo();

    for (int li_index = 0; li_index < ai_sourceColumns.length; li_index++) {
      ColumnInfo l_ColumnInfo_Source = l_RowInfo_Source.getColumnInfo(ai_sourceColumns[li_index]);
      ColumnInfo l_ColumnInfo_Destination = l_RowInfo_Destination.getColumnInfo(ai_destinationColumns[li_index]);

      if (!l_ColumnInfo_Source.equals(l_ColumnInfo_Destination)) {
        throw new DataSetException("Column classes that should be synchronized are not match.");
      }
    }

    for (int li_index = 0; li_index < ai_sourceKeys.length; li_index++) {
      ColumnInfo l_ColumnInfo_Source = l_RowInfo_Source.getColumnInfo(ai_sourceKeys[li_index]);
      ColumnInfo l_ColumnInfo_Destination = l_RowInfo_Destination.getColumnInfo(ai_destinationKeys[li_index]);

      if (!l_ColumnInfo_Source.equals(l_ColumnInfo_Destination)) {
        throw new DataSetException("Keys column classes of synchronization are not match");
      }
    }

    int li_sourceCount = a_DataSet_Source.getRowCount();
    int li_destinationCount = getRowCount();

    int li_addCount = 0;
    int li_modifyCount = 0;
    int li_removeCount = 0;

    if (li_sourceCount > 0 && li_destinationCount > 0) {
      // DataSet's need to be correct order because of copying

      a_DataSet_Source.setComparator(new RowComparator(ai_sourceKeys));
      a_DataSet_Source.sort();

      setComparator(new RowComparator(ai_destinationKeys));
      sort();

      int li_sIndex = 1;
      int li_dIndex = 1;
      do {
        Row l_Row_Destination = getReferenceToRow(li_dIndex);
        Row l_Row_Source = a_DataSet_Source.getReferenceToRow(li_sIndex);
//				String lS_S = String.valueOf(l_Row_Source.getValueAt(1));
//				String lS_D = String.valueOf(l_Row_Destination.getValueAt(1));

        int li_result = compareRows(l_Row_Source, l_Row_Destination, ai_sourceKeys, ai_destinationKeys);

        if (li_result > 0) {
          // Destination bigger so let's remove
          if (ab_doDelete) {
            do {
              removeRow(li_dIndex);
              li_destinationCount--;
              li_removeCount++;
//							lS_S = String.valueOf(l_Row_Source.getValueAt(1));
//							lS_D = String.valueOf(getReferenceToRow(li_dIndex).getValueAt(1));

            }
            while (li_dIndex <= li_destinationCount
                && compareRows(l_Row_Source, getReferenceToRow(li_dIndex), ai_sourceKeys, ai_destinationKeys) > 0);
          } else {
            li_dIndex++;
          }
        } else if (li_result < 0) {
          // Destination smaller so let's add
          if (ab_doAdd) {
            int li_row = addRow();
            copyRow(a_DataSet_Source, this, li_sIndex, li_row, ai_sourceColumns, ai_destinationColumns);
            li_addCount++;
          }
          li_sIndex++;
        } else {
          // Equal .. Copy

          do {
            if (ab_doUpdate) {
              if (copyRow(a_DataSet_Source, this, li_sIndex, li_dIndex, ai_sourceColumns, ai_destinationColumns)) {
                li_modifyCount++;
              }
            }
            li_dIndex++;
          }
          while (li_dIndex <= li_destinationCount
              && compareRows(l_Row_Source, getReferenceToRow(li_dIndex), ai_sourceKeys, ai_destinationKeys) == 0
              && (li_sIndex == li_sourceCount
              || compareRows(l_Row_Source, a_DataSet_Source.getReferenceToRow(li_sIndex + 1), ai_sourceKeys, ai_sourceKeys) != 0));
          li_sIndex++;
        }

      }
      while (li_dIndex <= li_destinationCount && li_sIndex <= li_sourceCount);

      // Some where left to added

      while (li_sIndex <= li_sourceCount) {
        // Destination smaller so let's add
        if (ab_doAdd) {
          int li_row = addRow();
          copyRow(a_DataSet_Source, this, li_sIndex, li_row, ai_sourceColumns, ai_destinationColumns);
          li_addCount++;
        }
        li_sIndex++;
      }
      if (ab_doDelete) {
        while (li_dIndex <= li_destinationCount) {
          removeRow(li_dIndex);
          li_destinationCount--;
          li_removeCount++;
        }
      }
    } else if (li_sourceCount == 0 && li_destinationCount > 0) {
      // Delete all from Destination
      // from bottom to top for saving time
      for (int li_index = getRowCount(); li_index >= 1; li_index--) {
        removeRow(li_index);
        li_removeCount++;
      }
    } else if (li_sourceCount > 0 && li_destinationCount == 0) {
      // Add all to Destination

      for (int li_index = 1; li_index <= li_sourceCount; li_index++) {
        int li_row = addRow();
        copyRow(a_DataSet_Source, this, li_index, li_row, ai_sourceColumns, ai_destinationColumns);
        li_addCount++;
      }
    }

    int[] li_returnValue = {li_addCount, li_modifyCount, li_removeCount};
    return li_returnValue;
  }

  private boolean copyRow(
      DataSet a_DataSet_Source,
      DataSet a_DataSet_Destination,
      int ai_sourceIndex,
      int ai_destinationIndex,
      int[] ai_colSource,
      int[] ai_colDestination) {
    int li_copyCount = 0;

    // Has to be optimized

    Row l_Row_Source = a_DataSet_Source.getReferenceToRow(ai_sourceIndex);
    Row l_Row_Destination = a_DataSet_Destination.getReferenceToRow(ai_destinationIndex);

    for (int li_x = 0; li_x < ai_colSource.length; li_x++) {
      int li_copy = 0;
      Object lO_Source = l_Row_Source.getValueAt(ai_colSource[li_x]);
      Object lO_Destination = l_Row_Destination.getValueAt(ai_colDestination[li_x]);

      if ((lO_Source != null && lO_Destination == null) || (lO_Source == null && lO_Destination != null)) {
        // We using DataSet to store value
        a_DataSet_Destination.setValueAt(lO_Source, ai_destinationIndex, ai_colDestination[li_x]);
        li_copy = 1;
      } else if (lO_Source == null && lO_Destination == null) {

      } else if (!lO_Source.equals(lO_Destination)) {
        a_DataSet_Destination.setValueAt(lO_Source, ai_destinationIndex, ai_colDestination[li_x]);
        li_copy = 1;
      }
      li_copyCount = li_copyCount + li_copy;
    }

    if (li_copyCount > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method getRowComparator retunrs current row Compator
   *
   * @return RowComparator
   */
  public Comparator getRowComparator() {
    return i_DataSetComparator.getComparator();
  }

  private int compareRows(Row a_Row_Source, Row a_Row_Destination, int[] ai_sourceKeys, int[] ai_destinationKeys) {
    for (int li_index = 0; li_index < ai_sourceKeys.length; li_index++) {
      int li_value =
          DataSetService.compareComparables(
              (Comparable) a_Row_Source.getValueAt(ai_sourceKeys[li_index]),
              (Comparable) a_Row_Destination.getValueAt(ai_destinationKeys[li_index]));
      if (li_value > 0) {
        return li_value;
      } else if (li_value < 0) {
        return li_value;
      }
    }
    return 0;
  }

  /**
   * Method getDeleted. This point only for WriteEngine
   *
   * @return List
   */
  public List getDeleted() {
    return iVe_Deleted;
  }

  /**
   * Method getModified. This point only for WriteEngine
   *
   * @return List
   */
  public List getModified() {
    return iVe_Modified;
  }

  /**
   * Method getInserted. This point only for WriteEngine
   *
   * @return List
   */
  public List getInserted() {
    return iVe_New;
  }

  /**
   * Method getAllRows. This point only for WriteEngine
   *
   * @return List
   */
  public List getAllRows() {
    return iVe_Data;
  }

  /**
   * Method getValuesAt returns range of values from certain column
   *
   * @param ai_startRow    Start row
   * @param ai_endRow      End row
   * @param ai_columnIndex Column
   * @return Object[]
   */
  public Object[] getValuesAt(int ai_startRow, int ai_endRow, int ai_columnIndex) {
    if (ai_startRow > ai_endRow) {
      throw new IllegalArgumentException("Start row value has be smaller than end row value");
    }
    if (ai_startRow <= 0) {
      throw new IllegalArgumentException("Start row has to be from 1 to rowCount");
    }
    if (ai_endRow > getRowCount()) {
      throw new IllegalArgumentException("End row cannot be larger than rowcount");
    }

    Object[] l_Objects = new Object[ai_endRow - ai_startRow + 1];

    int li_counter = 0;
    for (int li_index = ai_startRow; li_index <= ai_endRow; li_index++) {
      l_Objects[li_counter] = getValueAt(li_index, ai_columnIndex);
      li_counter++;
    }

    return l_Objects;
  }

  public void groupBy(int[] ai_groupColums) {
    GroupCalc[] l_GroupCalcs = null;
    groupBy(ai_groupColums, l_GroupCalcs);
  }

  /**
   * Method groupBy
   */
  public void groupBy(int[] ai_groupColumns, GroupCalc a_GroupCalc) {
    GroupCalc[] l_GroupCalcs = new GroupCalc[1];
    l_GroupCalcs[0] = a_GroupCalc;
    groupBy(ai_groupColumns, l_GroupCalcs);
  }

  /**
   * Method groupBy
   */
  public void groupBy(int[] ai_groupColumns, GroupCalc[] a_GroupCalcs) {
    setComparator(new RowComparator(ai_groupColumns));
    sort();

    // Search

    for (int li_row = getRowCount(); li_row > 0; li_row--) {
      // First find sequence of mached rows
      Row l_Row_Current = getReferenceToRow(li_row);
      int li_rowEnd = li_row;
      int li_rowStart = li_row;
      Row l_Row_Before;
      boolean lb_continue = true;

      if (li_row > 1) {
        do {
          li_row--;
          l_Row_Before = getReferenceToRow(li_row);

          if (l_Row_Current.equals(l_Row_Before, ai_groupColumns)) {
            li_rowStart = li_row;
          } else {
            lb_continue = false;
          }
        }
        while (li_row > 1 && lb_continue);
        li_row++; // move cursor
      }
      // Then  calculate group
      if (li_rowStart <= li_rowEnd) {
        if (a_GroupCalcs != null) {
          // Calculate grouping
          for (int li_index = 0; li_index < a_GroupCalcs.length; li_index++) {
            int li_column = a_GroupCalcs[li_index].getColumnIndex();
            Object[] l_Objects = getValuesAt(li_rowStart, li_rowEnd, li_column);
            setValueAt(a_GroupCalcs[li_index].calculateGroupBy(l_Objects), li_rowStart/*li_row*/, li_column);
          }
        }
        // Remove rows that are used in grouping
        for (int li_index = li_rowEnd; li_index > li_rowStart; li_index--) {
          removeRow(li_index);
        }
      }
    }
  }

  /**
   * This method if DataSet is modified. It is modified, if some of fields are modified or rows are
   * added or deleted.
   *
   * @return true if modified or false if not
   */
  public boolean isModified() {
    if (iVe_Modified != null && iVe_Modified.size() > 0) {
      return true;
    } else if (iVe_New != null && iVe_New.size() > 0) {
      return true;
    } else if (iVe_Deleted != null && iVe_Deleted.size() > 0) {
      return true;
    } else {
      return false;
    }
  }
}