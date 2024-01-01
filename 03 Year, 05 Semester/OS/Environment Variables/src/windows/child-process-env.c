#include <stdio.h>
#include <windows.h>

int main() {

  char envvar[MAX_PATH];
  // In the Windows API the maximum length for a path is MAX_PATH, which is
  // defined as 260 characters
  DWORD result = GetEnvironmentVariable("TEST_ENV", envvar, MAX_PATH);
  printf("TEST_ENV (child): %s\n", envvar);
}
