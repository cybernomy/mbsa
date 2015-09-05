/**
 * FileChooserConfig.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration information for a file chooser.
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileChooserConfig.java,v 1.1 2008/05/29 13:41:01 safonov Exp $
 */
public class FileChooserConfig implements Serializable {

	/**
	 * the type of files to be displayed
	 */
	public enum FileSelectionMode {
		/**
		 * Instruction to display only files.
		 */
		FILES_ONLY,
		/**
		 * Instruction to display only directories.
		 */
		DIRECTORIES_ONLY,
		/**
		 * Instruction to display both files and directories.
		 */
		FILES_AND_DIRECTORIES
	}

	/**
	 * Configuration information for a file filter. A file filter can be used on a
	 * file chooser to keep unwanted files from appearing in the
	 * directory listing.
	 */
	public static class FileFilterConfig implements Serializable {
		private String[] allowedExtensions;
		private String description;

		/**
		 * Constructs a new file filter configuration.
		 * 
		 * @param allowedExtensions	the allowed extensions of this file filter
		 * @param description	the description of this file filter
		 */
		public FileFilterConfig(String[] allowedExtensions, String description) {
			super();
			this.allowedExtensions = allowedExtensions;
			this.description = description;
		}

		/**
		 * Returns the allowed extensions of this file filter.
		 * 
		 * @return the allowedExtensions	the allowed extensions of this file filter
		 */
		public String[] getAllowedExtensions() {
			return allowedExtensions;
		}

		/**
		 * Returns the description of this file filter.
		 * 
		 * @return the description	the description of this file filter
		 */
		public String getDescription() {
			return description;
		}

	}
	
	private boolean acceptAllFileFilterUsed;
	private char approveButtonMnemonic;
	private String approveButtonText;
	private String approveButtonToolTipText;
	private boolean controlsShown;
	private String currentDirectory;
	private String dialogTitle;
	private boolean fileHidingEnabled;
	private FileSelectionMode fileSelectionMode;
	private boolean multiSelectionEnabled;
	private String selectedFile;
	private List<FileFilterConfig> fileFilterConfigs = new ArrayList<FileFilterConfig>();
	
	/**
	 * Adds a file filter configuration to this file chooser.
	 * 
	 * @param fileFilterConfig	the file filter configuration to be added
	 */
	public void addFileFilterConfig(FileFilterConfig fileFilterConfig) {
		fileFilterConfigs.add(fileFilterConfig);
	}

	/**
	 * Returns an array containing the file filter configurations of this file chooser.
	 * 
	 * @return	an array containing the file filter configurations of this file chooser
	 */
	public FileFilterConfig[] getFileFilterConfigs() {
		return fileFilterConfigs.toArray(new FileFilterConfig[fileFilterConfigs.size()]);
	}
	
	/**
	 * Returns whether the <code>AcceptAll FileFilter</code> ("all files") is used.
	 * 
	 * @return <code>true</code> if the <code>AcceptAll FileFilter</code> ("all files") is used
	 * @see	#setAcceptAllFileFilterUsed
	 */
	public boolean isAcceptAllFileFilterUsed() {
		return acceptAllFileFilterUsed;
	}

	/**
	 * Determines if the <code>AcceptAllFileFilter</code> ("all files") is used.
	 * 
	 * @param acceptAllFileFilterUsed <code>true</code> if the <code>AcceptAllFileFilter</code> should be used, <code>false</code> otherwise.
	 * @see	#isAcceptAllFileFilterUsed
	 */
	public void setAcceptAllFileFilterUsed(boolean acceptAllFileFilterUsed) {
		this.acceptAllFileFilterUsed = acceptAllFileFilterUsed;
	}

	/**
	 * Returns the approve button mnemonic of this file chooser configuration.
	 * 
	 * @return the approve button mnemonic of this file chooser configuration
	 */
	public char getApproveButtonMnemonic() {
		return approveButtonMnemonic;
	}

	/**
	 * Sets the approve button mnemonic of this file chooser configuration.
	 * 
	 * @param approveButtonMnemonic the approve button mnemonic of this file chooser configuration
	 */
	public void setApproveButtonMnemonic(char approveButtonMnemonic) {
		this.approveButtonMnemonic = approveButtonMnemonic;
	}

	/**
	 * Returns the approve button text of this file chooser configuration.
	 * 
	 * @return the approve button text of this file chooser configuration
	 */
	public String getApproveButtonText() {
		return approveButtonText;
	}

