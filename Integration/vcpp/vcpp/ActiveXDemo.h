// ActiveXDemo.h : main header file for the ACTIVEXDEMO application
//

#if !defined(AFX_ACTIVEXDEMO_H__66988828_D34A_11D4_9079_002018534C30__INCLUDED_)
#define AFX_ACTIVEXDEMO_H__66988828_D34A_11D4_9079_002018534C30__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CActiveXDemoApp:
// See ActiveXDemo.cpp for the implementation of this class
//

class CActiveXDemoApp : public CWinApp
{
public:
	CActiveXDemoApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CActiveXDemoApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CActiveXDemoApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_ACTIVEXDEMO_H__66988828_D34A_11D4_9079_002018534C30__INCLUDED_)
