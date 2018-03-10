#ifndef __DEFINESODL_H__
#define __DEFINESODL_H__

////////////////////////////////////////////////////////////////////////////////////////////
// enumSupportType;
////////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNoSupport		 ST_NOSUPPORT
#define _lppxLabelGap		 ST_LABELGAP
#define _lppxContinuous		 ST_CONTINUOUS
#define _lppxLabelMark		 ST_LABELMARK
#define _lppxLabelHole		 ST_LABELHOLE
#define _lppxVarLength		 ST_VARLENGTH
#define _lppxLabelMarkAbove	 ST_LABELMARKABOVE
#define _lppxLabelMarkBelow	 ST_LABELMARKBELOW
#define _lppxLabelNotch		 ST_LABELNOTCH
#define _lppxFanfoldInto	 ST_FANFOLDINTO
#define _lppxFanfoldOver	 ST_FANFOLDOVER
#define _lppxLowerDown		 ST_LOWERDOWN
#define _lppxLowerUp		 ST_LOWERUP
#define _lppxUpperDown		 ST_UPPERDOWN
#define _lppxUpperUp		 ST_UPPERUP
#define _lppxAdvancedGap	 ST_ADVANCEDGAP
#define _lppxAdvancedNotch	 ST_ADVANCEDNOTCH

// //////////////////////////////////////////////////////////////////////////////////////////
// enumSwitchToEx 
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxCreatePrinterIfNotExist	0x01

//#if _Modif(CBOE_6LKHBZ)
// //////////////////////////////////////////////////////////////////////////////////////////
// enumDisplayPrintMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxDisplayPrintModeDirect					0
#define _lppxDisplayPrintModeSimple					1
#define _lppxDisplayPrintModeExpanded				2
#define _lppxDisplayPrintModeSearchInDatabase		3
#define _lppxDisplayPrintModeDatabaseQueryResult	4

// //////////////////////////////////////////////////////////////////////////////////////////
// enumPrintMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPrintModeTest					0x0001
#define _lppxPrintModeDirect				0x0002
#define _lppxPrintModeMergeAll				0x0004
#define _lppxPrintModeMerge					0x0008
#define _lppxPrintModeInfile				0x0100
//#if _Modif(LAD_LAS)
#define _lppxPrintModeRePrint				0x0200
//#endif
/*#else
// //////////////////////////////////////////////////////////////////////////////////////////
// enumPrintMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPrintModeDirect				0
#define _lppxPrintModeSimple				1
#define _lppxPrintModeExpanded				2
#define _lppxPrintModeSearchInDatabase		3
#define _lppxPrintModeDatabaseQueryResult	4
#endif*/

// //////////////////////////////////////////////////////////////////////////////////////////
// enumPreviewMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPreviewModeRealSize			0
#define _lppxPreviewModeFullPage			1
#define _lppxPreviewModeFullWidth			2

// //////////////////////////////////////////////////////////////////////////////////////////
// enumDocuments
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxDocumentCS600				600
#define _lppxDocumentCS601				601
#define _lppxDocumentCS602				602
#define _lppxDocumentCS603				603
#define _lppxDocumentCS604				604
#define _lppxDocumentCS610				610
#define _lppxDocumentCS611				611
#define _lppxDocumentCS700				700

// //////////////////////////////////////////////////////////////////////////////////////////
// enumKindOfPrinters
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxInternalPrinters			1
#define _lppxWindowsPrinters			2
#define _lppxAllPrinters				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumTriggerForm
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNever						1
#define _lppxForEachSerie				2
#define _lppxForEachLabel				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumSort
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNoneSort					1
#define _lppxAscendingSort				2
#define _lppxDescendingSort				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumShared
// //////////////////////////////////////////////////////////////////////////////////////////
#define	_lppxSharedCounter				0
#define	_lppxSharedForm					1
#define	_lppxSharedFree					2
#define	_lppxSharedList					3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumDataSource
// //////////////////////////////////////////////////////////////////////////////////////////
#define	_lppxDataSource					0
#define	_lppxDataSourceCounter			1
#define	_lppxDataSourceTableLookup		2
#define	_lppxDataSourceDate				3
#define	_lppxDataSourceFormula			4
#define	_lppxDataSourceFree				5
#define	_lppxDataSourceForm				6
#define	_lppxDataSourceDataBase			7

