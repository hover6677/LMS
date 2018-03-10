// ActiveXDemoDlg.h : header file
//

#if !defined(AFX_ACTIVEXDEMODLG_H__6698882A_D34A_11D4_9079_002018534C30__INCLUDED_)
#define AFX_ACTIVEXDEMODLG_H__6698882A_D34A_11D4_9079_002018534C30__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "lppx2.h"
#include "Lppx2Connection.h"
#include "Util.h"

/////////////////////////////////////////////////////////////////////////////
// CActiveXDemoDlg dialog

class CActiveXDemoDlg : public CDialog
{
// Construction
public:
	CActiveXDemoDlg(CWnd* pParent = NULL);	// standard constructor
	
	IApplication m_LabelingSoftware;
	
	
// Dialog Data
	//{{AFX_DATA(CActiveXDemoDlg)
	enum { IDD = IDD_ACTIVEXDEMO_DIALOG };
	CEdit	m_qty;
	CListBox	m_VariableList;
	CString	m_LabName;
	BOOL	m_bVisible;
	CString	m_VarValue;
	BOOL	m_EnableEvents;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CActiveXDemoDlg)
	public:
	virtual BOOL DestroyWindow();
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;
	CLppx2Connection m_Lppx2Connection;
	
	
	void UpdatePreview(); 
	void FillVariableList();
	void Draw();

	// Generated message map functions
	//{{AFX_MSG(CActiveXDemoDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnBnOpendoc();
	afx_msg void OnCkVisible();
	afx_msg void OnBnDisplayform();
	afx_msg void OnButton1();
	afx_msg void Onvscrollup();
	afx_msg void Onvscrolldown();
	afx_msg void Onhscrollright();
	afx_msg void Onhscrollleft();
	afx_msg void OnBnSavedoc();
	afx_msg void Onprint();
	afx_msg void OnSelchangeLtVarlist();
	afx_msg void OnBnUpdatevarvalue();
	afx_msg void OnBnClosedoc();
	afx_msg void OnCkEnableevents();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

private:
	LONG		m_WidthMetaFile;
	LONG		m_HeightMetaFile;
	CPalette	m_palDIB ;
	HDIB		m_hDIB ;
	long		m_dibW ;
	long		m_dibH ;
	COleException m_pError;
	CString		  m_szServerError;
	long		m_xOffSet ;
	long		m_yOffSet ;
	CRect		m_RectPreview;
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_ACTIVEXDEMODLG_H__6698882A_D34A_11D4_9079_002018534C30__INCLUDED_)
