// ActiveXDemoDlg.cpp : implementation file
//

#include "stdafx.h"
#include "ActiveXDemo.h"
#include "ActiveXDemoDlg.h"
#include "SdefinesODL.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//		Documents Docs ;//= m_LabelingSoftware.GetDocuments();


/////////////////////////////////////////////////////////////////////////////
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialog
{
public:
	CAboutDlg();

// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialog(CAboutDlg::IDD)
{
	//{{AFX_DATA_INIT(CAboutDlg)
	//}}AFX_DATA_INIT
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAboutDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialog)
	//{{AFX_MSG_MAP(CAboutDlg)
		// No message handlers
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CActiveXDemoDlg dialog

CActiveXDemoDlg::CActiveXDemoDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CActiveXDemoDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CActiveXDemoDlg)
	m_LabName = _T("");
	m_bVisible = FALSE;
	m_VarValue = _T("");
	m_EnableEvents = FALSE;
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
//	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
	
	//m_hBitmap = NULL;
	//m_hDibPreview = NULL;
	m_hDIB = NULL ;
	m_WidthMetaFile = 0;
	m_HeightMetaFile = 0;
	m_xOffSet = 0 ;
	m_yOffSet = 0 ;
}

void CActiveXDemoDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CActiveXDemoDlg)
	DDX_Control(pDX, IDC_qty, m_qty);
	DDX_Control(pDX, IDC_LT_VARLIST, m_VariableList);
	DDX_Text(pDX, IDC_ET_LABNAME, m_LabName);
	DDX_Check(pDX, IDC_CK_VISIBLE, m_bVisible);
	DDX_Text(pDX, IDC_ET_VARVALUE, m_VarValue);
	DDX_Check(pDX, IDC_CK_ENABLEEVENTS, m_EnableEvents);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CActiveXDemoDlg, CDialog)
	//{{AFX_MSG_MAP(CActiveXDemoDlg)
	ON_WM_PAINT()
	ON_BN_CLICKED(IDC_BN_OPENDOC, OnBnOpendoc)
	ON_BN_CLICKED(IDC_CK_VISIBLE, OnCkVisible)
	ON_BN_CLICKED(IDC_BN_DISPLAYFORM, OnBnDisplayform)
	ON_BN_CLICKED(IDC_vscroll_up, Onvscrollup)
	ON_BN_CLICKED(IDC_vscrolldown, Onvscrolldown)
	ON_BN_CLICKED(IDC_hscrollright, Onhscrollright)
	ON_BN_CLICKED(IDC_hscrollleft, Onhscrollleft)
	ON_BN_CLICKED(IDC_BN_SAVEDOC, OnBnSavedoc)
	ON_BN_CLICKED(IDC_print, Onprint)
	ON_LBN_SELCHANGE(IDC_LT_VARLIST, OnSelchangeLtVarlist)
	ON_BN_CLICKED(IDC_BN_UPDATEVARVALUE, OnBnUpdatevarvalue)
	ON_BN_CLICKED(IDC_BN_CLOSEDOC, OnBnClosedoc)
	ON_BN_CLICKED(IDC_CK_ENABLEEVENTS, OnCkEnableevents)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CActiveXDemoDlg message handlers

BOOL CActiveXDemoDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Add "About..." menu item to system menu.


	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		CString strAboutMenu;
		strAboutMenu.LoadString(IDS_ABOUTBOX);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
		
	int ret = m_Lppx2Connection.GetConnection(&m_LabelingSoftware);
	
	if(ret == LPPX2_NOERR)
	{
		//m_LabelingSoftware.SetVisible (FALSE);
		//m_LabelingSoftware.SetVisible (TRUE);
		UpdatePreview();
	}
	m_qty.SetWindowText ("1") ;
	return TRUE;  
}

void CActiveXDemoDlg::OnSysCommand(UINT nID, LPARAM lParam)
{

		CDialog::OnSysCommand(nID, lParam);
}


// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.


void CActiveXDemoDlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
		Draw();
	}
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CActiveXDemoDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CActiveXDemoDlg::OnBnOpendoc() 
{
	// TODO: Add your control notification handler code here
	static char BASED_CODE szFilter[] = "LAB files (*.lab)|*.lab";
	CFileDialog openDlg(TRUE,
						"lab", 
						m_LabelingSoftware.GetDefaultFilePath () + "\\*.lab",
						OFN_HIDEREADONLY | OFN_OVERWRITEPROMPT,
						szFilter,
						this);	
	openDlg.m_ofn.lpstrInitialDir = m_LabelingSoftware.GetDefaultFilePath () ;
	if(openDlg.DoModal () == IDOK)
	{
		m_LabName = openDlg.GetPathName();
		m_VarValue = "";
		UpdateData(FALSE);
		Documents Docs = m_LabelingSoftware.GetDocuments();
        Docs = m_LabelingSoftware.GetDocuments();

		Docs.Open(m_LabName, FALSE);
		UpdatePreview();

		FillVariableList();
	}
	
}

void CActiveXDemoDlg::UpdatePreview() 
{
	m_xOffSet = 0 ;
	m_yOffSet = 0 ;
	IDocument1 CurrentDoc = m_LabelingSoftware.GetActiveDocument();
	CurrentDoc.CopyToClipboardEx (true,0,0,0,100);
	
	if(::OpenClipboard(::GetActiveWindow()))
	{
		if (m_hDIB != NULL)
		{
			DeleteObject(m_hDIB);
			m_hDIB = NULL;
		}
		HGLOBAL hGlobal=NULL;
		hGlobal = ::GetClipboardData(CF_DIB);
		if( hGlobal )
		{
			m_hDIB = (HDIB)::GlobalLock( hGlobal );
		}
		::GlobalUnlock( hGlobal );

		::CloseClipboard();
	}
	Invalidate();
}




BOOL WINAPI CreateDIBPalette(HDIB hDIB, CPalette* pPal)
{
	LPLOGPALETTE lpPal;      // pointer to a logical palette
	HANDLE hLogPal;          // handle to a logical palette
	int i;                   // loop index
	WORD wNumColors;         // number of colors in color table
	LPSTR lpbi;              // pointer to packed-DIB
	LPBITMAPINFO lpbmi;      // pointer to BITMAPINFO structure (Win3.0)
	LPBITMAPCOREINFO lpbmc;  // pointer to BITMAPCOREINFO structure (old)
	BOOL bWinStyleDIB;       // flag which signifies whether this is a Win3.0 DIB
	BOOL bResult = FALSE;

	/* if handle to DIB is invalid, return FALSE */

	if (hDIB == NULL)
	  return FALSE;

   lpbi = (LPSTR) ::GlobalLock((HGLOBAL) hDIB);

   /* get pointer to BITMAPINFO (Win 3.0) */
   lpbmi = (LPBITMAPINFO)lpbi;

   /* get pointer to BITMAPCOREINFO (old 1.x) */
   lpbmc = (LPBITMAPCOREINFO)lpbi;

   /* get the number of colors in the DIB */
   wNumColors = ::DIBNumColors(lpbi);

   if (wNumColors != 0)
   {
		/* allocate memory block for logical palette */
		hLogPal = ::GlobalAlloc(GHND, sizeof(LOGPALETTE)
									+ sizeof(PALETTEENTRY)
									* wNumColors);

		/* if not enough memory, clean up and return NULL */
		if (hLogPal == 0)
		{
			::GlobalUnlock((HGLOBAL) hDIB);
			return FALSE;
		}

		lpPal = (LPLOGPALETTE) ::GlobalLock((HGLOBAL) hLogPal);

		/* set version and number of palette entries */
		lpPal->palVersion = PALVERSION;
		lpPal->palNumEntries = (WORD)wNumColors;

		/* is this a Win 3.0 DIB? */
		bWinStyleDIB = IS_WIN30_DIB(lpbi);
		for (i = 0; i < (int)wNumColors; i++)
		{
			if (bWinStyleDIB)
			{
				lpPal->palPalEntry[i].peRed = lpbmi->bmiColors[i].rgbRed;
				lpPal->palPalEntry[i].peGreen = lpbmi->bmiColors[i].rgbGreen;
				lpPal->palPalEntry[i].peBlue = lpbmi->bmiColors[i].rgbBlue;
				lpPal->palPalEntry[i].peFlags = 0;
			}
			else
			{
				lpPal->palPalEntry[i].peRed = lpbmc->bmciColors[i].rgbtRed;
				lpPal->palPalEntry[i].peGreen = lpbmc->bmciColors[i].rgbtGreen;
				lpPal->palPalEntry[i].peBlue = lpbmc->bmciColors[i].rgbtBlue;
				lpPal->palPalEntry[i].peFlags = 0;
			}
		}

		/* create the palette and get handle to it */
		bResult = pPal->CreatePalette(lpPal);
		::GlobalUnlock((HGLOBAL) hLogPal);
		::GlobalFree((HGLOBAL) hLogPal);
	}

	::GlobalUnlock((HGLOBAL) hDIB);

	return bResult;
}