// //////////////////////////////////////////////////////////////////////////////////////////
// enumRotation
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNoRotation					0
#define _lppx90DegreeRight				1
#define _lppxUpSideDown					2
#define _lppx90DegreeLeft				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumAlignment
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxAlignLeft					0
#define _lppxAlignCenter				1
#define _lppxAlignRight					2
#define _lppxAlignJustify				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumHRPosition
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxHRNone						0
#define _lppxHRBelow					1
#define _lppxHRAbove					2
#define _lppxHRFree						3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumCheckMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNone						0
#define _lppx1Digit						1
#define _lppx2Digits					2
#define _lppxMod11Mod10					3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumAnchorPoint
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxTopLeft					1
#define _lppxTopCenter					2
#define _lppxTopRight					3
#define _lppxCenterLeft					4
#define _lppxCenter						5
#define _lppxCenterRight				6
#define _lppxBottomLeft					7
#define _lppxBottomCenter				8
#define _lppxBottomRight				9

// //////////////////////////////////////////////////////////////////////////////////////////
// enumCounterBase
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxBaseBinary					2
#define _lppxBaseOctal					8
#define _lppxBaseDecimal				10
#define _lppxBaseHexadecimal			16
#define _lppxBaseAlphabetic				26
#define _lppxBaseAlphaNumeric			36
#define _lppxBaseCustom					255

// //////////////////////////////////////////////////////////////////////////////////////////
// enumDocObject
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxObjectText					1
#define _lppxObjectBarCode				2
#define _lppxObjectImage				3
#define _lppxObjectLine					4
#define _lppxObjectRectangle			5
#define _lppxObjectEllipse				6
#define _lppxObjectPolygon				7
#define _lppxObjectOblique				8
#define _lppxObjectRoundRect			9
#define _lppxObjectOLEObject			10
#define _lppxObjectPolyline				11
#define _lppxObjectColorField			12
#define _lppxObjectListField			13
#define _lppxObjectRichTextField		14
#define _lppxObjectTextArt				15

// //////////////////////////////////////////////////////////////////////////////////////////
// enumWindowState
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNormal						1
#define _lppxMinimized					2
#define _lppxMaximized					3

/////////////////////////////////////////////////
// enumViewMode
/////////////////////////////////////////////////
#define _lppxViewModeName				1
#define _lppxViewModeSize				2
#define _lppxViewModeValue				3
#define _lppxViewModeForm				4

// //////////////////////////////////////////////////////////////////////////////////////////
// enumDialogType
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPrinterSelectDialog		1
#define _lppxOptionsDialog				2
#define _lppxFormDialog					3
#define _lppxPrinterSetupDialog			4
#define _lppxPageSetupDialog			5
#define _lppxDocumentPropertiesDialog	6
#define	_lppxPrintDialog				7

// //////////////////////////////////////////////////////////////////////////////////////////
// enumTriggerMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxNumberOfPrintedLabels		1
#define _lppxResetOfAnotherCounter		2

// //////////////////////////////////////////////////////////////////////////////////////////
// enumBuiltInDocumentProperty
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPropertyManager			1
#define _lppxPropertyCompany			2
#define _lppxPropertyCategory			3
#define _lppxPropertyTitle				4
#define _lppxPropertySubject			5
#define _lppxPropertyAuthor				6
#define _lppxPropertyKeywords			7
#define _lppxPropertyComments			8

// //////////////////////////////////////////////////////////////////////////////////////////
// enumProperty
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxPropertyTypeNumber			1
#define _lppxPropertyTypeBoolean		2
#define _lppxPropertyTypeDate			3
#define _lppxPropertyTypeString			4
#define _lppxPropertyTypeFloat			5

// //////////////////////////////////////////////////////////////////////////////////////////
// enumEndPrinting
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxEndOfJob					1
#define _lppxCancelled					2
#define _lppxSystemFailure				3

// //////////////////////////////////////////////////////////////////////////////////////////
// enumPausedReasonPrinting
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxGenericError				0
#define _lppxNoPaper					2
#define _lppxNoRibbon					3
#define _lppxPortNotAvailable			4
#define _lppxPrinterNotReady			5
#define _lppxCommunicationError			6
#define _lppxHeadLifted					7

