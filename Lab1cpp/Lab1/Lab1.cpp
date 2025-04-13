#include <windows.h>
#include <math.h>

#define PI 3.14159265


void DrawSun(HDC hdc, int x, int y, int radius, int numRays, COLORREF color) {
    int step = 360 / numRays;
    HPEN hPen = CreatePen(PS_SOLID, 2, color);
    HPEN hOldPen = (HPEN)SelectObject(hdc, hPen);


    HBRUSH hBrush = CreateSolidBrush(color);
    HBRUSH hOldBrush = (HBRUSH)SelectObject(hdc, hBrush);
    POINT sunBody[18];
    for (int i = 0; i < numRays; i++) {
        double angle = i * step * PI / 180.0;
        sunBody[i].x = x + radius * cos(angle);
        sunBody[i].y = y - radius * sin(angle);
    }
    Polygon(hdc, sunBody, numRays);
    SelectObject(hdc, hOldBrush);
    DeleteObject(hBrush);

    for (int i = 0; i < 360; i += step) {
        int x2 = x + (radius + 20) * cos(i * PI / 180);
        int y2 = y - (radius + 20) * sin(i * PI / 180);
        MoveToEx(hdc, x, y, NULL);
        LineTo(hdc, x2, y2);
    }

    SelectObject(hdc, hOldPen);
    DeleteObject(hPen);
}


void DrawTriangle(HDC hdc, int x, int y, int size, COLORREF color) {
    HBRUSH hBrush = CreateSolidBrush(color);
    HBRUSH hOldBrush = (HBRUSH)SelectObject(hdc, hBrush);

    POINT points[3] = {
        {x, y - size}, {x - size, y + size}, {x + size, y + size}
    };
    Polygon(hdc, points, 3);

    SelectObject(hdc, hOldBrush);
    DeleteObject(hBrush);
}


LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam) {
    switch (message) {
    case WM_PAINT: {
        PAINTSTRUCT ps;
        HDC hdc = BeginPaint(hWnd, &ps);

     
        HBRUSH hBrush = CreateSolidBrush(RGB(200, 200, 200)); 
        FillRect(hdc, &ps.rcPaint, hBrush);
        DeleteObject(hBrush);


        DrawSun(hdc, 300, 200, 50, 18, RGB(0, 0, 255)); 
        DrawTriangle(hdc, 300, 350, 50, RGB(255, 0, 0)); 

        EndPaint(hWnd, &ps);
    }
                 break;

    case WM_DESTROY:
        PostQuitMessage(0);
        break;

    default:
        return DefWindowProc(hWnd, message, wParam, lParam);
    }
    return 0;
}


int APIENTRY WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    WNDCLASS wc = { 0 };
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInstance;
    wc.lpszClassName = L"Lab1GDI";
    RegisterClass(&wc);

    HWND hwnd = CreateWindow(wc.lpszClassName, L"Lab1 - GDI Graphics", WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT, 600, 600, NULL, NULL, hInstance, NULL);
    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);

    MSG msg;
    while (GetMessage(&msg, NULL, 0, 0)) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }
    return (int)msg.wParam;
}