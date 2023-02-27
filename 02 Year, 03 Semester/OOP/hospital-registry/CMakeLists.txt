cmake_minimum_required(VERSION 3.21)
project(hospital-registry)

set(CMAKE_CXX_STANDARD 17)
set(exec_sources src/main.cpp src/date.cpp src/doctor.cpp src/file.cpp  src/menu.cpp src/patient.cpp src/person.cpp src/registry.cpp src/hospital.cpp src/appointment.cpp src/idgenerator.cpp src/showable.cpp src/menuItem.cpp src/runnable.cpp src/error.cpp)
set(exec_header include/date.h include/doctor.h include/file.h include/menu.h include/patient.h include/person.h include/registry.h include/hospital.h include/appointment.h include/idgenerator.h include/showable.h include/menuItem.h include/runnable.h include/state.h include/error.h)
add_executable(exec ${exec_sources} ${exec_headers})