BOOL WINAPI PaintDIB(HDC     hDC,
					LPRECT  lpDCRect,
					HDIB    hDIB,
					LPRECT  lpDIBRect,
					CPalette* pPal)
{
	LPSTR    lpDIBHdr;            // Pointer to BITMAPINFOHEADER
	LPSTR    lpDIBBits;           // Pointer to DIB bits
	BOOL     bSuccess=FALSE;      // Success/fail flag
	HPALETTE hPal=NULL;           // Our DIB's palette
	HPALETTE hOldPal=NULL;        // Previous palette

	/* Check for valid DIB handle */
	if (hDIB == NULL)
		return FALSE;

	/* Lock down the DIB, and get a pointer to the beginning of the bit
	 *  buffer
	 */
	lpDIBHdr  = (LPSTR) ::GlobalLock((HGLOBAL) hDIB);
	lpDIBBits = ::FindDIBBits(lpDIBHdr);

	// Get the DIB's palette, then select it into DC
	if (pPal != NULL)
	{
		hPal = (HPALETTE) pPal->m_hObject;

		// Select as background since we have
		// already realized in forground if needed
		hOldPal = ::SelectPalette(hDC, hPal, TRUE);
	}

	/* Make sure to use the stretching mode best for color pictures */
	::SetStretchBltMode(hDC, COLORONCOLOR);

	/* Determine whether to call StretchDIBits() or SetDIBitsToDevice() */
	if ((RECTWIDTH(lpDCRect)  == RECTWIDTH(lpDIBRect)) &&
	   (RECTHEIGHT(lpDCRect) == RECTHEIGHT(lpDIBRect)) && (false)) 
		bSuccess = ::SetDIBitsToDevice(hDC,                    // hDC
								   lpDCRect->left,             // DestX
								   lpDCRect->top,              // DestY
								   RECTWIDTH(lpDCRect),        // nDestWidth
								   RECTHEIGHT(lpDCRect),       // nDestHeight
								   lpDIBRect->left,            // SrcX
								   (int)DIBHeight(lpDIBHdr) -
									  lpDIBRect->top -
									  RECTHEIGHT(lpDIBRect),   // SrcY
								   0,                          // nStartScan
								   (WORD)DIBHeight(lpDIBHdr),  // nNumScans
								   lpDIBBits,                  // lpBits
								   (LPBITMAPINFO)lpDIBHdr,     // lpBitsInfo
								   DIB_RGB_COLORS);            // wUsage
   else
	  bSuccess = ::StretchDIBits(hDC,                          // hDC
							   lpDCRect->left,                 // DestX
							   lpDCRect->top,                  // DestY
							   RECTWIDTH(lpDCRect),            // nDestWidth
							   RECTHEIGHT(lpDCRect),           // nDestHeight
							   lpDIBRect->left,                // SrcX
							   lpDIBRect->top,                 // SrcY
							   RECTWIDTH(lpDIBRect),           // wSrcWidth
							   RECTHEIGHT(lpDIBRect),          // wSrcHeight
							   lpDIBBits,                      // lpBits
							   (LPBITMAPINFO)lpDIBHdr,         // lpBitsInfo
							   DIB_RGB_COLORS,                 // wUsage
							   SRCCOPY);                       // dwROP

   ::GlobalUnlock((HGLOBAL) hDIB);

	/* Reselect old palette */
	if (hOldPal != NULL)
	{
		::SelectPalette(hDC, hOldPal, TRUE);
	}

   return bSuccess;
}



