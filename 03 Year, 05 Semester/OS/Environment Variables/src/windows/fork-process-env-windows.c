#include <stdio.h>
#include <windows.h>

int main(int argc, char *argv[]) {

  SetEnvironmentVariable("TEST_ENV", "test");
  char envvar[MAX_PATH];
  DWORD result = GetEnvironmentVariable("TEST_ENV", envvar, MAX_PATH);
  printf("TEST_ENV: %s\n", envvar);

    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    ZeroMemory(&pi, sizeof(pi));

    if (CreateProcess(NULL,   
        "child.exe",          
        NULL,                 
        NULL,                
        FALSE,                
        0,                    
        NULL,                
        NULL,                 
        &si,                  
        &pi))                 
    {
        WaitForSingleObject(pi.hProcess, INFINITE); 
        CloseHandle(pi.hProcess);                    
        CloseHandle(pi.hThread);                     
    }
    else {
        printf("Error when creating child process (%d)\n", GetLastError());
    }

  return 0;
}