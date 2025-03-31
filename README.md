# TLUContact
app/
├── build.gradle
├── src/
    ├── main/
        ├── java/com/example/tlucontactdemo/
        │   ├── model/
        │   │   ├── Student.java
        │   │   └── Department.java
        │   ├── repository/
        │   │   ├── StudentRepository.java
        │   │   └── DepartmentRepository.java
        │   ├── viewmodel/
        │   │   ├── StudentViewModel.java
        │   │   └── DepartmentViewModel.java
	|   ├── util
	|   |   └── FireStoreInstance.java
        │   ├── view/
        │   │   ├── adapter/
        │   │   │   ├── StudentAdapter.java
        │   │   │   └── DepartmentAdapter.java
        │   │   ├── fragment/
        │   │   │   ├── StudentListFragment.java
        │   │   │   └── DepartmentListFragment.java
	|   |   ├── RegisterActivity.java	
	|   |   ├── LoginActivity.java
        │   │   └── MainActivity.java
        ├── res/
            ├── layout/
            │   ├── activity_main.xml
            │   ├── fragment_student_list.xml
            │   ├── fragment_department_list.xml
            │   ├── item_student.xml
            │   └── item_department.xml
	    ├── menu
	    |   └──bottom_nav_menu.xml 
            ├── navigation/
            │   └── nav_graph.xml
            └── values/
                ├── strings.xml
                └── colors.xml