void CActiveXDemoDlg::Draw()
{
CPoint		mappoint;
int			OldMapMode;
CPoint		pOrg;
double		dFactor;
long		lWidth, 
			lHeight;
CRect		rectMetaFile;
	
	GetDlgItem(IDC_PREVIEWDOC)->GetWindowRect(&m_RectPreview);
	ScreenToClient(&m_RectPreview);

	// Recuperer le dc d'affichage
	CDC *pDCPreview = GetDC();		

	OldMapMode = pDCPreview->SetMapMode(MM_HIMETRIC);
	mappoint = CPoint(m_WidthMetaFile, -m_HeightMetaFile);

	pDCPreview->LPtoDP(&mappoint, 1);

	rectMetaFile = CRect(CPoint(0,0), CPoint(mappoint.x - 1, mappoint.y - 1));

	pOrg = m_RectPreview.TopLeft( );

	dFactor = (double)m_RectPreview.Width() / (double)rectMetaFile.Width();
	if ((double)m_RectPreview.Height() / (double)rectMetaFile.Height() < dFactor)
		dFactor = (double)m_RectPreview.Height() / (double)rectMetaFile.Height();

	lWidth = (long)(rectMetaFile.Width()*dFactor);
	lHeight = (long)(rectMetaFile.Height()*dFactor);

	// Dessiner le rectangle blanc de fond
	pDCPreview->SetMapMode(MM_TEXT);

	CRect rcBackground(pOrg, m_RectPreview.Size());
	pDCPreview->FillRect(rcBackground, CBrush::FromHandle((HBRUSH)GetStockObject(WHITE_BRUSH)));



	if (m_hDIB)
	{
		LPSTR lpbi = (LPSTR)GlobalLock(m_hDIB);
		m_dibW = (long) DIBWidth(lpbi) ;
		m_dibH = (long) DIBHeight(lpbi) ;



	
		RECT r_dib ;
		r_dib.left = 0 ;
		r_dib.right = this->m_dibW ;
		r_dib.top = 0 ;
		r_dib.bottom = this->m_dibH ;

		CRect rectPreviewMem = m_RectPreview ;

		// If image width is greater than the pictureBox width 
		// OR
		// image height is greater than the pictureBox height
		// then it must be resized...
		if ((r_dib.right > m_RectPreview.Width()) || (r_dib.bottom > m_RectPreview.Height()))
		{
			// which one (width or height) must be used to resize ?
			if ((r_dib.right / m_RectPreview.Width()) > (r_dib.bottom / m_RectPreview.Height()))
			{
				// Must use width to resize
				double a = ((double)m_RectPreview.Width()) / (((double)r_dib.right)) ;
				m_RectPreview.SetRect (m_RectPreview.left,m_RectPreview.top,m_RectPreview.right,m_RectPreview.bottom*a) ;
			}
			else
			{
				// Must use Height to resize
				double a = ((double)m_RectPreview.Height()) / (((double)r_dib.bottom)) ;
				m_RectPreview.SetRect (m_RectPreview.left,m_RectPreview.top,m_RectPreview.right*a,m_RectPreview.bottom) ;
			}
		}
		else
		{
			m_RectPreview.SetRect (m_RectPreview.left,m_RectPreview.top,m_RectPreview.left + r_dib.right,m_RectPreview.top + r_dib.bottom) ;

		}
		// Now we must center this preview

			int w = m_RectPreview.Width() ;
			int h = m_RectPreview.Height() ;
			m_RectPreview.left += (int)((rectPreviewMem.Width() - w)/2);
			m_RectPreview.right = m_RectPreview.left + w ;
			m_RectPreview.top += (int)((rectPreviewMem.Height() - h)/2);
			m_RectPreview.bottom = m_RectPreview.top + h ;

		CreateDIBPalette(m_hDIB, &m_palDIB) ;
		PaintDIB (pDCPreview->m_hDC,&m_RectPreview,m_hDIB,&r_dib,&m_palDIB) ;
	}
	


	pDCPreview->SetMapMode(OldMapMode);

	ReleaseDC(pDCPreview);
}

BOOL CActiveXDemoDlg::DestroyWindow() 
{
	
	if (m_hDIB != NULL)
	{
		DeleteObject(m_hDIB);
		m_hDIB = NULL;

	}
	return CDialog::DestroyWindow() ;
}



void CActiveXDemoDlg::OnCkVisible() 
{
	UpdateData(TRUE);
	
	m_LabelingSoftware.SetVisible(m_bVisible);
	
	UpdateData(FALSE);
	
}

