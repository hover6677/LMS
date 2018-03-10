
#define HDIB HANDLE
#define WIDTHBYTES(bits)    (((bits) + 31) / 32 * 4)

/* DIB constants */
#define PALVERSION   0x300

/* DIB macros */
#define IS_WIN30_DIB(lpbi)  ((*(LPDWORD)(lpbi)) == sizeof(BITMAPINFOHEADER))
#define RECTWIDTH(lpRect)     ((lpRect)->right - (lpRect)->left)
#define RECTHEIGHT(lpRect)    ((lpRect)->bottom - (lpRect)->top)

HDIB BitmapToDIB(HBITMAP hBitmap, HPALETTE hPal);
WORD PaletteSize(LPSTR lpDIB);
WORD DIBNumColors(LPSTR lpDIB);
DWORD WINAPI DIBWidth(LPSTR lpDIB);
DWORD WINAPI DIBHeight(LPSTR lpDIB);
LPSTR WINAPI FindDIBBits(LPSTR lpDIB);