	/**
	 * Sets the approve button text of this file chooser configuration.
	 * 
	 * @param approveButtonText the approve button text of this file chooser configuration
	 */
	public void setApproveButtonText(String approveButtonText) {
		this.approveButtonText = approveButtonText;
	}

	/**
	 * Returns the approve button tool tip text of this file chooser configuration.
	 * 
	 * @return the approve button tool tip text of this file chooser configuration
	 */
	public String getApproveButtonToolTipText() {
		return approveButtonToolTipText;
	}

	/**
	 * Sets the approve button tool tip text of this file chooser configuration.
	 * 
	 * @param approveButtonToolTipText the approve button tool tip text of this file chooser configuration
	 */
	public void setApproveButtonToolTipText(String approveButtonToolTipText) {
		this.approveButtonToolTipText = approveButtonToolTipText;
	}

	/**
	 * Returns the value of <code>controlsShown</code>
	 * 
	 * @return the value of the <code>controlsShown</code>
	 * @see #setControlButtonsAreShown(boolean)
	 */
	public boolean isControlButtonsAreShown() {
		return controlsShown;
	}

	/**
	 * Determines if control buttons are to be shown. However, this is ultimately
	 * determined by the Look and Feels.
	 * 
	 * @param controlsShown <code>false</code> if control buttons should not be shown; otherwise, <code>true</code>
	 */
	public void setControlButtonsAreShown(boolean controlsShown) {
		this.controlsShown = controlsShown;
	}

	/**
	 * Returns the current directory of this file chooser configuration.
	 * 
	 * @return the current directory of this file chooser configuration
	 */
	public String getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * Sets the current directory of this file chooser configuration.
	 * 
	 * @param currentDirectory the current directory of this file chooser configuration
	 */
	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	/**
	 * Returns the dialog title of this file chooser configuration.
	 * 
	 * @return the dialog title of this file chooser configuration
	 */
	public String getDialogTitle() {
		return dialogTitle;
	}

	/**
	 * Sets the dialog title of this file chooser configuration.
	 * 
	 * @param dialogTitle the dialog title of this file chooser configuration
	 */
	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	/**
	 * Returns true if file hiding in this file chooser is enabled, false otherwise.
	 * 
	 * @return <code>true</code> if file hiding is enabled, <code>false</code> otherwise
	 */
	public boolean isFileHidingEnabled() {
		return fileHidingEnabled;
	}

	/**
	 * Sets the file handling behavior of this file chooser.
	 * 
	 * @param fileHidingEnabled <code>true</code> if file hiding is enabled, <code>false</code> otherwise
	 */
	public void setFileHidingEnabled(boolean fileHidingEnabled) {
		this.fileHidingEnabled = fileHidingEnabled;
	}

	/**
	 * Returns the current file selection mode. The default is
	 * <code>FileSelectionMode.FILES_ONLY</code>.
	 * 
	 * @return the type of files to be displayed
	 */
	public FileSelectionMode getFileSelectionMode() {
		return fileSelectionMode;
	}

	/**
	 * Sets file selection mode which allows the user to just select files, just
	 * select directories, or select both files and directories.
	 * The default is <code>FileSelectionMode.FILES_ONLY</code>.
	 * 
	 * @param fileSelectionMode the type of files to be displayed
	 */
	public void setFileSelectionMode(FileSelectionMode fileSelectionMode) {
		this.fileSelectionMode = fileSelectionMode;
	}

	/**
	 * Returns whether multiple files can be selected.
	 * 
	 * @return <code>true</code> if multiple files can be selected
	 */
	public boolean isMultiSelectionEnabled() {
		return multiSelectionEnabled;
	}

	/**
	 * Determines if the file chooser can allow multiple file selections.
	 * 
	 * @param multiSelectionEnabled <code>true</code> if multiple files may be selected
	 */
	public void setMultiSelectionEnabled(boolean multiSelectionEnabled) {
		this.multiSelectionEnabled = multiSelectionEnabled;
	}

	/**
	 * Returns the selected file of this file chooser configuration.
	 * 
	 * @return the selected file of this file chooser configuration
	 */
	public String getSelectedFile() {
		return selectedFile;
	}

	/**
	 * Sets the selected file of this file chooser configuration.
	 * 
	 * @param selectedFile the selected file of this file chooser configuration
	 */
	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}

}