void CActiveXDemoDlg::FillVariableList() 
{
	IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
	Variables Vars = MyDoc.GetVariables();
	Variable MyVar;
	COleVariant nItem;
	
	short sNb = Vars.GetCount();

	m_VariableList.ResetContent();
	
	for(short i = 0;i < sNb;i++)
	{
		nItem = short(i + 1);
		MyVar = Vars.Item(nItem);
		long VarType = MyVar.GetDataSource();
		if(	VarType == _lppxDataSourceForm ||
			VarType == _lppxDataSourceDataBase ||
			VarType == _lppxDataSourceCounter )
			m_VariableList.AddString(MyVar.GetName());
	}
}

void CActiveXDemoDlg::OnSelchangeLtVarlist() 
{
	int nIndex = m_VariableList.GetCurSel();
	int nCount = m_VariableList.GetCount();
	if ((nIndex != LB_ERR) && (nCount > 1))
	{
		CString str;
		Variable MyVar;
		COleVariant nItem;
		int n;

		//Get Variable name
		n = m_VariableList.GetTextLen( nIndex );
		m_VariableList.GetText( nIndex, str.GetBuffer(n) );
		str.ReleaseBuffer();

		//Get Variable value
		IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
		Variables Vars = MyDoc.GetVariables();
		nItem = str;
		MyVar = Vars._Item(nItem);
		str = MyVar.Get_Value();
		m_VarValue = str;
		UpdateData(FALSE);
	}
	
}

void CActiveXDemoDlg::OnBnUpdatevarvalue() 
{
	UpdateData(TRUE);
	int nIndex = m_VariableList.GetCurSel();
	int nCount = m_VariableList.GetCount();
	if ((nIndex != LB_ERR) && (nCount > 1))
	{
		CString str;
		Variable MyVar;
		COleVariant nItem;
		int n;

		//Get Variable name
		n = m_VariableList.GetTextLen( nIndex );
		m_VariableList.GetText( nIndex, str.GetBuffer(n) );
		str.ReleaseBuffer();

		//Set Variable value
		IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
		Variables Vars = MyDoc.GetVariables();
		nItem = str;
		MyVar = Vars._Item(nItem);
		MyVar.Set_Value( m_VarValue );

		//Retrieve value set
		str = MyVar.Get_Value();
		m_VarValue = str;
		UpdateData(FALSE);
		
		UpdatePreview () ;
	}
}


void CActiveXDemoDlg::OnBnDisplayform() 
{
	Dialogs Diags=m_LabelingSoftware.GetDialogs();
	Dialog  MyDial;
	short Nb=Diags.GetCount();
	COleVariant v ;
	MyDial= Diags.Item(3);
	MyDial.Show(v);
	UpdatePreview () ;
}


void CActiveXDemoDlg::OnBnSavedoc() 
{
	//if (MessageBox ("Are you sure ?","Confirm",MB_YESNO) == IDYES)
	{
		IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
		MyDoc.Save () ;

	}
}

void CActiveXDemoDlg::OnBnClosedoc() 
{
	//if (MessageBox ("Are you sure ?","Confirm",MB_YESNO) == IDYES)
	{
		IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
		MyDoc.Close( FALSE );

		m_VariableList.ResetContent();
		m_VarValue = "";
		m_LabName = "";
		UpdateData(FALSE);
		
		UpdatePreview () ;
	}
}

void CActiveXDemoDlg::OnCkEnableevents() 
{
	UpdateData(TRUE);
	m_LabelingSoftware.SetEnableEvents( m_EnableEvents );
}


void CActiveXDemoDlg::Onprint() 
{
	IDocument1 MyDoc =  m_LabelingSoftware.GetActiveDocument();
	CString s ;
	m_qty.GetWindowText(s) ;
	MyDoc.PrintDocument (atoi (s)) ;
}


void CActiveXDemoDlg::Onvscrollup() 
{
	m_yOffSet = max (m_yOffSet - 50,0) ;
	Draw ();
}

void CActiveXDemoDlg::Onvscrolldown() 
{
	m_yOffSet = min (m_yOffSet + 50,m_dibH-m_RectPreview.Height()) ;
	Draw ();	
}

void CActiveXDemoDlg::Onhscrollright() 
{
	m_xOffSet = min (m_xOffSet + 50,m_dibW-m_RectPreview.Width()) ;
	Draw ();	
}

void CActiveXDemoDlg::Onhscrollleft() 
{
	m_xOffSet = max (m_xOffSet - 50,0) ;
	Draw ();	
}







