// Lppx2Connection.h: interface for the CLppx2Connection class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LPPX2CONNECTION_H__04F67164_45F5_4757_A215_C437C06C4F26__INCLUDED_)
#define AFX_LPPX2CONNECTION_H__04F67164_45F5_4757_A215_C437C06C4F26__INCLUDED_

#include "lppx2.h"

#define LPPX2_NOERR					0	//
#define LPPX2_ERR					-1	//Generic error
#define LPPX2_ERR_ALREADYCONNECTED	-2	//

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CLppx2Connection  
{
public:
	CLppx2Connection();
	virtual ~CLppx2Connection();

	int GetConnection(IApplication *);
	void DisConnect();

private:
	IApplication	*m_pLabelingSoftware;
	LPDISPATCH		m_pDisp;
	BOOL			m_bAttached;
	BOOL			m_bWellLoad;
	COleException	m_pError;
			
	BOOL ConnectToActiveXInterface();
};

#endif // !defined(AFX_LPPX2CONNECTION_H__04F67164_45F5_4757_A215_C437C06C4F26__INCLUDED_)
