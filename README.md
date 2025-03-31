# TLUContact
## Project Structure

This Android project follows the MVVM (Model-View-ViewModel) architecture pattern and is organized as follows:

### Model Layer
- `model/` - Contains data models
  - `Student.java` - Student entity class
  - `Department.java` - Department entity class

### Repository Layer  
- `repository/` - Handles data operations
  - `StudentRepository.java` - Manages student data access
  - `DepartmentRepository.java` - Manages department data access

### ViewModel Layer
- `viewmodel/` - Contains ViewModels that manage UI data
  - `StudentViewModel.java` - Handles student-related UI logic
  - `DepartmentViewModel.java` - Handles department-related UI logic

### View Layer
- `view/` - Contains UI components
  - `adapter/` - RecyclerView adapters
    - `StudentAdapter.java` - Adapter for student list
    - `DepartmentAdapter.java` - Adapter for department list
  - `fragment/` - UI fragments
    - `StudentListFragment.java` - Shows student list
    - `DepartmentListFragment.java` - Shows department list
  - `MainActivity.java` - Main activity class

### Resources
- `res/` - Resource files
  - `layout/` - XML layout files
    - `activity_main.xml` - Main activity layout
    - `fragment_student_list.xml` - Student list fragment layout
    - `fragment_department_list.xml` - Department list fragment layout
    - `item_student.xml` - Student list item layout
    - `item_department.xml` - Department list item layout
  - `navigation/` - Navigation components
    - `nav_graph.xml` - Navigation graph for the app
  - `values/` - Value resources
    - `strings.xml` - String resources
    - `colors.xml` - Color resources

```plaintext
app/
├── build.gradle
├── src/
    ├── main/
        ├── java/com/example/mvvmdemo/
        │   ├── model/
        │   │   ├── Student.java
        │   │   └── Department.java
        │   ├── repository/
        │   │   ├── StudentRepository.java
        │   │   └── DepartmentRepository.java
        │   ├── viewmodel/
        │   │   ├── StudentViewModel.java
        │   │   └── DepartmentViewModel.java
        │   ├── view/
        │   │   ├── adapter/
        │   │   │   ├── StudentAdapter.java
        │   │   │   └── DepartmentAdapter.java
        │   │   ├── fragment/
        │   │   │   ├── StudentListFragment.java
        │   │   │   └── DepartmentListFragment.java
        │   │   └── MainActivity.java
        ├── res/
            ├── layout/
            │   ├── activity_main.xml
            │   ├── fragment_student_list.xml
            │   ├── fragment_department_list.xml
            │   ├── item_student.xml
            │   └── item_department.xml
            ├── navigation/
            │   └── nav_graph.xml
            └── values/
                ├── strings.xml
                └── colors.xml
```