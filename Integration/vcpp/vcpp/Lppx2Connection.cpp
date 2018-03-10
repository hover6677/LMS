// Lppx2Connection.cpp: implementation of the CLppx2Connection class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "ActiveXDemo.h"
#include "Lppx2Connection.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CLppx2Connection::CLppx2Connection()
{
	m_pLabelingSoftware = NULL;
	m_pDisp = NULL;
	m_bAttached = FALSE;
	m_bWellLoad = FALSE;
}

CLppx2Connection::~CLppx2Connection()
{
	DisConnect();	
}

int CLppx2Connection::GetConnection(IApplication *pApp)
{
	if(pApp == NULL)
		return LPPX2_ERR;

	if(m_pLabelingSoftware != NULL)
		return LPPX2_ERR_ALREADYCONNECTED;

	m_pLabelingSoftware = pApp;
	if(ConnectToActiveXInterface())
		return LPPX2_NOERR;
	else
		return LPPX2_ERR;
}

BOOL CLppx2Connection::ConnectToActiveXInterface()
{
// Get the Active Lppx2 Object Running
//
COleException pError;
CLSID clsid;
HRESULT hr;
IUnknown *pInterface=NULL;
CString szTemp;
CString szLABName;

	hr = ::CLSIDFromProgID(L"Lppx2.Application",&clsid);
	if (FAILED(hr))
		return FALSE;

	hr = ::GetActiveObject(clsid, NULL, &pInterface);
	if ( !FAILED(hr))
	{
		hr = pInterface->QueryInterface(IID_IDispatch, (void FAR* FAR*)&m_pDisp); 
		if ( !FAILED(hr) )
		{
			m_bAttached = TRUE;
			m_bWellLoad = TRUE;
			m_pLabelingSoftware->AttachDispatch(m_pDisp, TRUE);
		}
	}

	if( !m_bWellLoad )
	{
		//No current Instance Running
		//A new one is created for connection
			
		hr = ::CLSIDFromProgID(L"Lppx2.Application",&clsid);
		
		if ( !FAILED(hr) )
		{
			m_bWellLoad = TRUE;
			TRY
			{
      			m_pLabelingSoftware->CreateDispatch( "Lppx2.Application", &m_pError);
			}
			//Catch control-specific exceptions.
			CATCH (COleDispatchException, m_pError) 
			{
			CString cStr;

				if (!m_pError->m_strSource.IsEmpty())
					cStr = m_pError->m_strSource + " - ";
				if (!m_pError->m_strDescription.IsEmpty())
					cStr += m_pError->m_strDescription;
				else
					cStr += "unknown error";

				AfxMessageBox(cStr, MB_OK,(m_pError->m_strHelpFile.IsEmpty())? 0:m_pError->m_dwHelpContext);
				m_pError->Delete();
			}
            END_CATCH
		}
	}
	return m_bWellLoad;
}


void CLppx2Connection::DisConnect()
{
	if(m_pLabelingSoftware)
	//if a connection exist we make the disconnection
	{
		if (m_bAttached)
		{
			m_pLabelingSoftware->DetachDispatch();
			m_pDisp->Release();
			m_pDisp = NULL;
		}
		else
		{
			m_pLabelingSoftware->Quit();
			m_pLabelingSoftware->ReleaseDispatch( );
		}
	}
}
