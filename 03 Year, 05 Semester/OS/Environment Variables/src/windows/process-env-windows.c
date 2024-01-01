#include <stdio.h>
#include <windows.h>

int main() {

  SetEnvironmentVariable("TEST_ENV", "test");
  char envvar[MAX_PATH];
  DWORD result = GetEnvironmentVariable("TEST_ENV", envvar, MAX_PATH);
  printf("TEST_ENV: %s\n", envvar);
}