// //////////////////////////////////////////////////////////////////////////////////////////
// enumSymbology
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxCode11         			49
#define _lppx25Interleave   			50
#define _lppxCode39         			51
#define _lppxCode49         			52
#define _lppxMaxicode					53
#define _lppxCode16K					54
#define _lppxGermanPostcode				55
#define _lppxEAN8       				56
#define _lppxUPCE       				57
#define _lppxBC412						58
#define _lppxMicroPDF					59
#define _lppxCode93         			65
#define _lppx25Beared   				66
#define _lppxCode128        			67
#define _lppxEAN128     				68
#define _lppxEAN13      				69
#define _lppxCode39Full     			70
#define _lppxCode128Auto				71
#define _lppxCodablockF					72
#define _lppx25Industrial    			73
#define _lppx25Standard      			74
#define _lppxCodabar    				75
#define _lppxLogmars    				76
#define _lppxMsi        				77
#define _lppxCodablockA					78
#define _lppxPostnet    				79
#define _lppxPlessey    				80
#define _lppxCode128SSCC    			81
#define _lppxUPCExtended     			83
#define _lppxUPCA       				85
#define _lppxUPCEXT2    				86
#define _lppxUPCEXT5    				87
#define _lppxCode25PRDG     			88
#define _lppxUPCWEIGHT  				89
#define _lppxUPCEPLUS2  				97
#define _lppxUPCEPLUS5  				98
#define _lppxUPCAPLUS2  				99
#define _lppxUPCAPLUS5  				100
#define _lppxEAN8PLUS2  				101
#define _lppxEAN8PLUS5  				102
#define _lppxEAN13PLUS2 				103
#define _lppxEAN13PLUS5 				104
#define _lppxITF        				105
#define _lppx25MatrixEuropean			106
#define _lppx25MatrixJapan 				107
#define _lppxDatamatrix 				120
#define _lppxItf14      				121
#define _lppxPdf        				122
#define _lppxQrcode						123
#define _lppxRss						124
#define _lppxComposite					125
#define _lppxTLC39						126
#define _lppxCIP						127
#define _lppxAztec						128
#define _lppxAztecMesa					129
#define _lppxEAN14						130
#define _lppxBookland					131
#define _lppxPlanet						132
#define _lppxPharmacode					133		
#define _lppxItf16						134
#define _lppxVericode					135
#define _lppxCode93i					136
#define _lppxRM4SCC						137

// //////////////////////////////////////////////////////////////////////////////////////////
// enumImage
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxImageNoneResizable			0
#define _lppxImageStrechable			1
#define _lppxImageFixedRatio			2

// //////////////////////////////////////////////////////////////////////////////////////////
// enumDitherMethod
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxImageNone					0
#define _lppxImageBayer					1
#define _lppxImageBurkes				2
#define _lppxImageFloyd					3
#define _lppxImageGrayscale				4
#define _lppxImageFixedpalette			5
#define _lppxImageHalftone				6
#define _lppxImageThreshold				7

// //////////////////////////////////////////////////////////////////////////////////////////
// enumSettingsMode
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxSettingModeGeneric			0
#define _lppxSettingModeUser			1
#define _lppxSettingModeStation			2

// //////////////////////////////////////////////////////////////////////////////////////////
//  enumDatabaseType
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxDatabaseTypeNull			_tkxNULL
#define _lppxDatabaseTypeOLEDB			_tkxOLEDB
#define _lppxDatabaseTypeODBC			_tkxODBC
#define _lppxDatabaseTypeASCII			_tkxASCII

// //////////////////////////////////////////////////////////////////////////////////////////
//  enumDatabaseFind
// //////////////////////////////////////////////////////////////////////////////////////////
#define _lppxDatabaseFindErr			_tkxFindErr
#define _lppxDatabaseFindFirst			_tkxFindFirst
#define _lppxDatabaseFindPrev			_tkxFindPrev
#define _lppxDatabaseFindNext			_tkxFindNext
#define _lppxDatabaseFindLast			_tkxFindLast

#endif // __DEFINESODL_H__