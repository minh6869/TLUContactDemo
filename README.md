# TLUContact
# TLUContact Demo Project Structure

This Android application follows the MVVM (Model-View-ViewModel) architecture pattern and is organized into the following packages:

## Package Structure

### Model (`model/`)
- Contains data classes that represent the core business logic
- `Student.java`: Student entity class
- `Department.java`: Department entity class

### Repository (`repository/`)
- Handles data operations and provides a clean API to the rest of the application
- `StudentRepository.java`: Manages student data operations
- `DepartmentRepository.java`: Manages department data operations

### ViewModel (`viewmodel/`)
- Acts as a data holder and handles the communication between the View and Model
- `StudentViewModel.java`: Manages UI-related student data
- `DepartmentViewModel.java`: Manages UI-related department data

### Utility (`util/`)
- Contains helper classes and utilities
- `FireStoreInstance.java`: Manages Firebase Firestore database connection

### View (`view/`)
Contains all UI-related components:

#### Activities
- `MainActivity.java`: Main application screen
- `LoginActivity.java`: Handles user authentication
- `RegisterActivity.java`: Manages new user registration

#### Fragments (`fragment/`)
- `StudentListFragment.java`: Displays list of students
- `DepartmentListFragment.java`: Displays list of departments

#### Adapters (`adapter/`)
- `StudentAdapter.java`: RecyclerView adapter for student lists
- `DepartmentAdapter.java`: RecyclerView adapter for department lists

## Resources (`res/`)

### Layouts (`layout/`)
- `activity_main.xml`: Main activity layout
- `fragment_student_list.xml`: Student list fragment layout
- `fragment_department_list.xml`: Department list fragment layout
- `item_student.xml`: Individual student item layout
- `item_department.xml`: Individual department item layout

### Navigation (`navigation/`)
- `nav_graph.xml`: Navigation component graph

### Menu (`menu/`)
- `bottom_nav_menu.xml`: Bottom navigation menu items

### Values (`values/`)
- `strings.xml`: String resources
- `colors.xml`: Color definitions

This structure follows Android best practices and MVVM architecture principles, ensuring separation of concerns and maintainable code.



```plaintext
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
         | 	 |   ├── RegisterActivity.java	
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
 	             └──bottom_nav_menu.xml 
             ├── navigation/
             │   └── nav_graph.xml
             └── values/
                 ├── strings.xml
                 └── colors.xml
```